package com.example.xiandalong.google_maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        LatLng newYork = new LatLng(40.7484, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    @OnClick(R.id.button_map)
    public void mapButtonClicked() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @OnClick(R.id.button_satellite)
    public void satelliteButtonClicked() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    @OnClick(R.id.button_hybrid)
    public void hybridButtonClicked() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
