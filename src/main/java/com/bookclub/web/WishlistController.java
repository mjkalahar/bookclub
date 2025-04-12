/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;

import jakarta.validation.Valid;

/**
 * This class represents the controller for managing wishlist items in the Bookclub web application.
 * It handles requests related to viewing the wishlist and adding new items to the wishlist.
 */
@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    WishlistDao wishlistDao = new MongoWishlistDao();

    /**
     * Setter for the wishlistDao.
     * This method is used for dependency injection of the WishlistDao.
     * @param wishlistDao
     */
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Handles GET requests for displaying the user's wishlist.
     * Retrieves the wishlist items from the wishlist dao and adds them to the model.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render (wishlist/list).
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model)
    {

        List<WishlistItem> wishlist = wishlistDao.list();

        for(WishlistItem wishlistItem : wishlist) {
            System.out.println(wishlistItem.toString());
        }

        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    /**
     * Handles GET requests for displaying the form to add a new item to the wishlist.
     * Adds an empty WishlistItem object to the model.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render (wishlist/new).
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model)
    {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    /**
     * Handles POST requests for adding a new item to the wishlist.
     * Validates the submitted WishlistItem object and redirects to the wishlist view if successful.
     * If validation fails, returns to the form view with error messages.
     * If validation passes, calls the add method from wishlist dao
     *
     * @param wishlistItem  The WishlistItem object to add, populated from the form.
     * @param bindingResult The result of the validation.
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult) {
        System.out.println(wishlistItem.toString());

        System.out.println(bindingResult.getAllErrors());

        if(bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    }
}
