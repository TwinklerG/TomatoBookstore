package com.tomatobookstore.tomatobookstore.vo;

import com.tomatobookstore.tomatobookstore.po.Product;
import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductVO {
    @Nullable
    private String id;
    private String title;
    private BigDecimal price;
    private Double rate;
    @Nullable
    private String description;
    @Nullable
    private String cover;
    @Nullable
    private String detail;
    @Nullable
    private Set<SpecificationVO> specifications;

    public Product toPO() {
        Product product=new Product();
        if(id!=null)
            product.setProductId(Integer.getInteger(id));
        product.setTitle(title);
        product.setPrice(price);
        product.setRate(rate);
        if(description!=null)
            product.setDescription(description);
        if(cover!=null)
            product.setCover(cover);
        if(detail!=null)
            product.setDetail(detail);
        return product;
    }
}
