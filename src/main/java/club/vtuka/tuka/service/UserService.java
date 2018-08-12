package club.vtuka.tuka.service;

import club.vtuka.tuka.mapper.UserMapper;
import club.vtuka.tuka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vicky on 2018/1/22.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserInfoList(){
        List<User> userList = userMapper.findUserInfoList();
        return userList;
    }
}
