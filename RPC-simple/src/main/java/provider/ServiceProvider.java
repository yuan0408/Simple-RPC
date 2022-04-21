package provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private Map<String,Object> map;

    public ServiceProvider(){
        map = new HashMap<String, Object>();
    }

    public void register(Object obj){
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        for (Class clazz :
                interfaces) {
            map.put(clazz.getName(),obj);
        }
    }

    public Object getService(String interfaceName){
        return map.get(interfaceName);
    }
}
