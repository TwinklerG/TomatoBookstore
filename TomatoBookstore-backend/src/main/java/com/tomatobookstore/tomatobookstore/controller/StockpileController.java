package com.tomatobookstore.tomatobookstore.controller;

import com.tomatobookstore.tomatobookstore.service.StockpileService;
import com.tomatobookstore.tomatobookstore.vo.MessageVO;
import com.tomatobookstore.tomatobookstore.vo.ResultVO;
import com.tomatobookstore.tomatobookstore.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products/stockpile")
public class StockpileController {

    @Autowired
    StockpileService stockpileService;

    @PatchMapping("/{productId}")
    public ResultVO<MessageVO> patchStockpile(@PathVariable String productId, @RequestBody StockpileVO stockpileVO) throws Exception {
        return ResultVO.buildSuccess(stockpileService.updateStockpile(productId,stockpileVO));
    }

    @GetMapping("/{productId}")
    public ResultVO<StockpileVO> getStockpile(@PathVariable String productId) throws Exception {
        return ResultVO.buildSuccess(stockpileService.getStockpileById(productId));
    }
}
