package com.atguigu.bofei;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowedAggregationExample {

    public static void main(String[] args) throws Exception {
        // 创建执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 创建数据流
        DataStream<Transaction> transactions = env.fromElements(
                new Transaction("type1", 100.0, System.currentTimeMillis()),
                new Transaction("type2", 200.0, System.currentTimeMillis() + 1000),
                new Transaction("type1", 300.0, System.currentTimeMillis() + 2000)
        );

        // 分配时间戳和水印
        transactions = transactions.assignTimestampsAndWatermarks(
                WatermarkStrategy.<Transaction>forMonotonousTimestamps()
                        .withTimestampAssigner((event, timestamp) -> event.getTimestamp())
        );

        // 应用窗口和聚合函数
        DataStream<Double> result = transactions
                .keyBy(transaction -> transaction.getType())
                .window(TumblingEventTimeWindows.of(Time.minutes(1)))
                .aggregate(new SumAggregator());

        // 打印结果
        result.print();

        // 启动执行
        env.execute("Windowed Aggregation Example");
    }
}

