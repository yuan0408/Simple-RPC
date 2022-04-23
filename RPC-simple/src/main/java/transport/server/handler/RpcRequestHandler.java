package transport.server.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import provider.ServiceProvider;
import transport.message.RpcRequest;
import transport.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class RpcRequestHandler implements Runnable {

    private ServiceProvider serviceProvider;
    private Socket socket;

    public RpcResponse getResponse(RpcRequest rpcRequest) {
        //反射
        Object service = serviceProvider.getService(rpcRequest.getInterfaceName());
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            Object res = method.invoke(service, rpcRequest.getParameters());
            log.info("rpc response:{}", res);
            return RpcResponse.success(res);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return RpcResponse.fail();
        }
    }

    @Override
    public void run() {
        //假设只收到RPC请求
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            log.info("get request:{}", rpcRequest);

            RpcResponse response = getResponse(rpcRequest);
            log.info("send response:{}", response);

            //返回结果
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
