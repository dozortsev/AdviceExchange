package com.dozortsev.adviceexchange.domain;

public class Tag extends AbstractEntity<Long> {

    private Long id;
    private String name;
    private StringBuilder desc = new StringBuilder(1000);
}
