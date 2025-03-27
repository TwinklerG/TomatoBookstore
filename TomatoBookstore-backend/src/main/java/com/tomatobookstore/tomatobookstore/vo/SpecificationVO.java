package com.tomatobookstore.tomatobookstore.vo;

import com.tomatobookstore.tomatobookstore.po.Specification;
import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecificationVO {
    @Nullable
    private String id;
    private String item;
    private String value;
    @Nullable
    private String productId;

    public Specification toPO(Integer productId) {
        Specification specification=new Specification();
        specification.setItem(item);
        specification.setValue(value);
        specification.setProductId(productId);
        return specification;
    }
}
