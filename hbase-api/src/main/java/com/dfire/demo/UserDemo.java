package com.dfire.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dfire.base.BaseCRUD;
import com.dfire.entity.EntityUtil;
import com.dfire.entity.UserEntity;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.Set;

/**
 * Created by qqr on 16/8/2.
 */
public class UserDemo {

    static  String tableName="user";
    static  String namespace = "n1";

    public static void main(String[] args) throws IOException {

//        createTable();

//        BaseCRUD.putList(namespace + ":" + tableName, getPutList(tableName));

        List<Put> putList = getPutList(tableName);
        int i = 0;
        for(Put put : putList){
            while (true){
                BaseCRUD.put(namespace + ":" + tableName,put);
                System.out.println(i++);
            }
        }

//        showResult();


    }

    public static void createTable() throws IOException {
        String[] families = new String[]{tableName};
        BaseCRUD.create(namespace, tableName, families);
    }

    public static List<Put> getPutList(String family){
        JSONObject entity1 = UserEntity.getDemoJson();
        Set<String> fields = EntityUtil.getFieldNames(UserEntity.class);
        JSONArray userList = new JSONArray();
        userList.add(entity1);

        List<Put> putList = new ArrayList<Put>(userList.size());
        for(int i = 0;i<userList.size();i++){
            JSONObject object = userList.getJSONObject(i);
            Put put = new Put(Bytes.toBytes(object.getString("primaryKey")));
            for(String field : fields){
                String val = object.getString(field);
                if(val == null){
                    put.addColumn(Bytes.toBytes(family),Bytes.toBytes(field),null);
                }else {
                    put.addColumn(Bytes.toBytes(family),Bytes.toBytes(field),Bytes.toBytes(val));
                }
            }
            putList.add(put);
        }
        return putList;
    }



    public static void showResult() throws IOException {
        JSONObject entity1 = UserEntity.getDemoJson();
        System.out.println();
        BaseCRUD.getFamily(namespace+":"+tableName, entity1.getString("primaryKey"), tableName);
    }



}
