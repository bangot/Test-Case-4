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

    @Value("${upload.path}")
    private String fileUpload;

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


    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        Product product = productService.finById(id);
        productService.delete(product.getId());
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("bang");
        modelAndView.addObject("products", products);
        return modelAndView;
    }


    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(@RequestParam("id") Product product) {
        return productService.finById(product.getId());
    }

    @GetMapping("/bang")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/bang");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }


    @PostMapping("/create_product")
    public ModelAndView saveProduct(@ModelAttribute("products") Product product) {
        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImgSrc(fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("products", new Product());
        modelAndView.addObject("message", "New Product Created Successfully");
        return modelAndView;
    }


}
