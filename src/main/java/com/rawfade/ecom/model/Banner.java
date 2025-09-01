package com.rawfade.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name="banners")
public class Banner {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String imageUrl;
    private String ctaText;
    private String ctaLink;
    private boolean active = true;
    private int sortOrder = 0;

    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public void setTitle(String t){ this.title=t; }
    public String getSubtitle(){ return subtitle; }
    public void setSubtitle(String s){ this.subtitle=s; }
    public String getImageUrl(){ return imageUrl; }
    public void setImageUrl(String u){ this.imageUrl=u; }
    public String getCtaText(){ return ctaText; }
    public void setCtaText(String t){ this.ctaText=t; }
    public String getCtaLink(){ return ctaLink; }
    public void setCtaLink(String l){ this.ctaLink=l; }
    public boolean isActive(){ return active; }
    public void setActive(boolean a){ this.active=a; }
    public int getSortOrder(){ return sortOrder; }
    public void setSortOrder(int s){ this.sortOrder=s; }
}

    public void setId(Long id){ this.id = id; }
