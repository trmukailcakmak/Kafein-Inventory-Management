package com.kafein.cms.repository;

import com.kafein.cms.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<List<Warehouse>> findByName(String name);
    Optional<List<Warehouse>> findByNameLikeOrAreaLikeOrCityLikeOrDistrictLike(String name,String area,String city,String district);
}
