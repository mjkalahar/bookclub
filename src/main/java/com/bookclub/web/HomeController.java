/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;


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
        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list();

        for(Book book : books) {
            System.out.println(book.toString());
        }

        model.addAttribute("books", books);
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

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

     /**
     * Handles GET requests for the Book page.
     *
     * @param id Path variable containing the id used in URL
     * @param model The model to add attributes to.
     * 
     * @return The name of the view to render.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        String isbn = id;
        System.out.println(id);

        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(isbn);

        System.out.println(book.toString());

        model.addAttribute("book", book);
        return "monthly-books/view";
    }
}
