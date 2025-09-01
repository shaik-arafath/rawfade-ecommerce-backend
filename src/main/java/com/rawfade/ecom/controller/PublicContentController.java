package com.rawfade.ecom.controller;

import com.rawfade.ecom.model.Banner;
import com.rawfade.ecom.model.SiteText;
import com.rawfade.ecom.repo.BannerRepository;
import com.rawfade.ecom.repo.SiteTextRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PublicContentController {
    private final BannerRepository bannerRepo;
    private final SiteTextRepository textRepo;
    public PublicContentController(BannerRepository bannerRepo, SiteTextRepository textRepo){
        this.bannerRepo=bannerRepo; this.textRepo=textRepo;
    }

    @GetMapping("/api/banners")
    public List<Banner> banners(){ return bannerRepo.findAll(); }

    @GetMapping("/api/texts/{key}")
    public SiteText getText(@PathVariable String key){ return textRepo.findByKeyName(key).orElse(null); }
}
