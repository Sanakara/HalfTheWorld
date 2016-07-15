package be.sankara.halftheworld.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import database.thing.R;

public class GooglePlacesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location location;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MIN_TIME_BW_UPDATES = 1000;
    private static final int REQUEST_PERMISSION_ACCESS_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_places);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_ACCESS_LOCATION);

            return;
        }
        location = locationManager.getLastKnownLocation(provider);

        LatLng myPosition = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myPosition).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 16.0f));
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch(requestCode){
            case REQUEST_PERMISSION_ACCESS_LOCATION:
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, getString(R.string.thanx), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.reconsider), Toast.LENGTH_LONG).show();
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
            }
        }
    }

}

