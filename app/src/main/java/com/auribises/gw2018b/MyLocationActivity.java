package com.auribises.gw2018b;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLocationActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    @BindView(R.id.textViewLocation)
    TextView txtLocation;

    @BindView(R.id.buttonFetch)
    Button btnFetch;

    LocationManager locationManager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        ButterKnife.bind(this);

        btnFetch.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Lcoation...");
    }

    @Override
    public void onLocationChanged(Location location) {

        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        txtLocation.setText("Location is: " + latitude + " , " + longitude);

        // No More Location Updates Required
        //locationManager.removeUpdates(this);

        //float speed = location.getSpeed();

        try {
            // Reverse Geocoding
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 2);

            StringBuffer completeAddress = new StringBuffer();

            if(addresses !=null && addresses.size()>0){
                Address address = addresses.get(0);
                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                    completeAddress.append(address.getAddressLine(i)+"\n");
                }

                txtLocation.setText("Location is: " + latitude + " , " + longitude+"\n"+completeAddress.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.buttonFetch) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Please Grant Permissions to access Location in Settings",Toast.LENGTH_LONG).show();
            }else {
                progressDialog.show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, this);
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        locationManager.removeUpdates(this);
    }
}
