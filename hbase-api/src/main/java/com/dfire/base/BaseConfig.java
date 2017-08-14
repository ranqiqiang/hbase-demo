package com.dfire.base;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Created by qqr on 16/8/2.
 */
public class BaseConfig {
    public static final String ZK_ADDRESS = "zk1.2dfire-daily.com:2181,zk2.2dfire-daily.com:2181,zk3.2dfire-daily.com:2181";
    public static Configuration getConfiguration() {
        Configuration conf =  HBaseConfiguration.create();
        conf.set("hbase.rootdir", "/hbase1");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("hbase.zookeeper.quorum",ZK_ADDRESS);
//         conf.set("hbase.master", "192.168.90.161:16000");
        conf.set("zookeeper.znode.parent","/hbase1");
        return conf;
    }
}
