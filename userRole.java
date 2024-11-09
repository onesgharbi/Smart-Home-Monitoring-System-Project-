// File: UserRole.java
// Package: com.smarthome.users

package com.smarthome.users;

import com.smarthome.security.Permission;
import java.util.EnumSet;
import java.util.Set;

/**
 * Enum representing roles in the system, each with specific permissions.
 */
public enum UserRole {
    HOMEOWNER(EnumSet.of(Permission.ACCESS_HOMEOWNER_AREA, Permission.ACCESS_SECURITY_AREA, Permission.ACCESS_TECHNICIAN_AREA)),
    SECURITY_GUARD(EnumSet.of(Permission.ACCESS_SECURITY_AREA)),
    TECHNICIAN(EnumSet.of(Permission.ACCESS_TECHNICIAN_AREA));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Gets the permissions associated with the role.
     * @return Set of permissions for the role.
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Checks if the role has a specific permission.
     * @param permission The permission to check.
     * @return True if the role has the permission; otherwise, false.
     */
    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}

