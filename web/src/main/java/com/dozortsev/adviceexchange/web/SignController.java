package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.BadgeService;
import com.dozortsev.adviceexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "message" })
public class SignController {

    @Autowired private Md5PasswordEncoder encoder;

    @Autowired private UserService userService;

    @Autowired private BadgeService badgeService;

    @RequestMapping(value = "/createAccount", method = POST)
    public ModelAndView createAccount(@ModelAttribute User member,
                                      @RequestParam String email,
                                      @RequestParam String password)
    {
        if (userService.findUserByLogin(email) != null) {
            return new ModelAndView("redirect:/signup/failed");
        }
        member.setPassword(encoder.encodePassword(password, null));
        member.getBadges().add(badgeService.findById(2L)); // USER badge
        userService.create(member);

        return new ModelAndView("redirect:/login", "message", "Successful registered! Now please LogIn");
    }

    @RequestMapping(value = "/login/failed", method = GET)
    public String loginFail(Model m) {

        m.addAttribute("message", "Bad login or password");

        return "redirect:/login";
    }

    @RequestMapping(value = "/signup/failed", method = GET)
    public String signupFail(Model m) {

        m.addAttribute("message", "User with this data already exist. Please try SingUp again");

        return "redirect:/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(SessionStatus status) {

        status.setComplete();

        return "redirect:/login";
    }
}
