package com.tomatobookstore.tomatobookstore.serviceImpl;

import com.tomatobookstore.tomatobookstore.exception.TomatoBookstoreException;
import com.tomatobookstore.tomatobookstore.po.Stockpile;
import com.tomatobookstore.tomatobookstore.repository.StockpileRepository;
import com.tomatobookstore.tomatobookstore.service.StockpileService;
import com.tomatobookstore.tomatobookstore.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockpileServiceImpl implements StockpileService {
    @Autowired
    StockpileRepository stockpileRepository;

    @Override
    public StockpileVO getStockpileById(String productId) throws Exception {
        try {
            return stockpileRepository.findByProductId(Integer.valueOf(productId)).toVO();
        } catch (Exception e) {
            throw TomatoBookstoreException.productNotExisted();
        }
    }

    @Override
    public void updateStockpile(String productId, StockpileVO stockpileVO) throws Exception {
        try {
            if(stockpileRepository.findByProductId(Integer.valueOf(productId))==null)
                throw TomatoBookstoreException.productNotExisted();
            Stockpile stockpile=stockpileRepository.findByProductId(Integer.valueOf(productId));
            stockpile.setAmount(stockpileVO.getAmount());
            stockpileRepository.save(stockpile);
        } catch (Exception e) {
            throw TomatoBookstoreException.productNotExisted();
        }
    }
}
