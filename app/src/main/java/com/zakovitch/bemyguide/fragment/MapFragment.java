package com.zakovitch.bemyguide.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zakovitch.bemyguide.R;

/**
 * Created by Zakovitch on 15/11/2017.
 */

public class MapFragment extends BottomSheetDialogFragment implements OnMapReadyCallback{

    static final String TAG = "MapFragment";

    private GoogleMap mMap;


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            Log.d(TAG,"State "+newState);

            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

            if(newState == BottomSheetBehavior.STATE_EXPANDED){
                //initMap();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //Log.d(TAG,"onSlide slideOffset "+slideOffset);
        }


    };


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);


        View contentView = View.inflate(getContext(), R.layout.map_fragment, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if( behavior != null && behavior instanceof BottomSheetBehavior ) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()// getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map_panel);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG,"onMapReady");
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
        SupportMapFragment f = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map_panel);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }
}
