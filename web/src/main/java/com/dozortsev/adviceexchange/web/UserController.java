package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.QuestionService;
import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    private static final Logger log = getLogger(UserController.class);

    @RequestMapping(value="/welcome", method = GET)
    public String printWelcome(Model model, Principal principal ) {

        User user = userService.findUserByLogin(principal.getName());
        model.addAttribute("user", user);

        return "index";
    }

    @RequestMapping(value = "/questions", method = GET)
    public ModelAndView findQuestions(@RequestParam String tags) {

        ArrayList<Long> longs = new ArrayList<>();
        for (String id : tags.split(" ")) {
            longs.add(Long.valueOf(id));
        }

        Set<Question> questions = questionService.findQuestionsByTagsId(longs.toArray(new Long[]{}));

        return new ModelAndView("index", "questions", questions);
    }

    @RequestMapping(value = "/createAccount", method = POST)
    public ModelAndView createAccount(@ModelAttribute("newUser") User user) {

        return new ModelAndView("index", "user", userService.findById(userService.create(user)));
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
