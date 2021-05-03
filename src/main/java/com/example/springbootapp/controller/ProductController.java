package com.example.springbootapp.controller;

import com.example.springbootapp.dto.ProductDto;
import com.example.springbootapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute ProductDto productDto) {
        productService.save(productDto);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}
