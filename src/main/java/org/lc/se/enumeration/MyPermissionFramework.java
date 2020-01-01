package org.lc.se.enumeration;

/**
 * @author lc
 */
public enum  MyPermissionFramework {
    /**
     * spring security
     */
    SPRING_SECURITY{
        @Override
        String getInfo() {
            return "spring security";
        }
    },

    /**
     * shiro
     */
    SHIRO {
        @Override
        String getInfo() {
            return "shiro";
        }
    };

    abstract String getInfo();
}
