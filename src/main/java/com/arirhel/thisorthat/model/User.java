package com.arirhel.thisorthat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private List<UserRole> userRoles;

}
