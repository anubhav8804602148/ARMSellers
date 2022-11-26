package com.arm.seller.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private GrantedAuthority authority;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    private List<Role> roles;

    public Authority() {
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(GrantedAuthority authority) {
        this.authority = authority;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority=" + authority +
                ", roles=" + roles +
                '}';
    }
}
