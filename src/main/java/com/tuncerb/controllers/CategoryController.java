package com.tuncerb.controllers;

import com.tuncerb.repositories.ProductRepository;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by tuncer on 24/05/2018.
 */
@Controller
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{categoryId}/product")
    public String listIngredients(@PathVariable String categoryId, Model model){
        model.addAttribute("categories", categoryService.getCategories());

        //o kategori
        model.addAttribute("category", categoryService.findById(Long.valueOf(categoryId)));

        //TODO Categorinin ürünlerinin gelmesi lazım
        //model.addAttribute("products", productService.getProducts());
        //model.addAttribute("products", productService.findProducts(Long.valueOf(recipeId)));

        return "category/productlist";
    }
}
