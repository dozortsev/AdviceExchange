package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.BadgeService;
import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "message" })
public class SignController {

    private @Autowired Md5PasswordEncoder encoder;

    private @Autowired UserService userService;

    private @Autowired BadgeService badgeService;

    private static final Logger log = Logger.getLogger(SignController.class);

    @RequestMapping(value = "/createAccount", method = POST)
    public ModelAndView createAccount(@ModelAttribute User member,
                                      @RequestParam   String email,
                                      @RequestParam   String password)
    {
        if (userService.findUserByLogin(email) != null) {
            return new ModelAndView("redirect:/signup/failed");
        }
        member.setPassword(encoder.encodePassword(password, null));
        member.getBadges().add(badgeService.findById(2L)); // USER badge
        userService.create(member);

        return new ModelAndView("redirect:/login", "message", format(
                "User %s was successful registered! Now you have access", email)
        );
    }

    @RequestMapping(value = "/login/failed", method = GET)
    public String loginFail(Model m) {

        m.addAttribute("message", "We could not find an account for that email/password address.");

        return "redirect:/login";
    }

    @RequestMapping(value = "/signup/failed", method = GET)
    public String signupFail(Model m) {

        m.addAttribute("message", "User with this data already exist. Please try SingUp again");

        return "redirect:/login";
    }
}
