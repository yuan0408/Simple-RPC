package transport.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -5428831208111082529L;

    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
