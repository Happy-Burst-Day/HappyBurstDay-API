package com.hbd.mommy.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hbd.mommy.domain.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query("select u from User u where u.status = 'ACTIVE' and u.id = :id ")
	Optional<User> findById(@Param("id") Long id);

	@EntityGraph(attributePaths = {"major"})
	@Query("select u from User u where u.status = 'ACTIVE' and u.studentId = :studentId ")
	Optional<User> findByStudentId(@Param("studentId") String studentId);

	@Query("select u from User u where u.status = 'ACTIVE' and u.nickname = :nickname")
	Optional<User> findByNickname(@Param("nickname") String nickname);
}
