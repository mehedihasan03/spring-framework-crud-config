/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Instructor
 */

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String helloWorld(){
        return "index";
    }
    
}
