package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.service.QuestionService;
import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes({ "user", "userCount" })
public class UserController {

    private @Autowired UserService userService;

    private @Autowired QuestionService questionService;

    private static final Logger log = getLogger(UserController.class);

    @RequestMapping(value = "/users/login", method = GET)
    public ModelAndView logIn(Principal principal) {

        return new ModelAndView("redirect:/questions", "user", userService.findByLogin(principal.getName()));
    }

    @RequestMapping(value = "/users", method = GET)
    public ModelAndView users(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer page) {

        boolean isNameValid = name != null && name.length() > 0;

        return new ModelAndView("users", "name", name)
                .addObject(
                        "userCount", isNameValid ? userService.totalCount(name) : userService.totalCount()
                )
                .addObject(
                        "userSet", userService.findByName(isNameValid ? name : "", page != null ? (page - 1) * 36 : 0
                )
        );
    }

    @RequestMapping(value = "/user/{id}", method = GET)
    public ModelAndView user(@PathVariable Long id) {

        return new ModelAndView("user", "member", userService.findById(id))
                .addObject("activity", userService.userActivities(id));
    }

    @RequestMapping(value = "/search", method = GET)
    public ModelAndView findQuestions(@RequestParam String tags) {

        Set<Question> questions = questionService.findByTags(tags.split(" "));

        return new ModelAndView("index", "questions", questions);
    }
}
