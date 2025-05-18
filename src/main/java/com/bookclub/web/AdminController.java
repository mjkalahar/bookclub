/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;

import jakarta.validation.Valid;

/**
 * This class represents the controller for managing book of the month in the Bookclub web application.
 * It handles requests related to viewing the book of the month and adding new books of the month
 */
@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    /**
     * Setter for the bookOfTheMonthDao.
     * This method is used for dependency injection of the BookOfTheMonthDao.
     * @param bookOfTheMonthDao
     */
    @Autowired
    private void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    /**
     * Handles GET requests for displaying the books of the month
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render (bookofthemonth/list).
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showBookOfTheMonth(Model model)
    {
        model.addAttribute("books", bookOfTheMonthDao.list("999"));
        return "monthly-books/list";
    }

    /**
     * Handles GET requests for displaying the form to add a new book of the month.
     * Adds an empty BookOfTheMonth object to the model.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render (bookofthemonth/new).
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String bookOfTheMonthForm(Model model)
    {
        model.addAttribute("bookofthemonth", new BookOfTheMonth());
        model.addAttribute("months", getMonths());
        return "monthly-books/new";
    }

    /**
     * Handles POST requests for adding a new book of the month.
     * Validates the submitted BookOfTheMonth object and redirects to the book of the month view if successful.
     * If validation fails, returns to the form view with error messages.
     * If validation passes, calls the add method from bookOfTheMonthDao
     *
     * @param bookOfTheMonth  The BookOfTheMonth object to add, populated from the form.
     * @param bindingResult The result of the validation.
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addBookOfTheMonth(@Valid @ModelAttribute("bookofthemonth") BookOfTheMonth bookOfTheMonth, BindingResult bindingResult, Model model) {
        System.out.println(bookOfTheMonth.toString());

        System.out.println(bindingResult.getAllErrors());

        if(bindingResult.hasErrors()) {
            model.addAttribute("months", getMonths());
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);

        return "redirect:/monthly-books";
    }

    /**
     * Handles POST requests for updating a book of the month
     *
     * @param bookOfTheMonth  The BookOfTheMonth id to update.
     * @param bindingResult The result of the validation.
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "monthly-books/view";
        }

        bookOfTheMonthDao.update(bookOfTheMonth);

        return "redirect:/monthly-books";
    }

    /**
     * Handles GET requests for deleting a book of the month
     *
     * @param id  The BookOfTheMonth id of the item to delete
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {
        bookOfTheMonthDao.remove(id);

        return "redirect:/monthly-books";
    }

    private Map<Integer, String> getMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        
        return months;
    }
}
