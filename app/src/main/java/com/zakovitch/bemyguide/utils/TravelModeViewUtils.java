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

    public static View getTravelModeView(Segment segment,boolean isFirst ,Context context){

        switch (segment.getTravelMode()){
            case WALKING:
                return getWalkingView(segment,isFirst,context);
        }

        return null;

    }


    /**
     * Return walking view
     * @param segment current Segment
     * @param isFirst if is the first segment
     * @param context
     * @return
     */
    private static View getWalkingView(Segment segment,boolean isFirst,Context context) {
        LinearLayout walkingView = (LinearLayout) inflateView(context,R.layout.walk_mode_details_view);

        if(walkingView!=null){

            String time = (segment.getStops().get(0)!=null) ? TimeUtils.getTime(segment.getStops().get(0).getDatetime()) : "N/A";
            String startDestination = (segment.getStops().get(0).getName()!=null) ? segment.getStops().get(0).getName() : "Start";

            //Update info
            TextView timeView = walkingView.findViewById(R.id.lbl_time);
            TextView startDestinationView = walkingView.findViewById(R.id.lbl_start_place);
            TextView walkingTimeView = walkingView.findViewById(R.id.lbl_walking_time);


            timeView.setText(time);
            startDestinationView.setText(startDestination);
            walkingTimeView.setText(StringUtils.getTravelModeString(segment.getTravelMode(),context)+" "+segment.getSegmentTime()+context.getString(R.string.time_minute));

            startDestinationView.setTypeface(startDestinationView.getTypeface(), isFirst ? Typeface.NORMAL : Typeface.BOLD);
            timeView.setTypeface(timeView.getTypeface(), isFirst ? Typeface.NORMAL : Typeface.BOLD);


            startDestinationView.setTextColor(Color.parseColor(segment.getColor()));
            walkingTimeView.setTextColor(Color.parseColor(segment.getColor()));

            //Set Indicator colors
            setColorForIndicators(walkingView,segment.getColor());

            return walkingView;
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

        indicatorBody.setBackgroundColor(Color.parseColor(color));
        indicatorHeader.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }
}
