package transport.client;

import transport.message.RpcRequest;
import transport.message.RpcResponse;

public interface RpcClient {

    RpcResponse sendRequest(RpcRequest rpcRequest);
}
