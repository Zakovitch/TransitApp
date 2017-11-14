package com.zakovitch.bemyguide;

import android.os.health.ServiceHealthStats;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.zakovitch.backendmanager.DataManager;
import com.zakovitch.backendmanager.event.OnParseDataError;
import com.zakovitch.backendmanager.event.OnParseDataSucceeded;
import com.zakovitch.backendmanager.model.Response;
import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.backendmanager.model.enums.TravelMode;
import com.zakovitch.bemyguide.adapter.RouteAdapter;
import com.zakovitch.bemyguide.interfaces.IRoute;
import com.zakovitch.bemyguide.utils.TravelModeViewUtils;
import com.zakovitch.bemyguide.utils.ViewUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IRoute{

    final String TAG = "MainActivity";

    //Json response after parsing
    public Response response;


    @ViewById(R.id.route_list)
    RecyclerView routeRecycleView;
    RouteAdapter adapter;
    LinearLayoutManager linearLayoutManager;


    //initialize the UI
    @AfterViews
    void initUI(){

        //init RecycleView params
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        routeRecycleView.setLayoutManager(linearLayoutManager);
        routeRecycleView.setHasFixedSize(true);
        getResponse();
    }

    @Background
    void getResponse(){
        //ToDo start loading view
        Log.d(TAG,"getResponse called");
        DataManager.getResponse(this);
    }

    /**
     * Called when respose parsing succeeded
     * @param event
     */
    @Subscribe
    public void onEventMainThread(OnParseDataSucceeded event) {
        //ToDo stop loading view
        Log.d(TAG,"OnParseDataSucceeded called");
        response = event.getResponse();
        updateList();
    }

    @Subscribe
    public void onEventMainThread(OnParseDataError event){
        //ToDo stop loading view
        //ToDo show error message
        Log.d(TAG,"OnParseDataError called");
    }

    /**
     * Update the main list
     */
    @UiThread
    public void updateList(){
        Log.d(TAG,"updateList called");
        if(response.getRoutes().size()>0){
            adapter = new RouteAdapter(response.getRoutes(),this,this);
            routeRecycleView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
        }
    }



    /*
        EventBus register and unregister
     */
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * When the item of route is clicked
     */
    @Override
    public void onRouteClicked(View rootView, int position) {
        Log.d(TAG,"onRouteClicked =>"+position);
        ArrayList<Segment> currentSegment = response.getRoutes().get(position).getSegments();

        //for test
        //View walkingView = TravelModeViewUtils.getTravelModeView(currentSegment.get(0),false,getApplicationContext());
        //View busView = TravelModeViewUtils.getTravelModeView(currentSegment.get(1),true,getApplicationContext());

        LinearLayout detailsRootView = rootView.findViewById(R.id.route_details_panel);

        //detailsRootView.addView(walkingView);
        //.addView(busView);

        for (int i=0; i<currentSegment.size();i++) {

            View view = TravelModeViewUtils.getTravelModeView(currentSegment.get(i),i==(currentSegment.size()-1),getApplicationContext());
            detailsRootView.addView(view);
        }
        //Visible
        detailsRootView.setVisibility(View.VISIBLE);

    }

    /**
     * When the button FAB of map is clicked show the map
     */
    @Override
    public void onMapClicked(int position) {
        Log.d(TAG,"Map Clicked "+position);
    }
}
