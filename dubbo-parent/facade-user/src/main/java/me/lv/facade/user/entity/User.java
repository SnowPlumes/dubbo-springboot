package me.lv.facade.user.entity;

import me.lv.common.entity.BaseEntity;

/**
 * @author lzw
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = -7843181386498556863L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}