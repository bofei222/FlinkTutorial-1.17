package com.atguigu.bofei;

import org.apache.flink.api.common.functions.AggregateFunction;

public class SumAggregator implements AggregateFunction<Transaction, Double, Double> {

    // 创建一个新的累加器
    @Override
    public Double createAccumulator() {
        return 0.0;
    }

    // 将一个新值添加到累加器中
    @Override
    public Double add(Transaction value, Double accumulator) {
        return accumulator + value.getAmount();
    }

    // 获取结果
    @Override
    public Double getResult(Double accumulator) {
        return accumulator;
    }

    // 合并两个累加器
    @Override
    public Double merge(Double a, Double b) {
        return a + b;
    }
}
