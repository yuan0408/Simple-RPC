package transport.client;

import lombok.extern.slf4j.Slf4j;
import service.UserService;
import service.common.User;
import transport.message.RpcRequest;
import transport.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class RpcClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        //手动构造请求对象
        RpcRequest selectUserById = RpcRequest.builder()
                .interfaceName(UserService.class.getName())
                .methodName("selectUserById")
                .parameters(new Object[]{1})
                .paramTypes(new Class[]{Integer.class})
                .build();
        objectOutputStream.writeObject(selectUserById);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object res = objectInputStream.readObject();
        log.info("rpc response:{}",res);
    }
}
