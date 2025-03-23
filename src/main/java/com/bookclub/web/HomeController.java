/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * This class represents the home controller for the Bookclub web application.
 * It handles requests for the home page, about us page, and contact us page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * 
     * Handles GET requests for the home page.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model)
    {
        return "index";
    }

    /**
     * Handles GET requests for the about us page.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) 
    {
        return "about";
    }

    /**
     * Handles GET requests for the contact us page.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model)
    {
        return "contact";
    }
}
