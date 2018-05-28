package com.tuncerb.controllers;

import com.tuncerb.constants.PaginationConstants;
import com.tuncerb.domain.Product;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ProductService;
import com.tuncerb.wrappers.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tuncer on 24/05/2018.
 */
@Controller
public class IndexController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public IndexController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @RequestMapping({ "", "/" })
    public String getIndexPage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber, Model model){
        model.addAttribute("categories", categoryService.getCategories());
        PageWrapper<Product> products = new PageWrapper<Product> (productService.paginatedProducts(pageNumber, PaginationConstants.ITEM_COUNT),"");

        model.addAttribute("products", products);
        return "index";
    }
}