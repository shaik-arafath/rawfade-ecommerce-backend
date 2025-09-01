package com.rawfade.ecom.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // ROLE_ADMIN, ROLE_USER

    public Role() {}
    public Role(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Role)) return false;
        Role r=(Role)o;
        return Objects.equals(name, r.name);
    }
    @Override public int hashCode(){ return Objects.hash(name); }
}
