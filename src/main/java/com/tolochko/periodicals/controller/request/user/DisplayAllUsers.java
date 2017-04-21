package com.tolochko.periodicals.controller.request.user;

import com.tolochko.periodicals.controller.request.RequestProcessor;
import com.tolochko.periodicals.model.domain.user.User;
import com.tolochko.periodicals.model.service.UserService;
import com.tolochko.periodicals.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Processes a GET request to a page with a list of all users in the system.
 */
public class DisplayAllUsers implements RequestProcessor{
    private static final Logger logger = Logger.getLogger(DisplayAllUsers.class);
    private static final DisplayAllUsers instance = new DisplayAllUsers();
    private UserService userService = UserServiceImpl.getInstance();

    private DisplayAllUsers() {}

    public static DisplayAllUsers getInstance() {
        return instance;
    }

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        List<User> list = userService.findAll();
        logger.debug("find: "+ list.size()+ " users");
        request.setAttribute("allUsers", userService.findAll());
        return FORWARD + "users/userList";
    }
}