/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller handling security-related requests like login and logout.
 */
@Controller
public class SecurityController {

    /**
     * Handles GET requests to the /login path.
     * Displays the login page view.
     *
     * @return The name of the login view template ("login").
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    /**
     * Handles GET requests to the /logout path.
     * Performs logout using Spring Security's SecurityContextLogoutHandler.
     * Clears the security context and invalidates the session.
     * Redirects the user to the login page with a logout confirmation parameter.
     *
     * @param request  The HttpServletRequest.
     * @param response The HttpServletResponse.
     * @return A redirect string to the login page.
     */

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout=true";
    }
}
