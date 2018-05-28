package com.tuncerb.repositories;

import com.tuncerb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByIdAsc();
}
