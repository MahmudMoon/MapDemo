package com.example.bou.mapdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }else
           mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);


        // Add a marker in Sydney and move the camera
        LatLng rajshahi = new LatLng(24.3726449, 88.618339);
        MarkerOptions markerOptions1 = new MarkerOptions().position(rajshahi).title("Rajshahi").snippet("This is a dencely populated country"); //creating a markar options // snippet is used to give extra information about that location or markar
        markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.student_)); //changing the icon of the markar
        Marker marker = mMap.addMarker(markerOptions1); //adding the markar to the map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rajshahi,7.0f)); // moving the camera to the marked position
        marker.setVisible(true);

        // setting the visibility of the markar
       // marker.remove(); // is used to remove the markar

        LatLng dhaka  = new LatLng(23.8172375,90.3797777);
        MarkerOptions markerOptions = new MarkerOptions().position(dhaka).title("Dhaka");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        Marker marker1 = mMap.addMarker(markerOptions);
        marker1.setAlpha(0.5f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaka,7.0f));



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {             //this is the map click listener
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("TAg",marker.getTitle());
                if(marker.getTitle().toString().equals("Rajshahi")){
                    Log.d("TAG","in rajsahi");
                }else if(marker.getTitle().toString().equals("Dhaka")){
                    Log.d("TAG","in Dhaka");
                }
                return false;
            }
        });
    }
}
