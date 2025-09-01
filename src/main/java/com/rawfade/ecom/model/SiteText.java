package com.rawfade.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name="site_texts", uniqueConstraints = @UniqueConstraint(columnNames = "keyName"))
public class SiteText {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String keyName; // e.g., HOME_HERO_TITLE, FOOTER_COPYRIGHT

    @Column(columnDefinition = "TEXT")
    private String value;

    public Long getId(){ return id; }
    public String getKeyName(){ return keyName; }
    public void setKeyName(String k){ this.keyName=k; }
    public String getValue(){ return value; }
    public void setValue(String v){ this.value=v; }
}

    public void setId(Long id){ this.id = id; }
