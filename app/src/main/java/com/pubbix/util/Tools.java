package com.pubbix.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.places.Place;
import com.pubbix.R;
import com.pubbix.data.model.Address;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import timber.log.Timber;

/**
 * Created by AugusteC on 6/4/17.
 */

public class Tools {
    public Tools() {
    }

    public static boolean isNullOrBlank(String s) {
        return (s == null || s.trim().equals(""));
    }

    public static String imageToString(final Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imgBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } else {
            return "";
        }

    }

    public static void showLoginDialogBox(final Context context) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.brick_user_not_login_dialog_box, null);
        final Button loginButton = (Button) mView.findViewById(R.id.logInBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                /*final Intent intent = new Intent(context, MainSigninActivity.class);
                context.startActivity(intent);*/
            }
        });
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e(e);
        }

        return false;
    }

    public static Address getAddress(Geocoder geocoder, Place place) {
        Address pAddress = null;
        try {
            List<android.location.Address> addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
            String address = addresses.get(0).getAddressLine(0); //address
            String country = addresses.get(0).getCountryName();
            String state = addresses.get(0).getSubAdminArea();
            String city = addresses.get(0).getLocality();

            pAddress = new Address(country, state, city, address);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return pAddress;
    }

}
