package com.example.JPAexample.enums;

import lombok.Getter;

@Getter
public enum UserPermissionEnum {
    ALUMNO_READ("alumno:read"),
    ALUMNO_WRITE("alumno:write"),
    CARRERA_READ("carrera:read"),
    CARRERA_WRITE("carrera:write"),
    PERSONA_READ("persona:read"),
    PERSONA_WRITE("persona:write");

    private final String permission;

    UserPermissionEnum(String permission) {
        this.permission = permission;
    }
}
