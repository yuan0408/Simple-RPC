package proxy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transport.client.RpcClient;
import transport.message.RpcRequest;
import transport.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
@Slf4j
public class ClientProxy implements InvocationHandler {

    private RpcClient rpcClient;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .parameters(args)
                .build();

        log.info("send request:{}",request.toString());
        RpcResponse rpcResponse = rpcClient.sendRequest(request);
        return rpcResponse.getData();
    }

    //客户端获取接口的代理对象
    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
    }
}
