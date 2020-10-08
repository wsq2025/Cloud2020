package com.future.springcloud.shading;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author wsq
 * @date 2020/10/7  13:41
 *
 * 分片算法
 */
public class UserShardingAlgorithm {
    public static final DatabaseShardingAlgorithm databaseShardingAlgorithm = new DatabaseShardingAlgorithm();
    public static final TableShardingAlgorithm tableShardingAlgorithm = new TableShardingAlgorithm();

    static class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {
            for (String database : databaseNames) {
                if (database.endsWith(String.valueOf(shardingValue.getValue() % 2))) {
                    return database;
                }
            }

            return "";
        }
    }

    static class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
        @Override
        public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> shardingValue) {
            for (String table : tableNames) {
                if (table.endsWith(String.valueOf(shardingValue.getValue() % 2))) {
                    return table;
                }
            }

            return "";
        }
    }
}
