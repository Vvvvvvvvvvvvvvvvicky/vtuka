package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.RespResult;
import club.vtuka.tuka.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@RestController
public class LoginController {

    @RequestMapping("/api/login")
    public RespResult login(@RequestBody User user){
//        User user = new User();
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);

        String password = user.getPassword();
        password = HtmlUtils.htmlEscape(password);

        if(!(Objects.equals(username,"admin") && Objects.equals("123456",password))){
            System.out.println("进来了");
            return RespResult.error("账号密码错误");
        }else{
            return RespResult.ok("成功");
        }
    }
}
