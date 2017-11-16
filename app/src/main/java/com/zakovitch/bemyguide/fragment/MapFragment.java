package com.zakovitch.bemyguide.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.bemyguide.R;
import com.zakovitch.bemyguide.utils.ViewUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakovitch on 15/11/2017.
 */

public class MapFragment extends BottomSheetDialogFragment implements OnMapReadyCallback{

    static final String TAG = "MapFragment";

    private GoogleMap mMap;

    private ArrayList<Segment> segments;

    private LinearLayout panel;

    private TextView routeType;



    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {


            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

            if(newState == BottomSheetBehavior.STATE_EXPANDED){

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

        //init route panel that contains all type of routes
        panel = contentView.findViewById(R.id.routes_panel);
        initRoutePanel();

        //init the mapfragment
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map_panel);
        mapFragment.getMapAsync(this);


    }


    /**
     * OnMapReady called when the map is loaded
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG,"onMapReady");
        mMap = googleMap;

        //Draw polylines
        for (Segment seg: segments) {
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.511305, 13.40235), 12));

    }


    /**
     * Create different views that represent the type of travel Bus,Walking,...
     * and put them in the header of the fragment
     */
    public void initRoutePanel(){

        for (final Segment seg : segments){
            View segView = ViewUtils.getTravelModeView(seg,getContext());
            if(segView!=null) {
                panel.addView(segView);
                segView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Zoom the map to the first stop
                        moveMap(new LatLng(seg.getStops().get(0).getLat(),seg.getStops().get(0).getLng()));
                    }
                });
            }
        }
    }


    /**
     * Animate the camera for the map to latLng
     */
    private void moveMap(LatLng latLng){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        /**
         * When the fragment is being destroyed we remove the current
         * map_fragment SupportMapFragment from the Fragment manager
         * to avoid the same map_fragment in the fragment manager
         */
        SupportMapFragment f = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map_panel);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }



    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

}
