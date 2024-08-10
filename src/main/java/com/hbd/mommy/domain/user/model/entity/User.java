package com.hbd.mommy.domain.user.model.entity;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.dku.council.domain.user.model.UserStatus;
import com.dku.council.domain.user.service.UserInfoService;
import com.dku.council.global.auth.role.UserRole;
import com.dku.council.global.base.BaseEntity;
import com.hbd.mommy.global.base.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "dku_user",
        indexes = {
                @Index(name = "idx_user_student_id", columnList = "studentId"),
                @Index(name = "idx_user_phone", columnList = "phone"),
                @Index(name = "idx_user_nickname", columnList = "nickname")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column
    private String studentId;

    @NotNull
    private String password;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    @Column(length = 20)
    private String nickname;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    private Integer yearOfAdmission;

    private String academicStatus;

    @NotNull
    @Column
    private String phone;

    @Enumerated(STRING)
    private UserStatus status;

    @Enumerated(STRING)
    private UserRole userRole;

    @Builder
    private User(@NonNull String studentId,
                 @NonNull String password,
                 @NonNull String name,
                 @NonNull Major major,
                 @NonNull String phone,
                 @NonNull String nickname,
                 @NonNull String academicStatus,
                 Integer yearOfAdmission,
                 UserStatus status,
                 UserRole role) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.major = major;
        this.phone = phone;
        this.nickname = nickname;
        this.academicStatus = academicStatus;
        this.yearOfAdmission = yearOfAdmission;
        this.status = status;
        this.userRole = role;
    }

    public String getName() {
        if (!getStatus().isActive()) {
            return DELETED_USER;
        }
        return this.name;
    }

    public String getNickname() {
        if (!getStatus().isActive()) {
            return DELETED_USER;
        }
        return this.nickname;
    }
}
