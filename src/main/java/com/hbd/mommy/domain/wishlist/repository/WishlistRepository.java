package com.hbd.mommy.domain.wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hbd.mommy.domain.wishlist.model.entity.WishlistItem;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

	@Query("select w from WishlistItem w where w.user.id = :userId")
	List<WishlistItem> findAllByUserId(@Param("userId") Long userId);

	Optional<WishlistItem> findByFoodId(Long foodId);

	Long countByFoodId(Long foodId);

	void deleteByFoodId(Long foodId);

	@Query("SELECT w.food, COUNT(w.food.id) as foodCount " +
		"FROM WishlistItem w " +
		"GROUP BY w.food.id " +
		"ORDER BY foodCount DESC " +
		"LIMIT 3")
	List<WishlistItem> findTop3Foods();
}
