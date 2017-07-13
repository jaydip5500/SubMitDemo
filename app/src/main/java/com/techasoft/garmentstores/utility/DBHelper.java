package com.techasoft.garmentstores.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "billManager";

    // Table Names
    private static final String TABLE_BILLSLIST = "blist";
    private static final String TABLE_BILLITEMS = "bitems";


    private static final String Bill_ID = "id";
    private static final String BILL_NO = "bill_no";
    private static final String BILL_DATE = "bill_date";
    private static final String BILL_AMOUNT = "bill_amount";



    public static final String ITEM_ID="item_id";
    public static final String ITEM_NAME="item_name";
    public static final String ITEM_PRICE="item_price";
    public static final String ITEM_QUANTITY="item_quantity";
    public static final String ITEM_TOTAL_AMOUNT="item_total_amount";



    private static final String CREATE_TABLE_BILLSLIST = "CREATE TABLE "
            + TABLE_BILLITEMS + "(" + Bill_ID + " INTEGER PRIMARY KEY NOT NULL," + BILL_NO
            + " INTEGER," + BILL_DATE + " VARCHAR,"+ BILL_AMOUNT+  "VARCHAR;" +")";


    private static final String CREATE_TABLE_BILLITEMS = "CREATE TABLE "
            + TABLE_BILLSLIST + "(" + ITEM_ID + " INTEGER PRIMARY KEY NOT NULL," + BILL_NO
            + " INTEGER," + ITEM_NAME + " VARCHAR,"+ ITEM_PRICE + " VARCHAR,"+ ITEM_QUANTITY + " VARCHAR,"+ ITEM_TOTAL_AMOUNT + " VARCHAR;"  +")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_BILLSLIST + " (" +
                Bill_ID + " INTEGER PRIMARY KEY, " +
                BILL_NO + " TEXT NOT NULL, " +
                BILL_DATE + " TEXT NOT NULL, " +
                BILL_AMOUNT + " TEXT NOT NULL);"
        );

        db.execSQL(" CREATE TABLE " + TABLE_BILLITEMS + " (" +
                ITEM_ID + " INTEGER PRIMARY KEY, " +
                BILL_NO + " TEXT, " +
                ITEM_NAME + " TEXT NOT NULL, " +
                ITEM_PRICE + " TEXT NOT NULL, " +
                ITEM_QUANTITY + " TEXT NOT NULL, " +
                ITEM_TOTAL_AMOUNT + " TEXT NOT NULL);"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILLSLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILLITEMS);

        onCreate(db);

    }

    public void insertBillslistData(String billno,String billDate,String billAmount){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_no",billno);
        contentValues.put("bill_date",billDate);
        contentValues.put("bill_amount",billAmount);
        db.insert("blist", null, contentValues);


    }

    public void insertBillslistItem(String billno,String itemName,String itemPrice,String itemQuantity,String itemAmount){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bill_no",billno);
        contentValues.put("item_name",itemName);
        contentValues.put("item_price",itemPrice);
        contentValues.put("item_quantity",itemQuantity);
        contentValues.put("item_total_amount",itemAmount);
        db.insert("bitems", null, contentValues);


    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bitems where bill_no="+id+"", null );
        return res;
    }

    public ArrayList<String> getitemNAme(int id) {
        ArrayList<String> array_itemNAme = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from bitems where bill_no="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_itemNAme.add(res.getString(res.getColumnIndex(ITEM_NAME)));
            res.moveToNext();
        }
        return array_itemNAme;
    }
    public ArrayList<String> getitemprice(int id) {
        ArrayList<String> array_itemPrice = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from bitems where bill_no="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_itemPrice.add(res.getString(res.getColumnIndex(ITEM_PRICE)));
            res.moveToNext();
        }
        return array_itemPrice;
    }
    public ArrayList<String> getitemquan(int id) {
        ArrayList<String> array_itemQuant = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from bitems where bill_no="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_itemQuant.add(res.getString(res.getColumnIndex(ITEM_QUANTITY)));
            res.moveToNext();
        }
        return array_itemQuant;
    }
    public ArrayList<String> getitemamount(int id) {
        ArrayList<String> array_itemamAmount = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from bitems where bill_no="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_itemamAmount.add(res.getString(res.getColumnIndex(ITEM_TOTAL_AMOUNT)));
            res.moveToNext();
        }
        return array_itemamAmount;
    }

    public ArrayList<String> getAllBillsNo() {
        ArrayList<String> array_billno = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from blist", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_billno.add(res.getString(res.getColumnIndex(BILL_NO)));
            res.moveToNext();
        }
        return array_billno;
    }

    public ArrayList<String> getAllBillsDate() {
        ArrayList<String> array_billdate = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from blist", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_billdate.add(res.getString(res.getColumnIndex(BILL_DATE)));
            res.moveToNext();
        }
        return array_billdate;
    }

    public ArrayList<String> getAllBillsAmount() {
        ArrayList<String> array_billamount = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from blist", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_billamount.add(res.getString(res.getColumnIndex(BILL_AMOUNT)));
            res.moveToNext();
        }
        return array_billamount;
    }
}
