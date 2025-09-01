package com.rawfade.ecom.repo;

import com.rawfade.ecom.model.SiteText;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SiteTextRepository extends JpaRepository<SiteText, Long> {
    Optional<SiteText> findByKeyName(String keyName);
}
