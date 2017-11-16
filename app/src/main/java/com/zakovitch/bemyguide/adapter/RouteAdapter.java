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
import com.zakovitch.bemyguide.interfaces.IRoute;
import com.zakovitch.bemyguide.utils.StringUtils;
import com.zakovitch.bemyguide.utils.ViewUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakovitch on 13/11/2017.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteViewHolder> {

    final static String TAG = "RouteAdapter";

    List<Route> routes;
    Context context;

    //Interface to handle the list item click
    IRoute iRoute;

    public RouteAdapter(List<Route> routes,IRoute iRoute ,Context context) {
        this.routes = routes;
        this.context = context;
        this.iRoute = iRoute;
    }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RouteViewHolder holder, final int position) {

        Route currentRoute = routes.get(position);
        holder.routeType.setText(StringUtils.getRouteTypeString(currentRoute.getType(),context));
        holder.routePrice.setText(currentRoute.getFullPrice());
        holder.routeTime.setText(TimeUtils.getTimeInString(currentRoute.getRouteTime()));

        if(currentRoute.getProvider()!=null){
            holder.powredBy.setVisibility(View.VISIBLE);
            String provider =currentRoute.getProvider().substring(0,1).toUpperCase() + currentRoute.getProvider().substring(1);
            holder.powredBy.setText("Powered by: " + provider);
        }else holder.powredBy.setVisibility(View.GONE);

        //if there is no view in linearlayout add them
        ArrayList<Segment> segments =routes.get(position).getSegments();

        //remove all views in the travelModeHolder to avoid the repetition of the view inside the panel
        holder.travelModeHolder.removeAllViews();

        //Add a travel mode view in the panel
        if(holder.travelModeHolder.getChildCount()==0){
            for(Segment segment : segments) {
                View cardView = ViewUtils.getTravelModeView(segment,context);
                holder.travelModeHolder.addView(cardView);
            }
        }

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo add animations (fadein & fadeout)
                if(holder.routeDetails.getVisibility() ==View.VISIBLE) {

                    hideViews(holder);

                }else {
                    holder.mapFab.show();
                    holder.divider.setVisibility(View.VISIBLE);
                    iRoute.onRouteClicked(holder.routeDetails, position);
                    holder.expandablePanel.expand(true);
                }
            }
        });

        //when the fab map icon is clicked
        holder.mapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRoute.onMapClicked(position);
            }
        });

    }


    /**
     * Hide the expandable panel that host the timeline
     *
     * @param holder
     */
    private void hideViews(final RouteViewHolder holder){
        //hide FAB & divider
        holder.mapFab.hide();
        holder.divider.setVisibility(View.GONE);

        holder.expandablePanel.collapse(true);
        holder.expandablePanel.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if (state== ExpandableLayout.State.COLLAPSED){

                    holder.routeDetails.setVisibility(View.GONE);
                    holder.routeDetails.removeAllViews();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    @Override
    public void onViewDetachedFromWindow(RouteViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        hideViews(holder);
    }
}
