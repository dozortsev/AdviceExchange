package com.dozortsev.adviceexchange.domain;

public enum Badge {

    ADMIN(1L, "Admin", "One who oversees discussions on an Internet forum"),
    USER(2L, "User", "A person using a generic system");

    private final Long id;
    private final String status;
    private final String desc;

    Badge(Long id, String status, String desc) {
        this.id = id;
        this.status = status;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
