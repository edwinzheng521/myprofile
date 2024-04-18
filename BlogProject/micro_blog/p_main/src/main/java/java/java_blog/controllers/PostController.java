/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.menik.cs4370.models.ExpandedPost;
import uga.menik.cs4370.models.User;
import uga.menik.cs4370.services.BookmarkService;
import uga.menik.cs4370.services.PostService;
import uga.menik.cs4370.services.UserService;

/**
 * Handles /post URL and its sub urls.
 */
@Controller
@RequestMapping("/post")
public class PostController {


    private final UserService userService;
    private final BookmarkService bookmarkService;
    private final PostService postService;

    @Autowired
    public PostController(UserService userService, BookmarkService bookmarkService, PostService postService) {
        this.userService = userService;
        this.bookmarkService = bookmarkService;
        this.postService = postService;
    }

    /**
     * This function handles the /post/{postId} URL.
     * This handlers serves the web page for a specific post.
     * Note there is a path variable {postId}.
     * An example URL handled by this function looks like below:
     * http://localhost:8081/post/1
     * The above URL assigns 1 to postId.
     * 
     * See notes from HomeController.java regardig error URL parameter.
     */
    @GetMapping("/{postId}")
    public ModelAndView webpage(@PathVariable("postId") String postId,
            @RequestParam(name = "error", required = false) String error) {
        System.out.println("The user is attempting to view post with id: " + postId);
        // See notes on ModelAndView in BookmarksController.java.
        ModelAndView mv = new ModelAndView("posts_page");

        try {
            // Following line populates post/comment data.
            ExpandedPost posts = postService.findExpandedPostById(userService.getLoggedInUser().getUserId(), postId); // Use the new method here
            mv.addObject("posts", posts);
        } catch (Exception e) {
            System.err.println("Some error occured!" + e.getMessage());
            // If an error occured, show the error message to the user.
            String errorMessage = e.getMessage();
            mv.addObject("errorMessage", errorMessage);
        }
        
        return mv;
    } // ModelAndView

    /**
     * Handles comments added on posts.
     * See comments on webpage function to see how path variables work here.
     * This function handles form posts.
     * See comments in HomeController.java regarding form submissions.
     */
    @PostMapping("/{postId}/comment")
    public String postComment(@PathVariable("postId") String postId,
            @RequestParam(name = "comment") String comment) {
        System.out.println("The user is attempting add a comment:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tcomment: " + comment);

        if (!userService.isAuthenticated()) {
            // If the user is not logged in, redirect them to the login page
            String message = URLEncoder.encode("Please log in to comment.", StandardCharsets.UTF_8);
            return "redirect:/login?error=" + message;
        }

        User currentUser = userService.getLoggedInUser();
        boolean success = postService.createComment(postId, currentUser.getUserId(), comment);

        if (success) {
            // Redirect the user if the comment adding is a success.
            return "redirect:/post/" + postId;
        } else {
            // Redirect the user with an error message if there was an error.
            String message = URLEncoder.encode("Failed to post the comment. Please try again.", 
                    StandardCharsets.UTF_8);
            return "redirect:/post/" + postId + "?error=" + message;
        } // if

    } // postComment

    /**
     * Handles likes added on posts.
     * See comments on webpage function to see how path variables work here.
     * See comments in PeopleController.java in followUnfollowUser function regarding 
     * get type form submissions and how path variables work.
     */
    @GetMapping("/{postId}/heart/{isAdd}")
    public String addOrRemoveHeart(@PathVariable("postId") String postId,
            @PathVariable("isAdd") Boolean isAdd) {
        System.out.println("The user is attempting add or remove a heart:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tisAdd: " + isAdd);

        String currentId = userService.getLoggedInUser().getUserId();

        if(!isAdd) {
            //attempt to remove an existing heart
            try {
                boolean heartSuccess = postService.rmHeart(currentId, postId);
                if (heartSuccess) {
                    // Redirect the user if the heart is a success.
                    return "redirect:/";
                } else {
                    // If the follow fails for some reason, redirect to people page with a message.
                    String message = URLEncoder
                            .encode("Heart deletion failed. Please try again.", "UTF-8");
                        return "redirect:/post/" + postId + "?error=" + message;
                }
            } catch (Exception e) {
                // If the follow fails for some reason, redirect the user with an error message.
                String message = URLEncoder.encode("Failed to unheart the post. Please try again.",
                StandardCharsets.UTF_8);
                return "redirect:/post/" + postId + "?error=" + message;
            } // try
        } else {
            //attempt to add a new heart
            try {
                boolean heartSuccess = postService.addHeart(currentId, postId);
                if (heartSuccess) {
                    // Redirect the user if the following is a success.
                    return "redirect:/post/" + postId;
                } else {
                    // If the follow fails for some reason, redirect to people page with a message.
                    String message = URLEncoder
                            .encode("Heart registration failed. Please try again.", "UTF-8");
                        return "redirect:/post/" + postId + "?error=" + message;
                }
            } catch (Exception e) {
                // If the follow fails for some reason, redirect the user with an error message.
                String message = URLEncoder.encode("Failed to like the post. Please try again.",
                StandardCharsets.UTF_8);
                return "redirect:/post/" + postId + "?error=" + message;
            } // try
        } // if       
    } // addOrRemoveHeart

    /**
     * Handles bookmarking posts.
     * See comments on webpage function to see how path variables work here.
     * See comments in PeopleController.java in followUnfollowUser function regarding 
     * get type form submissions.
     */
    @GetMapping("/{postId}/bookmark/{isAdd}")
    public String addOrRemoveBookmark(@PathVariable("postId") String postId,
            @PathVariable("isAdd") Boolean isAdd) {
        System.out.println("The user is attempting add or remove a bookmark:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tisAdd: " + isAdd);

        String currentId = userService.getLoggedInUser().getUserId();

        if (isAdd) {
            try {
                boolean bookmarkSuccess = bookmarkService.addBookmark(postId, currentId);
                if (bookmarkSuccess) {
                    // Redirect the user if the bookmark adding is a success.
                    return "redirect:/post/" + postId;                                    
                } else {
                    // Redirect the user with an error message if there was an error.
                    String message = URLEncoder.encode("Failed to bookmark the post. Please try again.",
                            StandardCharsets.UTF_8);
                    return "redirect:/post/" + postId + "?error=" + message;
                }
            } catch (Exception e) {
                // If the bookmark fails for some reason, redirect the user with an error message.
                String message = URLEncoder.encode("Failed to bookmark the post. Please try again.",
                StandardCharsets.UTF_8);
                return "redirect:/post/" + postId + "?error=" + message;
            } // try
            
        } else {
                try { boolean bookmarkSuccess = bookmarkService.removeBookmark(postId, currentId);
                if (bookmarkSuccess) {
                    // Redirect the user if the bookmark removing is a success.
                    return "redirect:/post/" + postId;                                    
                } else {
                    // Redirect the user with an error message if there was an error.
                String message = URLEncoder.encode("Failed to unbookmark the post. Please try again.",
                        StandardCharsets.UTF_8);
                return "redirect:/post/" + postId + "?error=" + message;
                }
            } catch (Exception e) {
                // If the bookmark fails for some reason, redirect the user with an error message.
                String message = URLEncoder.encode("Failed to unbookmark the post. Please try again.",
                StandardCharsets.UTF_8);
                return "redirect:/post/" + postId + "?error=" + message;
            } // try
        }                
    } // addOrRemoveBookmark
}
