package com.tuncerb.bootstrap;

import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import com.tuncerb.repositories.CategoryRepository;
import com.tuncerb.repositories.ProductRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuncer on 26/05/2018.
 */
@Component
public class DatabaseBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DatabaseBootstrap(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Category> categories = new ArrayList<>();
        categories.addAll(createCategories());

        categoryRepository.saveAll(categories);
        this.addProducts(categories);
    }

    private List<Category> createCategories() {

        List<Category> categories = new ArrayList<>();

        Category clothingCategory =new Category();
        clothingCategory.setName("Clothing");
        categories.add(clothingCategory);

        Category bagCategory =new Category();
        bagCategory.setName("Bag");
        categories.add(bagCategory);

        Category category3=new Category();
        category3.setName("Shoe");
        categories.add(category3);

        Category accessoryCategory =new Category();
        accessoryCategory.setName("Accessory");
        categories.add(accessoryCategory);

        return categories;
    }

    private void addProducts(List<Category> categories){
        List<Product> products = new ArrayList<>();

        categories.forEach(category -> {
            for (int i = 1; i < 7; i++) {
                Product product = new Product();
                product.setName("Product " + category.getId() * i);
                product.setDescription( category.getName() + " category product");
                product.setOriginalUrl("https://www.ebay.com/");
                product.setImageUrl("https://i.ebayimg.com/images/g/cjAAAOSw3h1ZSLzx/s-l500.jpg");
                product.setCategory(category);
                products.add(product);
            }
        });

        productRepository.saveAll(products);
    }
}
