package com.gold.GoldPoc.controller;

import com.gold.GoldPoc.model.User;
import com.gold.GoldPoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Prabhakar Verma
 */
@Controller
public class UserController {

    private static final String pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

    @Autowired
    private UserService userService;

    @PostMapping( "/register")
    public ResponseEntity<Object> userRegister(@RequestBody User user ) {
        LocalDate userdate = LocalDate.parse(user.getDob()).minusDays(0);
        if (LocalDate.now().compareTo(userdate) > 0) {
            Matcher mtch = Pattern.compile(pattern).matcher(user.getPassword());
            if (mtch.matches()) {
                userService.createUser(user);
                return new ResponseEntity<Object>("User Created: " + user.getName(), HttpStatus.CREATED);
            } else {
                userService.createUser(user);
                return new ResponseEntity<Object>("Password atleast 8 digit with special symbol,caps,small and numeric ", HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<Object>("DOB should be less than feuture date: ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping( "/login" )
    public ResponseEntity<Object> userLogin(@RequestBody User user) {
        ResponseEntity<Object> userResponseEntity = null;
        try {
            User usr = userService.userLogin(user);
            if (null != usr) {
                return new ResponseEntity<Object>("Welcome to " + usr.getName(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>("Invalid user ", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userResponseEntity;
    }

    @PutMapping( "/resetpassword/{newpassword}" )
    public ResponseEntity<Object> resetPassword(@RequestBody User user, @PathVariable String newpassword) {
        User response = userService.resetPassword(user, newpassword);
        if (response == null) {
            return new ResponseEntity<Object>("User Name " + user.getName() + " does not exists", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Object>("password successfully reset", HttpStatus.CREATED);
        }
    }

    @PostMapping( "/forgetPassword")
    public ResponseEntity<Object> forgetPassword(@RequestBody User user) {
        User response = userService.forgotPassword(user);
        if (null == response) {
            System.out.println("User Name " + user.getEmail() + " does not exists");
            return new ResponseEntity<Object>("user email :   " + user.getEmail() + "  does not exists", HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("get User password:: " + response);
            return new ResponseEntity<Object>("Password is :  " + response.getPassword(), HttpStatus.OK);
        }

    }

}
