package transport.server;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import provider.ServiceProvider;
import transport.message.RpcRequest;
import transport.message.RpcResponse;
import transport.server.handler.RpcRequestHandler;
import transport.server.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class BIORpcServer {

    private ServiceProvider serviceProvider;

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("server boot");

            while (true) {
                Socket accept = serverSocket.accept();
                log.info("accept {}", accept.getRemoteSocketAddress().toString());

                new Thread(new RpcRequestHandler(serviceProvider, accept)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
