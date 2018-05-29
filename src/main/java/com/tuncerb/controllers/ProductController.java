package com.tuncerb.controllers;


import com.tuncerb.commands.ProductCommand;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ImageService;
import com.tuncerb.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by tuncer on 25/05/2018.
 */
@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public ProductController(ProductService productService, CategoryService categoryService, ImageService imageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @RequestMapping("product/new")
    public String getNewProduct(Model model){
        model.addAttribute("product", new ProductCommand());

        model.addAttribute("categories", categoryService.getCategories());

        return "product/form";
    }

    @GetMapping("/product/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("product", productService.findById(new Long(id)));

        return "product/show";
    }

    @GetMapping("product/{id}/update")
    public String updateProduct(@PathVariable String id, Model model){
        model.addAttribute("product", productService.findCommandById(Long.valueOf(id)));
        model.addAttribute("categories", categoryService.getCategories());

        return  "product/form";
    }

    @PostMapping("product")
    public String saveOrUpdate(@Valid @ModelAttribute("product") ProductCommand command, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("categories", categoryService.getCategories());

            return "product/form";
        }

        ProductCommand savedCommand = productService.saveProductCommand(command);
        if (command.getFiles() != null && command.getFiles()[0].getSize() > 0){
            imageService.saveImageFile(savedCommand.getId(), command.getFiles());
        }

        return "redirect:/";
    }

    @GetMapping("product/{productId}/image/{imageId}")
    public String deleteProductImage (@PathVariable String productId,
                                   @PathVariable String imageId){

        imageService.deleteById(Long.valueOf(productId), Long.valueOf(imageId));

        return "redirect:/product/" + productId;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }


}
