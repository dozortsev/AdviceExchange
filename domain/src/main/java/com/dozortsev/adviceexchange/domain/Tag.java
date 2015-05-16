package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

@Entity @Table(name = "tag")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Tag extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Lob @Column(name = "desc")
    private String desc;

    public Tag() { }

    public Tag(String name, String desc) {
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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tag tag = (Tag) o;

        if (!desc.equals(tag.desc)) return false;
        if (!name.equals(tag.name)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
