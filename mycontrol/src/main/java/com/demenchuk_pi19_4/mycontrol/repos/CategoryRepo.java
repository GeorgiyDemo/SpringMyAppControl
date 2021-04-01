package com.demenchuk_pi19_4.mycontrol.repos;

import com.demenchuk_pi19_4.mycontrol.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Category repo.
 */
public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {
}