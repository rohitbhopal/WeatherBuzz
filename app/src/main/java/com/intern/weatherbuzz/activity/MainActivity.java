package com.intern.weatherbuzz.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private HashMap<String,ArrayList<String>> countrytoCity =new HashMap<>();
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> city = new ArrayList<>();
    ProgressDialog progressDoalog;
    AppLocationService appLocationService;
    String cityW="";
    Toolbar toolbar;
    ArrayAdapter adapter;
    ListView listView;
    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readCitiesList();
        getLatLon();
    }


    public void readCitiesList () {


        String[] isoCountryCodes = Locale.getISOCountries();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            countryList.add(locale.getDisplayName());
            System.out.println(locale.getDisplayName());
        }

        try {

            for (String countryName : countryList) {

                JSONObject obj = new JSONObject(loadJSONFromAsset());
                countryName = countryName.replace("&", "and");
                JSONArray countryListArray = null;
                if (obj.has(countryName)) {
                    countryListArray = obj.getJSONArray(countryName);

                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < countryListArray.length(); i++) {
                        String cityName = countryListArray.getString(i);
                        Log.d("Details-->", cityName);
                        city.add(cityName);
                        list.add(cityName);

                    }
                    countrytoCity.put(countryName, list);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getLatLon() {
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();

            cityW=Utils.getCityFromLocation(latitude,longitude,MainActivity.this);
        }
    }
}
