package com.dfire;
import java.io.IOException;

import com.dfire.base.BaseCRUD;
import com.dfire.base.BaseConfig;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.hbase.client.*;

import org.apache.hadoop.hbase.HTableDescriptor;

import org.apache.hadoop.hbase.HColumnDescriptor;

import org.apache.hadoop.hbase.TableName;

/**
 * Created by qqr on 16/8/2.
 */
public class Demo1 {



    public static void main(String[] args) throws IOException{
        String tableName="scores";
        createTable();
//        BaseCRUD.put(tableName,"row_c1","grade","a","数据1");
//        BaseCRUD.getFamily(tableName,"row_c1","grade");

    }


    private static void createTable() throws IOException {
        String tableName="scores";
        String[] families = {"grade", "course"};
        BaseCRUD.create("n2",tableName,families);
    }




}
