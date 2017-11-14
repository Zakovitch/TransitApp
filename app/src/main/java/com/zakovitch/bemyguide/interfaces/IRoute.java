package com.zakovitch.bemyguide.interfaces;

import android.view.View;

/**
 * Created by Zakovitch on 14/11/2017.
 */

public interface IRoute {
    void onRouteClicked(View rootView,int position);
    void onMapClicked(int position);
}
