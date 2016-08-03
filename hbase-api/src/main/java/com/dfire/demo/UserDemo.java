package com.dfire.demo;

import com.dfire.base.BaseCRUD;
import com.dfire.model.UserModel;

import java.io.IOException;

/**
 * Created by qqr on 16/8/2.
 */
public class UserDemo {
    public static void main(String[] args) {
        UserModel model = UserModel.getDemoModel();

    }

    private void createTable() throws IOException {
        String tableName="userInfo";
        String[] families = {"user"};
        String[] families = {"user"};

        BaseCRUD.create("namespace1", tableName, families);

    }
}
