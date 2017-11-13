package com.zakovitch.bemyguide.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zakovitch.bemyguide.R;

import org.w3c.dom.Text;

/**
 * Created by Zakovitch on 13/11/2017.
 */

public class RouteViewHolder extends RecyclerView.ViewHolder {

    public TextView routeType;
    public TextView routePrice;
    public TextView routeTime;
    public LinearLayout travelModeHolder;

    public RouteViewHolder(View itemView) {
        super(itemView);
        routeType =  itemView.findViewById(R.id.lbl_route_type);
        routePrice =  itemView.findViewById(R.id.lbl_route_price);
        routeTime =  itemView.findViewById(R.id.lbl_route_time);
        travelModeHolder = itemView.findViewById(R.id.routes_panel);
    }
}
