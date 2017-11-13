package com.zakovitch.bemyguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zakovitch.backendmanager.model.Route;
import com.zakovitch.backendmanager.utils.TimeUtils;
import com.zakovitch.bemyguide.R;
import com.zakovitch.bemyguide.adapter.holder.RouteViewHolder;

import java.util.List;

/**
 * Created by Zakovitch on 13/11/2017.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteViewHolder> {

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




    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}
