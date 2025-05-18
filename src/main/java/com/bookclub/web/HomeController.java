/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;


/**
 * This class represents the home controller for the Bookclub web application.
 * It handles requests for the home page, about us page, and contact us page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

     /**
     * Sets the BookOfTheMonthDao dependency.
     * This method is used by Spring for dependency injection.
     *
     * @param bookOfTheMonthDao The BookOfTheMonthDao instance to inject.
     */
    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }
    /**
     * 
     * Handles GET requests for the home page.
     * It determines the current month, fetches the "Book of the Month" entries for that month,
     * retrieves book details from an external API using their ISBNs, and adds these books to the model.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model)
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH) + 1;

        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();

        RestBookDao bookDao = new RestBookDao();

        for(BookOfTheMonth monthlyBook : monthlyBooks) {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.toString().length() - 1);
        List<Book> books = bookDao.list(isbnString);

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

    /**
     * Handles GET requests for "favicon.ico".
     * This method is typically used to prevent 404 errors when browsers request a favicon.
     * It returns an empty response body.
     */
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

     /**
     * Handles GET requests to display details for a specific book, typically a "Book of the Month".
     * The book is identified by its ISBN, which is passed as a path variable.
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
