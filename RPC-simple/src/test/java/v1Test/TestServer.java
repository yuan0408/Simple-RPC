package v1Test;

import provider.ServiceProvider;
import transport.server.BIORpcServer;
import transport.server.impl.BlogServiceImpl;
import transport.server.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {

        ServiceProvider serviceProvider = new ServiceProvider();
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();
        serviceProvider.register(userService);
        serviceProvider.register(blogService);

        BIORpcServer bioRpcServer = new BIORpcServer(serviceProvider);
        bioRpcServer.start(8080);
    }
}
