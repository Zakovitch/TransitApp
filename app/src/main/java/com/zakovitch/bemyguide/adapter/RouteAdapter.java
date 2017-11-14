package com.zakovitch.bemyguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zakovitch.backendmanager.model.Route;
import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.backendmanager.utils.TimeUtils;
import com.zakovitch.bemyguide.R;
import com.zakovitch.bemyguide.adapter.holder.RouteViewHolder;
import com.zakovitch.bemyguide.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakovitch on 13/11/2017.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteViewHolder> {

    final static String TAG = "RouteAdapter";
    List<Route> routes;
    Context context;

    public RouteAdapter(List<Route> routes, Context context) {
        this.routes = routes;
        this.context = context;
    }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {

        Route currentRoute = routes.get(position);
        holder.routeType.setText(currentRoute.getType().toString());
        holder.routePrice.setText(currentRoute.getFullPrice());
        holder.routeTime.setText(TimeUtils.getTimeInString(currentRoute.getRouteTime()));

        Log.d(TAG,"Route "+currentRoute.getType().toString()+ "position "+position);
        Log.d(TAG,"Segment "+routes.get(position).getSegments().size());
        Log.d(TAG,"Childes "+holder.travelModeHolder.getChildCount());
        //if there is no view in linearlayout add them
        ArrayList<Segment> segments =routes.get(position).getSegments();

        holder.travelModeHolder.removeAllViews();

        //Add a travel mode view in the panel
        if(holder.travelModeHolder.getChildCount()==0){
            for(Segment segment : segments) {
                Log.d(TAG,"Segment info route "+currentRoute.getType().toString());
                View cardView = ViewUtils.getTravelModeView(segment,context);
                holder.travelModeHolder.addView(cardView);
                holder.travelModeHolder.invalidate();
            }
        }
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}
