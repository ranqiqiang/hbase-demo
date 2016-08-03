package com.dfire.model;

/**
 * User table
 * Created by qqr on 16/8/2.
 */
public class UserModel {

    private String uuid;

    private String name;

    private String passWord;

    private String address;

    private String phone;

    public UserModel() {
    }

    public UserModel(String uuid, String name, String passWord, String address, String phone) {
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


    public static UserModel getDemoModel(){
        UserModel model = new UserModel();
        model.setUuid("0000158353177222015317840eb30027");
        model.setAddress("Hang Zou,China");
        model.setPassWord("123456");
        model.setPhone("18821272222");
        model.setName("Michael Ran");
        return model;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
