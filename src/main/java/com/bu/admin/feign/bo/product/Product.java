package com.bu.admin.feign.bo.product;


import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends QueryBase {

    private ProductBasic productBasic;
    private ProductSpecific productSpecific;

    public ProductBasic getProductBasic() {
        return productBasic;
    }

    public void setProductBasic(ProductBasic productBasic) {
        this.productBasic = productBasic;
    }

    public ProductSpecific getProductSpecific() {
        return productSpecific;
    }

    public void setProductSpecific(ProductSpecific productSpecific) {
        this.productSpecific = productSpecific;
    }
}
