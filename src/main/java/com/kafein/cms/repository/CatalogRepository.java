package com.kafein.cms.repository;

import com.kafein.cms.model.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<List<Catalog>> findByName(String name);
    Optional<List<Catalog>> findByNameLike(String name);
}
