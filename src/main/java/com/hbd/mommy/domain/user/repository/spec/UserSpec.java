package com.hbd.mommy.domain.user.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.hbd.mommy.domain.user.model.entity.User;

public class UserSpec {

    public static Specification<User> withUsernameOrNickname(String name) {
        if (name == null) {
            return Specification.where(null);
        }

        String pattern = "%" + name + "%";
        return (root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), pattern),
                        builder.like(root.get("nickname"), pattern)
                );
    }
}
