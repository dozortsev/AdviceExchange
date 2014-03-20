package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

@Entity @Table(name = "badge")
@AttributeOverride(name = "id", column = @Column(name = "bdg_id"))
public class Badge extends AbstractEntity<Long> {

    @Column(name = "bdg_name")
    private String name;

    @Lob @Column(name = "bdg_desc")
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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Badge badge = (Badge) o;

        if (!desc.equals(badge.desc)) return false;
        if (!name.equals(badge.name)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
