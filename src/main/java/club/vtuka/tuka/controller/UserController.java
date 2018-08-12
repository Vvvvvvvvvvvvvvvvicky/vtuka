package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.User;
import club.vtuka.tuka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Vicky on 2018/1/22.
 */
@Controller
@RequestMapping("/user")
public class UserController {
//    private org.apache.log4j.Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfoList")
    @ResponseBody
    public List<User> getUserInfo(){
        List<User> userList = userService.getUserInfoList();
        if(userList!=null){
            for(User user:userList){
//                System.out.println("user.getUserName(): "+user.getUserName());
            }
        }
        return userList;
    }

    @RequestMapping("/hello")
    public String freemarker(Map<String, Object> map){
        map.put("name", "Joe");
        map.put("sex", 1);    //sex:性别，1：男；0：女；

        // 模拟数据
        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "xbq");
        friend.put("age", 22);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "July");
        friend.put("age", 18);
        friends.add(friend);
        map.put("friends", friends);
        return "hello";
    }
}
