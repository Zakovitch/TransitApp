package com.zakovitch.bemyguide.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.bemyguide.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakovitch on 15/11/2017.
 */

public class MapFragment extends BottomSheetDialogFragment implements OnMapReadyCallback{

    static final String TAG = "MapFragment";

    private GoogleMap mMap;

    private ArrayList<Segment> segment;



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

        //Draw polylines
        for (Segment seg:segment) {
            if(seg.getPolyline()!=null){
                List<LatLng> points = PolyUtil.decode(seg.getPolyline());
                googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .width(30)
                        .geodesic(true)
                        .color(Color.parseColor(seg.getColor()))
                        .addAll(points));

                mMap.addMarker(new MarkerOptions().position(points.get(0)).title(seg.getName()!=null ? seg.getName() : seg.getStops().get(0).getName()));
            }
        }

        //Zoom map to the drawed polyline
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.511305, 13.40235), 12));

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

    public ArrayList<Segment> getSegment() {
        return segment;
    }

    public void setSegment(ArrayList<Segment> segment) {
        this.segment = segment;
    }

}
