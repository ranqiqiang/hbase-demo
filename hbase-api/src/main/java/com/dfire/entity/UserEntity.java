package com.dfire.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.dfire.annocation.ColName;
import com.dfire.annocation.RowKey;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * User table
 * Created by qqr on 16/8/2.
 */
public class UserEntity {


    @RowKey
    public String primaryKey;
    @ColName
    private String uuid;
    @ColName
    private String name;
    @ColName("password")
    private String passWord;
    @ColName
    private String address;
    @ColName
    private String phone;

    public UserEntity() {
    }

    public UserEntity(String uuid, String name, String passWord, String address, String phone) {
        this.uuid = uuid;
        this.name = name;
        this.passWord = passWord;
        this.address = address;
        this.phone = phone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public static UserEntity getDemoModel(){
        UserEntity model = new UserEntity();
        model.setUuid("0000158353177222015317840eb30027");
        model.setAddress("Hang Zou,China");
        model.setPassWord("123456");
        model.setPhone("18821272222");
        model.setName("Michael Ran");
        model.setPrimaryKey(model.getUuid());
        return model;
    }

    public static JSONObject getDemoJson() {
        String str = JSON.toJSONString(getDemoModel());
        return JSONObject.parseObject(str);
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "primaryKey='" + primaryKey + '\'' +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(UserEntity.getDemoJson().getBytes("primaryKey"));
        System.out.println(UserEntity.getDemoJson());
    }
}
