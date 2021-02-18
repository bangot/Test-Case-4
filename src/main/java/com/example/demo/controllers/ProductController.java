package com.example.demo.controllers;

import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/products")
@RequestMapping("/products")
public class  ProductController {
    @Autowired
    private ProductService productService;
   @GetMapping("/list")
    public ModelAndView showAllProduct(){
//       return new ModelAndView("product","products",productService.findAll());
     ModelAndView modelAndView=new ModelAndView("index");
     modelAndView.addObject("data",productService.findAll());
     return modelAndView;
   }


   @PostMapping("/save")
    public String save(Product product){
      productService.save(product);
       return "products";
   }


    @GetMapping("/delete")
    public String deleteProduct(long id){
       productService.delete(id);
       return "products";
   }


    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(long id){
       return productService.finById(id);
}
}
