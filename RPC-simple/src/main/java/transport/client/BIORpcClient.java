package transport.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transport.message.RpcRequest;
import transport.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class BIORpcClient implements RpcClient {

    private String host;
    private int port;

    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object res = objectInputStream.readObject();
            log.info("rpc response:{}", res);

            return (RpcResponse) res;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("send rpc request fail");
        }
    }
}
