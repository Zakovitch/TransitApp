package com.zakovitch.backendmanager;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.zakovitch.backendmanager.event.OnParseDataError;
import com.zakovitch.backendmanager.event.OnParseDataSucceeded;
import com.zakovitch.backendmanager.model.Response;
import com.zakovitch.backendmanager.model.properties.CarSharingProperties;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * Created by Zakovitch on 12/11/2017.
 */

public class DataManager {

    public static String TAG ="TransitApp";

    public static void getResponse(Context context){

        InputStream raw =  context.getResources().openRawResource(R.raw.data);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        Response response = gson.fromJson(rd, Response.class);
        if(response!=null){
            //Post succeed event
            EventBus.getDefault().post(new OnParseDataSucceeded(response));

        }else //Post error event
            EventBus.getDefault().post(new OnParseDataError("error parsing"));

    }
}
