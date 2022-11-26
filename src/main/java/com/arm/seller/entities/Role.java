package com.arm.seller.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Role() {
    }

    public Role(String name, List<Authority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
