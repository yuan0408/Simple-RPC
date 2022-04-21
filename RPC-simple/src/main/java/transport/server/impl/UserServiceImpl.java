package transport.server.impl;

import service.UserService;
import service.common.User;

public class UserServiceImpl implements UserService {
    public User selectUserById(Integer id) {
        return User.builder().name("xx").id(id).sex(true).build();
    }

    public int insertUser(User user) {
        return user.getId();
    }
}
