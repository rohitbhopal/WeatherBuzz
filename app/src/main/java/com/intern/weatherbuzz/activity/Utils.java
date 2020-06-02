package com.intern.weatherbuzz.activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Utils {

    public static String   getCityFromLocation(final double latitude, final double longitude,
                                               final Context context) {
        /*Thread thread = new Thread() {
            @Override
            public void run() {*/
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String result = null;
        Address address=null;
        try {
            List<Address> addressList = geocoder.getFromLocation(
                    latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }

                System.out.println("here"+address.getLocality());
                System.out.println("here"+address.getAdminArea());
                //sb.append(address.getLocality()).append("\n");
                //sb.append(address.getPostalCode()).append("\n");
                //sb.append(address.getCountryName());
                result = sb.toString();
            }
        } catch (IOException e) {
            //Log.e(TAG, "Unable connect to Geocoder", e);
        }
        return address.getAdminArea();


                /*finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n\nAddress:\n" + result;
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();*/
    }
    //};
    // thread.start();


}


