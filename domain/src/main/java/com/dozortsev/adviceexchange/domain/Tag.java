package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity @Table(name = "tag")
@AttributeOverride(name = "id", column = @Column(name = "tag_id", unique = true, nullable = false))
public class Tag extends AbstractEntity<Long> {

    @NotBlank @Size(min = 2, max = 20)
    @Column(name = "tag_name")
    private String name;

    @Lob @NotBlank @Size(min = 10)
    @Column(name = "tag_desc")
    private StringBuilder desc = new StringBuilder(1000);

    public Tag() { }

    public Tag(String name, StringBuilder desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public StringBuilder getDesc() {
        return desc;
    }
    public void setDesc(StringBuilder desc) {
        this.desc = desc;
    }
}
