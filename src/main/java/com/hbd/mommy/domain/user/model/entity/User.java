package com.hbd.mommy.domain.user.model.entity;

import static jakarta.persistence.EnumType.*;

import java.time.LocalDate;

import com.hbd.mommy.global.auth.role.UserRole;
import com.hbd.mommy.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "hbd_user",
	indexes = {
		@Index(name = "idx_user_email", columnList = "email")
	})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private LocalDate birthDate;

	@Enumerated(STRING)
	private UserRole role;

	public void changeBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
