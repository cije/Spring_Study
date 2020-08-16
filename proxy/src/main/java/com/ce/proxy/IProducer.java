package com.ce.proxy;

public interface IProducer {
    /**
     * 销售
     */
    void saleProduct(float money);

    /**
     * 售后
     */
    void afterService(float money);
}
