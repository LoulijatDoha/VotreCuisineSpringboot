package com.dohaproject.VotreCuisine.Dao;

import com.dohaproject.VotreCuisine.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
