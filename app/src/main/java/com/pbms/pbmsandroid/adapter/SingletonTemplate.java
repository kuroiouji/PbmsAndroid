package com.pbms.pbmsandroid.adapter;

import android.content.Context;

/**
 * Created by computer on 23/4/2561.
 */

public class SingletonTemplate {

    private static SingletonTemplate instance;

    public static SingletonTemplate getInstance() {
        if (instance == null)
            instance = new SingletonTemplate();
        return instance;
    }

    private Context mContext;

    private SingletonTemplate() {
        //mContext = Contextor.getInstance().getContext();
    }
}
