package tqmgpartners.com.chivastv;

import android.app.Application;

import java.util.ArrayList;

import tqmgpartners.com.chivastv.Entities.clsEditorial;

/**
 * Created by user on 17/02/2017.
 */

public class ChivasTv extends Application {

    public String strToken=null;
    public ArrayList<clsEditorial> colEditorials = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }
}