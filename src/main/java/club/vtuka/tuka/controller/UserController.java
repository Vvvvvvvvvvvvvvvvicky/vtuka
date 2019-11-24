package club.vtuka.tuka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(){
    return "/pages/hello";
    }

    @RequestMapping("/userinfo")
    public String getUserInfo(){
        return null;
    }

}
