package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Provider {

    /*
        "provider_icon_url": "https://d3m2tfu2xpiope.cloudfront.net/providers/drivenow.svg",
        "disclaimer": "Our data is as live and real-time as possible.",
        "ios_itunes_url": "https://itunes.apple.com/app/drivenow-carsharing/id435719709?mt=8",
        "ios_app_url": "drivenow://",
        "android_package_name": "com.dn.drivenow",
        "display_name": "Drivenow"
     */

    @Nullable
    @SerializedName("provider_icon_url")
    private String providerIconUrl;

    @Nullable
    @SerializedName("disclaimer")
    private String disclaimer;

    @Nullable
    @SerializedName("ios_itunes_url")
    private String iosItunesUrl;

    @Nullable
    @SerializedName("ios_app_url")
    private String iosAppUrl;

    @Nullable
    @SerializedName("android_package_name")
    private String androidPackageName;

    @Nullable
    @SerializedName("display_name")
    private String displayName;

    @Nullable
    public String getProviderIconUrl() {
        return providerIconUrl;
    }

    public void setProviderIconUrl(@Nullable String providerIconUrl) {
        this.providerIconUrl = providerIconUrl;
    }

    @Nullable
    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(@Nullable String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @Nullable
    public String getIosItunesUrl() {
        return iosItunesUrl;
    }

    public void setIosItunesUrl(@Nullable String iosItunesUrl) {
        this.iosItunesUrl = iosItunesUrl;
    }

    @Nullable
    public String getIosAppUrl() {
        return iosAppUrl;
    }

    public void setIosAppUrl(@Nullable String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
    }

    @Nullable
    public String getAndroidPackageName() {
        return androidPackageName;
    }

    public void setAndroidPackageName(@Nullable String androidPackageName) {
        this.androidPackageName = androidPackageName;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
    }
}
