package com.example.demo.controllers;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    public ModelAndView showAllProduct() {
        ModelAndView modelAndView = new ModelAndView("/bang");
       Iterable<Product> product = productService.findAll();
        modelAndView.addObject("products", product);
        return modelAndView;
    }


    @PostMapping("/save")
    public String save(@RequestParam("id") Product product) {
        productService.save(product);
        return "redirect:/";
    }




    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(@RequestParam("id") Product product) {
        return productService.finById(product.getId());
    }







}
