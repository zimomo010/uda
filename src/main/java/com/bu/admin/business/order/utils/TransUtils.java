package com.bu.admin.business.order.utils;


import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * 系统静态辅助方法
 *
 * @author ghostWu
 */
@Slf4j
public class TransUtils {
    private TransUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param transaction
     * @return
     */
    public static void addNegateDealType(Transaction transaction, ProductDealType... productDealTypes) {
        List<String> negateDealTypeList = new ArrayList<>();
        for (ProductDealType productDealType : productDealTypes) {
            negateDealTypeList.add(productDealType.name());
        }
        transaction.setDealTypeNegateList(negateDealTypeList);
    }



}
