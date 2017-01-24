package com.example.xiandalong.google_maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(
                R.id.streetViewPanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        LatLng newYork = new LatLng(42.366717, -71.081779);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(42.367063, -71.080308))
                .title("Intrepid Pursuits")
                .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.intrepid_logo));
        googleMap.addMarker(markerOptions);
        googleMap.addCircle(new CircleOptions().center(new LatLng(42.367063, -71.080308))
                                    .radius(10)
                                    .strokeColor(Color.RED)
                                    .fillColor(Color.argb(64, 255, 0, 0)));

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

    @OnClick(R.id.seattle_button)
    public void seattleButtonClicked() {
        LatLng target = new LatLng(47.6204, -122.3491);
        flyTo(target);
    }

    @OnClick(R.id.new_york_button)
    public void newYorkButtonClicked() {
        LatLng target = new LatLng(40.7127, 74.0059);
        flyTo(target);
    }

    @OnClick(R.id.boston_button)
    public void bostonButtonClicked() {
        LatLng target = new LatLng(42.3601, -71.0589);
        flyTo(target);
    }

    private void flyTo(LatLng target) {
        CameraPosition seattlePosition = CameraPosition.builder().target(target).zoom(14).bearing(90).tilt(45).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(seattlePosition), 500, null);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(42.366717, -71.081779));
        StreetViewPanoramaCamera streetViewPanoramaCamera = new StreetViewPanoramaCamera.Builder().zoom(10).build();
        streetViewPanorama.animateTo(streetViewPanoramaCamera, 1000);
    }
}
