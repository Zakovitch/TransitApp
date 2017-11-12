package com.zakovitch.backendmanager;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.zakovitch.backendmanager.model.Response;
import com.zakovitch.backendmanager.model.properties.CarSharingProperties;

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
            Log.d(TAG,"***************************************");
            Log.d(TAG,"Number of routes "+response.getRoutes().size());
            Log.d(TAG,"Provider "+response.getRoutes().get(3).getProvider());
            Log.d(TAG,"Provider obj"+response.getRoutes().get(3).getProvider(response.getProviders()).getDisclaimer());
            Log.d(TAG,"Type "+response.getRoutes().get(3).getType());
            Log.d(TAG,"Price "+response.getRoutes().get(3).getFullPrice());
            Log.d(TAG,"TravelMode "+response.getRoutes().get(3).getSegments().get(3).getTravelMode());
            Log.d(TAG,"TravelMode "+((CarSharingProperties)response.getRoutes().get(3).getProperties()).getAddress());

        }

    }
}
