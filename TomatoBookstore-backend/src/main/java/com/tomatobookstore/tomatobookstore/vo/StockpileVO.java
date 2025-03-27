package com.tomatobookstore.tomatobookstore.vo;

import com.tomatobookstore.tomatobookstore.po.Stockpile;
import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockpileVO {
    @Nullable
    private String id;
    @Nullable
    private String productId;
    private Integer amount;
    @Nullable
    private Integer frozen;

    public Stockpile toPO() {
        Stockpile stockpile=new Stockpile();
        stockpile.setStockpileId(Integer.parseInt(id));
        stockpile.setProductId(Integer.valueOf(productId));
        stockpile.setAmount(amount);
        stockpile.setFrozen(frozen);
        return stockpile;
    }
}
