package transport.message;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Data
@ToString
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 7152501340046298201L;

    private int code;
    private String message;
    private Object data;

    public static RpcResponse success(Object data){
        return RpcResponse.builder().code(200).message("success").data(data).build();
    }

    public static RpcResponse fail(){
        return RpcResponse.builder().code(500).message("fail").build();
    }
}
