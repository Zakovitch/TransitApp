package com.zakovitch.bemyguide.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.backendmanager.utils.TimeUtils;
import com.zakovitch.bemyguide.R;

/**
 * Created by Zakovitch on 14/11/2017.
 */

public class TravelModeViewUtils {

    public static View getTravelModeView(Segment segment,boolean isLast ,Context context){

        switch (segment.getTravelMode()){
            case WALKING:
                return getWalkingView(segment,isLast,context);
            case SUBWAY:
            case BUS:
                return getPublicTransportView(segment,isLast,context);
        }

        return null;

    }


    /**
     * Return walking view
     * @param segment current Segment
     * @param isLast if is the first segment
     * @param context
     * @return
     */
    private static View getWalkingView(Segment segment,boolean isLast,Context context) {
        LinearLayout walkingView = (LinearLayout) inflateView(context,R.layout.walk_mode_details_view);

        if(walkingView!=null){

            String time = (segment.getStops().get(0)!=null) ? TimeUtils.getTime(segment.getStops().get(0).getDatetime()) : "N/A";
            String startDestination = (segment.getStops().get(0).getName()!=null) ? segment.getStops().get(0).getName() : "Start";

            //Update info
            TextView timeView = walkingView.findViewById(R.id.lbl_time);
            TextView startDestinationView = walkingView.findViewById(R.id.lbl_start_place);
            TextView endDestinationView = walkingView.findViewById(R.id.lbl_end_place);
            TextView walkingTimeView = walkingView.findViewById(R.id.lbl_walking_time);


            timeView.setText(time);
            startDestinationView.setText(startDestination);
            walkingTimeView.setText(StringUtils.getTravelModeString(segment.getTravelMode(),context)+" "+segment.getSegmentTime()+context.getString(R.string.time_minute));
            if(isLast)
                endDestinationView.setText(segment.getDescription());

            startDestinationView.setTextColor(Color.parseColor(segment.getColor()));
            walkingTimeView.setTextColor(Color.parseColor(segment.getColor()));

            //Set Indicator colors
            setColorForIndicators(walkingView,segment.getColor());

            return walkingView;
        }else return null;

    }


    /**
     * Public transport View creator for Bus & Subway
     * @param segment
     * @param isLast
     * @param context
     * @return
     */
    private static View getPublicTransportView(Segment segment,boolean isLast,Context context) {
        LinearLayout publicTransport = (LinearLayout) inflateView(context,R.layout.bus_subway_mode_details_view);

        if(publicTransport!=null){

            String time = (segment.getStops().get(0)!=null) ? TimeUtils.getTime(segment.getStops().get(0).getDatetime()) : "N/A";
            String startDestination = (segment.getStops().get(0).getName()!=null) ? segment.getStops().get(0).getName() : "Start";

            //Update info
            TextView timeView = publicTransport.findViewById(R.id.lbl_time);
            TextView startDestinationView = publicTransport.findViewById(R.id.lbl_start_place);
            TextView endDestinationView = publicTransport.findViewById(R.id.lbl_end_place);
            TextView stopDuration = publicTransport.findViewById(R.id.lbl_stops_duration);
            TextView stopFromTo = publicTransport.findViewById(R.id.lbl__stops_from_to);



            timeView.setText(time);
            startDestinationView.setText(startDestination);

            String stopsdurationTime = segment.getNumStops() +" Stops "+TimeUtils.getTimeInString(segment.getSegmentTime());
            stopDuration.setText(stopsdurationTime);
            stopDuration.setTextColor(Color.parseColor(segment.getColor()));
            stopFromTo.setText(segment.getName()+" to "+segment.getStops().get(segment.getNumStops()).getName());

            if(isLast)
                endDestinationView.setText(segment.getDescription());

            startDestinationView.setTextColor(Color.parseColor(segment.getColor()));
            stopDuration.setTextColor(Color.parseColor(segment.getColor()));

            //Set Indicator colors
            setColorForIndicators(publicTransport,segment.getColor());

            return publicTransport;
        }else return null;

    }


    /**
     * Inflate layout from Resourse ID
     * @param context
     * @param resId
     * @return
     */
    public static View inflateView(Context context, int resId){
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(resId, null);
    }

    /**
     * Set color for indicator view
     * @param color
     */
    public static void setColorForIndicators(View travelModeView, String color){

        View indicatorHeader = travelModeView.findViewById(R.id.indicator_header);
        View indicatorBody = travelModeView.findViewById(R.id.indicator_body);
        View indicatorBottom = travelModeView.findViewById(R.id.indicator_bottom);

        indicatorBody.setBackgroundColor(Color.parseColor(color));
        indicatorHeader.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
        indicatorBottom.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }
}
