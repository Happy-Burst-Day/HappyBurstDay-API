package com.hbd.mommy.domain.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hbd.mommy.domain.food.model.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

	@Query("select f from Food f where f.name like %:keyword%")
	List<Food> findAllByNameKeyword(@Param("keyword") String keyword);
}
