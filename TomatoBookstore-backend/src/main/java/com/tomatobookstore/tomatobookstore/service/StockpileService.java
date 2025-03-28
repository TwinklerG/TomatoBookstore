package com.tomatobookstore.tomatobookstore.service;

import com.tomatobookstore.tomatobookstore.vo.StockpileVO;
import org.springframework.stereotype.Service;

@Service
public interface StockpileService {

    StockpileVO getStockpileById(String productId) throws Exception;

    void updateStockpile(String productId, StockpileVO stockpileVO) throws Exception;

}
