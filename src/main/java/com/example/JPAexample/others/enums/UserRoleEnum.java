package com.example.JPAexample.others.enums;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.JPAexample.others.enums.UserPermissionEnum.*;

@Getter
public enum UserRoleEnum {
    ALUMNO(Sets.newHashSet(ALUMNO_READ, ALUMNO_WRITE)),
    ADMIN(Sets.newHashSet(ALUMNO_READ, ALUMNO_WRITE, PERSONA_READ, PERSONA_WRITE, CARRERA_READ, CARRERA_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(ALUMNO_READ, PERSONA_READ, CARRERA_READ));

    private final Set<UserPermissionEnum> permissions;

    UserRoleEnum(Set<UserPermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
