package com.future.springcloud.bloom;

import java.util.BitSet;

/**
 * @author wsq
 * @date 2020/9/21  17:04
 *
 * 自定义布隆过滤器
 */
public class MyBloomFilter {
    /**
     * 位数组大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     *位数组，存0或1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 随机哈希函数映射数组，6个哈希函数
     */
    private static final int[] Randoms = {3,13,46,71,91,134};

    /**
     * 存放hash函数类的数组
     */
    private RandomHash[] func = new RandomHash[Randoms.length];

    /**
     * 初始化随机  hash 函数
     */
    public MyBloomFilter(){
        for (int i = 0; i < Randoms.length; i++) {
            func[i] = new RandomHash(DEFAULT_SIZE,Randoms[i]);
        }
    }

    public void add(Object value){
        for(RandomHash f:func){
            bits.set(f.hash(value),true);
        }
    }

    public boolean contains(Object value){
        boolean ret = true;
        for(RandomHash f: func){
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * hash 操作类
     */
    static class RandomHash
    {
        private int cap;
        private int seed;

        public RandomHash(int cap , int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         * @param value
         * @return
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }
}
