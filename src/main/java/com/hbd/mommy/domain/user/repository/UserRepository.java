package com.hbd.mommy.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hbd.mommy.domain.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.id = :id")
	Optional<User> findById(@Param("id") Long id);

	@Query("select u from User u where u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);
}
