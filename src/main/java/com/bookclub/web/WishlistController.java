/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
     *
     * @return The name of the view to render (wishlist/list).
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist()
    {
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
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        wishlistItem.setUsername(authentication.getName());
        System.out.println(wishlistItem.toString());

        System.out.println(bindingResult.getAllErrors());

        if(bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    }

    /**
     * Handles GET requests for getting a wishlist item by id
     *
     * @param id  The WishlistItem id to retrieve.
     * @param model The model to add attributes to.
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);

        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
    }

    /**
     * Handles POST requests for updating a wishlist item
     *
     * @param wishlistItem  The WishlistItem id to update.
     * @param bindingResult The result of the validation.
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        wishlistItem.setUsername(authentication.getName());

        if(bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    /**
     * Handles GET requests for deleting a wishlist item
     *
     * @param id  The WishlistItem id of the item to delete
     * @return The name of the view to render or a redirect URL.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }
}
