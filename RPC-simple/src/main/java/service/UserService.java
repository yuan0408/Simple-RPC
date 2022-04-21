package service;

import service.common.User;

public interface UserService {

    User selectUserById(Integer id);

    int insertUser(User user);
}
