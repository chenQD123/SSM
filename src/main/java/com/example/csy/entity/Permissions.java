package com.example.csy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissions {
    private Integer id;
    private String permissionName;
    private Integer roleId;
    private Role role;
}
