package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag extends AbstractEntity<Long> {

    @NotEmpty @Length(min = 2, max = 20)
    @Column(name = "tag_name")
    private String name;

    @Lob @NotEmpty @Length(min = 10, max = 150)
    @Column(name = "tag_decs")
    private StringBuilder desc = new StringBuilder(150);


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
