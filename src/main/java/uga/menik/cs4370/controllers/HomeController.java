/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.menik.cs4370.models.Post;
import uga.menik.cs4370.services.PeopleService;
import uga.menik.cs4370.services.PostService;
import uga.menik.cs4370.services.HashtagService;
import uga.menik.cs4370.services.UserService;

/**
 * This controller handles the home page and some of it's sub URLs.
 */
@Controller
@RequestMapping
public class HomeController {

    
    private final PostService postService;
    private final PeopleService peopleService;
    private final UserService userService;
    private final HashtagService hashtagService;


    @Autowired
    public HomeController(PostService postService, UserService userService, PeopleService peopleService, HashtagService hashtagService) {
        this.postService = postService;
        this.userService = userService;
        this.peopleService = peopleService;
        this.hashtagService = hashtagService;
    }

    /**
     * This is the specific function that handles the root URL itself.
     * 
     * Note that this accepts a URL parameter called error.
     * The value to this parameter can be shown to the user as an error message.
     * See notes in HashtagSearchController.java regarding URL parameters.
     */
    @GetMapping
    public ModelAndView webpage(@RequestParam(name = "error", required = false) String error) {
        // See notes on ModelAndView in BookmarksController.java.
        ModelAndView mv = new ModelAndView("home_page");
        List<Post> posts = new ArrayList<>();

        try {
            // Following line populates the post data.
            posts = peopleService.getFollowedUsersPosts(userService.getLoggedInUser().getUserId());
            mv.addObject("posts", posts);
        } catch (Exception e) {
            System.err.println("Some error occured!" + e.getMessage());
            // If an error occured, show the error message to the user.
            String errorMessage = e.getMessage();
            mv.addObject("errorMessage", errorMessage);
        }

        // show no content message if your content list is empty.
        if (posts.isEmpty()) {
            mv.addObject("isNoContent", true);
        } else {
            mv.addObject("isNoContent", false);
        }
        
        return mv;
    }

    /**
     * This function handles the /createpost URL.
     * This handles a post request that is going to be a form submission.
     * The form for this can be found in the home page. The form has a
     * input field with name = posttext. Note that the @RequestParam
     * annotation has the same name. This makes it possible to access the value
     * from the input from the form after it is submitted.
     */
    @PostMapping("/createpost")
    public String createPost(@RequestParam(name = "posttext") String postText) {
        boolean success = postService.createPost(postText, userService.getLoggedInUser());
        
        if (success) {
            hashtagService.hashtagPost(postText);
            // Redirect the user to home page if post creation is successful.
            return "redirect:/";
        } else {
            // Redirect the user with an error message if there was an error.
            String message = URLEncoder.encode("Failed to create the post. Please try again.", StandardCharsets.UTF_8);
            return "redirect:/?error=" + message;
        }
    }
}