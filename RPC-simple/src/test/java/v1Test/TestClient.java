package v1Test;

import proxy.ClientProxy;
import service.BlogService;
import service.UserService;
import service.common.Blog;
import service.common.User;
import transport.client.BIORpcClient;

public class TestClient {
    public static void main(String[] args) {
        BIORpcClient client = new BIORpcClient("localhost", 8080);
        ClientProxy clientProxy = new ClientProxy(client);

        UserService userService = clientProxy.getProxy(UserService.class);
        User user = userService.selectUserById(1);
        int res = userService.insertUser(User.builder().id(2).name("xx").sex(true).build());
        System.out.println("user :" + user);
        System.out.println("insert user res:" + res);

        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blog = blogService.selectBlogById(1);
        Integer res2 = blogService.insertBlog(Blog.builder().author("xx").content("content").title("titile").id(2).build());
        System.out.println("blog :" + blog);
        System.out.println("insert blog res:" + res2);

    }
}
