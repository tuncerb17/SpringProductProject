package com.tuncerb.controllers;

import com.tuncerb.constants.PaginationConstants;
import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ProductService;
import com.tuncerb.wrappers.PageWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tuncer on 24/05/2018.
 */
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;


    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/category/{categoryId}/product")
    public String listCategoriess(@PathVariable String categoryId,@RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber, Model model){
        model.addAttribute("categories", categoryService.getCategories());

        Category category = categoryService.findById(Long.valueOf(categoryId));

        PageWrapper<Product> products = new PageWrapper<Product>(productService.paginatedCategoryProducts(Long.valueOf(categoryId), pageNumber, PaginationConstants.ITEM_COUNT),"");

        model.addAttribute("products", products);

        model.addAttribute("category", category);

        return "category/productlist";
    }
}
