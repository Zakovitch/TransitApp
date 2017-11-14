package com.zakovitch.bemyguide.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;
import com.zakovitch.backendmanager.model.Segment;
import com.zakovitch.bemyguide.R;
import com.zakovitch.bemyguide.utils.svg.SvgDecoder;
import com.zakovitch.bemyguide.utils.svg.SvgDrawableTranscoder;
import com.zakovitch.bemyguide.utils.svg.SvgSoftwareLayerSetter;

import java.io.InputStream;


/**
 * Created by Zakovitch on 13/11/2017.
 */

public class ViewUtils {
    static final String TAG = "ViewUtils";
    /**
     * Return a view contain Travel Mode icon and color to inject it into list
     * @param segment
     * @param context
     * @return
     */
    public static View getTravelModeView(Segment segment, Context context){

        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View travelCard = inflater.inflate(R.layout.travel_mode_card_layout, null);
        ImageView imgView = travelCard.findViewById(R.id.travel_mode_img);
        //Card color
        if(segment.getColor()!=null){
            GradientDrawable drawable = (GradientDrawable) travelCard.getBackground();
            drawable.setColor(Color.parseColor(segment.getColor()));
        }

        if(segment.getName()!=null)
            ((TextView)travelCard.findViewById(R.id.travel_mode_text)).setText(segment.getName());

        if(segment.getIconUrl()!=null){
            Log.d(TAG,segment.getIconUrl());
            downloadSVG(context,segment.getIconUrl(),imgView,segment.getColor());

        }


        //LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) travelCard.getLayoutParams();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, convertDpToPx(context,8), 0);
        travelCard.setLayoutParams(params);
        return travelCard;
    }

    /**
     * Dp to px converter
     * @param context
     * @param dp
     * @return
     */
    public static int convertDpToPx(Context context,int dp){
        Resources r = context.getResources();
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );

    }


    public static void downloadSVG(final Context context, String url, final ImageView imageView, final String color){

        GenericRequestBuilder requestBuilder = Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                //.placeholder(R.drawable.)
                //.error(R.drawable.ic_web)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());


        Uri uri = Uri.parse(url);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(new ImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Object resource) {
                        //ToDo change color of SVG
                        Drawable d = (Drawable)resource;
                        //Bitmap bitmap = convertDrawableToBitmap(d);
                        //Bitmap bitmap1 = changeImageColor(bitmap,Color.parseColor("#ffffff"));
                        imageView.setImageDrawable(d);

                    }});

    }



    public static Bitmap changeImageColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }


    public static Drawable covertBitmapToDrawable(Context context, Bitmap bitmap) {
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }

    public static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
