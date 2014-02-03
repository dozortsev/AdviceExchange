package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity @Table(name = "badge")
@AttributeOverride(name = "id", column = @Column(name = "bdg_id", unique = true, nullable = false))
public class Badge extends AbstractEntity<Long> {

    @NotEmpty @Length(min = 3, max = 30)
    @Column(name = "bdg_name")
    private String name;

    @Lob @NotEmpty @Size(min = 10, max = 100)
    @Column(name = "bdg_desc")
    private String desc;


    public Badge() { }

    public Badge(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}