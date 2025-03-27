package com.tomatobookstore.tomatobookstore.repository;

import com.tomatobookstore.tomatobookstore.po.Stockpile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockpileRepository extends JpaRepository<Stockpile, Integer> {
    Stockpile findByProductId(Integer productId);
    @Transactional
    void deleteByProductId(Integer productId);
}
