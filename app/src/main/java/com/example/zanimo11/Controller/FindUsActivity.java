package com.example.zanimo11.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.zanimo11.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindUsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng ZanimoLoc1 = new LatLng(36.755489, 10.274609);
        LatLng ZanimoLoc2 = new LatLng(36.405898, 10.141264);
        LatLng ZanimoLoc3 = new LatLng(36.787527, 10.174860);

        googleMap.addMarker(new MarkerOptions().position(ZanimoLoc1)
                .title("Zanimo Location 1"));
        googleMap.addMarker(new MarkerOptions().position(ZanimoLoc2)
                .title("Zanimo Location 2"));
        googleMap.addMarker(new MarkerOptions().position(ZanimoLoc3)
                .title("Zanimo Location 3"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ZanimoLoc1));
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(ZanimoLoc1);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        map.moveCamera(center);
        map.animateCamera(zoom);
    }
}