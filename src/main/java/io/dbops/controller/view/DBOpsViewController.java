package io.dbops.controller.view;

import io.dbops.util.DBOpsConstants;
import io.dbops.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class DBOpsViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModelAndView modelAndView;

    //logout
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    protected String logout(final HttpServletRequest req) {
        invalidateSession(req);
        return "redirect:" + req.getContextPath() + "/";
    }

    private void invalidateSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    public void defaultModelAndViewObjects(HttpServletRequest req, String pageTitle) {
        modelAndView.addObject("title", pageTitle);
        modelAndView.addObject("username", SessionUtils.get(req, DBOpsConstants.username));
        modelAndView.addObject("userId", SessionUtils.get(req, DBOpsConstants.userId));
    }
}

