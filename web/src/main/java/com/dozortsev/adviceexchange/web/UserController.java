package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = getLogger(UserController.class);

    @RequestMapping(value="/welcome", method = GET)
    public String printWelcome(Model model, Principal principal ) {

        User user = userService.findUserByLogin(principal.getName());
        model.addAttribute("user", user);

        return "index";

    }

    @RequestMapping(value="/login", method = GET)
    public String login(Model model) {

        return "login";

    }

    @RequestMapping(value="/loginfailed", method = GET)
    public String loginerror(Model model) {

        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value="/logout", method = GET)
    public String logout(Model model) {

        return "login";

    }
}
