//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.webapi.controllers;

import com.example.webapi.exceptions.UserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptionController {
    public HandleExceptionController() {
    }

    @ExceptionHandler({UserException.class})
    public String handleUserException(UserException ex, Model model) {
        model.addAttribute("error", ex);
        return "error";
    }
}
