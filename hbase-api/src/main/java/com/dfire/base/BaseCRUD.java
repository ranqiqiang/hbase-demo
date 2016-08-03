package com.dfire.base;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qqr on 16/8/2.
 */
public class BaseCRUD {

    private static Connection connection;
    private static Admin admin ;

    static {
        Configuration config = BaseConfig.getConfiguration();
        try {
            connection = ConnectionFactory.createConnection(config);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    

    // 创建一张表
    public static void create(String tableName,String[] families)
            throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        if (admin.tableExists(tableNameObj)){
            System.out.println("table  exist, return!");
            return;
        }

        HTableDescriptor td = new HTableDescriptor(tableNameObj);

        for(int i = 0; i < families.length; i++){
            td.addFamily(new HColumnDescriptor(families[i]));
        }
        admin.createTable(td);
        admin.close();
    }




    // 创建一张表
    public static void create(String namespace,String tableName,String[] families)
            throws IOException {
        List<HBaseProtos.SnapshotDescription> list = admin.listSnapshots(namespace);
        if(list == null || list.size() == 0){
            NamespaceDescriptor  descriptor =  NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(descriptor);
        }else {
            System.out.println("namespace "+namespace+" is exist");
        }
        create(namespace+":"+tableName,families);
    }




    // 添加一条记录
    public static void putMany(String tableName, String[] rows,String[] families,
                           String[] columns, String[] dataList) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = connection.getTable(tableNameObj);

        for(int i =0;i<rows.length;i++){
            String row = rows[i];
            String columnFamily = families[i];
            String column = columns[i];
            String data = dataList[i];
            Put p1 = new Put(Bytes.toBytes(row));
            p1.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
            table.put(p1);
            System.out.println("put'" + row + "'," + columnFamily + ":" + column + "','" + data + "'");
        }
    }

    /**
     * put a row
     * @param tableName
     * @param row
     * @param columnFamily
     * @param column
     * @param data
     * @throws IOException
     */
    public static void put(String tableName, String row,String columnFamily,
                           String column, String data) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = connection.getTable(tableNameObj);
        if (!admin.tableExists(tableNameObj)){
            System.out.println("table not exist, return!");
            return;
        }
        Put p1 = new Put(Bytes.toBytes(row));
        p1.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(p1);
        System.out.println("put'" + row + "'," + columnFamily + ":" + column + "','" + data + "'");
    }


    /**
     * get a row
     * @param tableName
     * @param row
     * @param columnFamily
     * @param column
     * @throws IOException
     */
    public static void getColumn(String tableName, String row,String columnFamily,
                           String column) throws IOException {

        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = connection.getTable(tableNameObj);
        if (!admin.tableExists(tableNameObj)){
            System.out.println("table not exist, return!");
            return;
        }
        Get p1 = new Get(Bytes.toBytes(row));
        p1.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        showCell(table.get(p1));
    }


    public static void getFamily(String tableName, String row,String columnFamily) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = connection.getTable(tableNameObj);
        if (!admin.tableExists(tableNameObj)){
            System.out.println("table not exist, return!");
            return;
        }
        Get p1 = new Get(Bytes.toBytes(row));
        p1.addFamily(Bytes.toBytes(columnFamily));

        Result result = table.get(p1);
        showCell(result);
    }


    public static void showCell(Result result){
        Cell[] cells = result.rawCells();
        for(Cell cell:cells){
            System.out.println("RowName:"+new String(CellUtil.cloneRow(cell)));
            System.out.println("TimeTamp:"+cell.getTimestamp());
            System.out.println("column Family:"+new String(CellUtil.cloneFamily(cell)));
            System.out.println("row Name:"+new String(CellUtil.cloneQualifier(cell)));
            System.out.println("value:"+new String(CellUtil.cloneValue(cell)));
        }
    }

}
