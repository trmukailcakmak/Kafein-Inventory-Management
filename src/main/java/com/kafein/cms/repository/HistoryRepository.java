package com.kafein.cms.repository;

import com.kafein.cms.model.entity.Catalog;
import com.kafein.cms.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}
