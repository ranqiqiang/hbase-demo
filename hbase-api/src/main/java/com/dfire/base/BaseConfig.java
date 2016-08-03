package com.dfire.base;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Created by qqr on 16/8/2.
 */
public class BaseConfig {
    public static final String ZK_ADDRESS = "zookeeper1.dihuo.online:2181,zookeeper2.dihuo.online:2181,zookeeper3.dihuo.online:2181";
    public static Configuration getConfiguration() {
        Configuration conf =  HBaseConfiguration.create();
//        conf.set("hbase.rootdir", "hdfs://hadoop1:8020/hbase");
//        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("hbase.zookeeper.quorum",ZK_ADDRESS);
        return conf;
    }
}
