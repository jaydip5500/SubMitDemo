package com.techasoft.garmentstores.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class StorageHelper  {


    public static boolean dataVisibility =false;

    public static String Data="";

   public static List<Items> products;


    public static void setBillnoAuto(Context context,int billNO)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("bill_no",billNO);

        editor.commit();

    }

    public static int getBillno(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int billno=01;
        if( preferences.contains("bill_no")){
            billno= preferences.getInt("bill_no",01);
        }
        return billno;
    }
}
