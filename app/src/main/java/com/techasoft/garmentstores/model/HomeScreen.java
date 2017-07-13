package com.techasoft.garmentstores.model;

import java.util.ArrayList;

/**
 * Created by JAYDIP PATEL on 13-07-2017.
 */

public class HomeScreen {

    private String billno;
    private String billdate;
    private String total;

    public ArrayList<BillDetail> getBillDetailArrayList() {
        return billDetailArrayList;
    }

    public void setBillDetailArrayList(ArrayList<BillDetail> billDetailArrayList) {
        this.billDetailArrayList = billDetailArrayList;
    }

    private ArrayList<BillDetail> billDetailArrayList;

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
