package transport.server;

import lombok.extern.slf4j.Slf4j;
import provider.ServiceProvider;
import transport.message.RpcRequest;
import transport.server.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RpcServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket = new ServerSocket(8080);
        log.info("server boot");
        Socket accept = serverSocket.accept();

        //注册服务
        ServiceProvider serviceProvider = new ServiceProvider();
        UserServiceImpl userService = new UserServiceImpl();
        serviceProvider.register(userService);

        //假设只收到RPC请求
        ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
        RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

        //反射
        Object service = serviceProvider.getService(rpcRequest.getInterfaceName());
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
        Object res = method.invoke(service, rpcRequest.getParameters());
        log.info("rpc response:{}",res);

        //返回结果
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
        objectOutputStream.writeObject(res);
        objectOutputStream.flush();
    }
}
