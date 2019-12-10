package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.RespResult;
import club.vtuka.tuka.model.User;
import club.vtuka.tuka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/api/login")
    public RespResult login(@RequestBody User user){
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);

        String password = user.getPassword();
        password = HtmlUtils.htmlEscape(password);

        User userDb = userService.getUserByUsernameNPassword(username, password);
        if(null == userDb){
            return RespResult.error("账号密码错误或用户不存在");
        }else{
            return RespResult.ok("成功");
        }
    }
}
