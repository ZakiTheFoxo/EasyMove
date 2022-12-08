package com.example.easymove;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {


    public static final int REQUEST_CODE_LOCATION = 1;
    private Boolean activateGPS = true;
    private GoogleMap map;
    private Location currentLocation;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initPermission();
        createFragment();
        getLocation();
    }

    private void createFragment() {
        SupportMapFragment mapFragment;
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        createMarker();
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);

    }
    private void createMarker(){

        Intent intent=getIntent();
        String data=intent.getStringExtra("Banco");


        LatLng BBVApaz= new LatLng(19.0588732,-98.231265);
        LatLng BBVAjuarez=new LatLng(19.04795,-98.2127642);
        LatLng BBVAreforma= new LatLng(19.044492,-98.2014027);
        LatLng BBVA2poniente= new LatLng(19.0396323,-98.1899363);
        LatLng BBVA14oriente= new LatLng(19.0430899,-98.1810761);

        LatLng santUpa= new LatLng(19.0479904,-98.2183315);
        final LatLng santPaz= new LatLng(19.0585364,-98.2328457);
        LatLng santJuarez= new LatLng(19.0530775,-98.2238712);
        LatLng sant14oriente= new LatLng(19.0426343,-98.2451299);
        LatLng santReforma= new LatLng(19.0453525,-98.2031342);

        final LatLng hsbcPaz= new LatLng(19.0586026,-98.2330458);
        final LatLng hsbcReforma= new LatLng(19.044492,-98.2014027);
        final LatLng hsbc25= new LatLng(19.0444163,-98.2241034);
        final LatLng hsbcJuarez= new LatLng(19.0476064,-98.2120897);
        LatLng hsbc14= new LatLng(18.9807888,-98.2210638);

        LatLng citiPaz= new LatLng(19.0574669,-98.2347059);
        LatLng citiReforma= new LatLng(19.044492,-98.2014027);
        LatLng citi25= new LatLng(19.0430099,-98.242043);
        LatLng citiJuarez= new LatLng(19.0537275,-98.224116);
        LatLng citi5mayo= new LatLng(19.0431689,-98.242043);

        map.addMarker(new MarkerOptions().position(BBVApaz).title("BBVA la paz"));
        map.addMarker(new MarkerOptions().position(BBVAjuarez).title("BBVA Av. Juarez"));
        map.addMarker(new MarkerOptions().position(BBVA14oriente).title("BBVA 14 oriente"));
        map.addMarker(new MarkerOptions().position(BBVA2poniente).title("BBVA 2 oriente"));
        map.addMarker(new MarkerOptions().position(BBVAreforma).title("BBVA reforma"));

        map.addMarker(new MarkerOptions().position(santUpa).title("Santander UPAEP"));
        map.addMarker(new MarkerOptions().position(santReforma).title("Santander reforma"));
        map.addMarker(new MarkerOptions().position(santJuarez).title("Santander Av. Juarez"));
        map.addMarker(new MarkerOptions().position(santPaz).title("Santander La Paz"));
        map.addMarker(new MarkerOptions().position(sant14oriente).title("Santander 14 oriente"));

        map.addMarker(new MarkerOptions().position(hsbc14).title("HSBC 14 sur"));
        map.addMarker(new MarkerOptions().position(hsbcPaz).title("HSBC La Paz"));
        map.addMarker(new MarkerOptions().position(hsbc25).title("HSBC 25 poniente"));
        map.addMarker(new MarkerOptions().position(hsbcReforma).title("HSBC Reforma"));
        map.addMarker(new MarkerOptions().position(hsbcJuarez).title("HSBC Av. Juarez"));

        map.addMarker(new MarkerOptions().position(citiPaz).title("Banamex La Paz"));
        map.addMarker(new MarkerOptions().position(citi5mayo).title("Banamex Boulevard 5 de mayo"));
        map.addMarker(new MarkerOptions().position(citi25).title("Banamex 25 poniente"));
        map.addMarker(new MarkerOptions().position(citiReforma).title("Banamex Reforma"));
        map.addMarker(new MarkerOptions().position(citiJuarez).title("Banamex Av.Juarez"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(santUpa,18f),4000,null);


        if(data=="BancomerPaz"){
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(BBVApaz,18f),4000,null);
        }else if(data=="BancomerReforma") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(BBVAreforma, 18f), 4000, null);
        }else if(data=="BancomerJuarez") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(BBVAjuarez, 18f), 4000, null);
        }else if(data=="Bancomer14") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(BBVA14oriente, 18f), 4000, null);
        }else if(data=="Bancomer2p") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(BBVA2poniente, 18f), 4000, null);
        }else if(data=="SantanderUpaep") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(santUpa, 18f), 4000, null);
        }else if(data=="SantanderJuarez") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(santJuarez, 18f), 4000, null);
        }else if(data=="Santander14") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(sant14oriente, 18f), 4000, null);
        }else if(data=="SantanderPaz") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(santPaz, 18f), 4000, null);
        }else if(data=="SantanderReforma") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(santReforma, 18f), 4000, null);
        }else if(data=="Hsbc14") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hsbc14, 18f), 4000, null);
        }else if(data=="Hsbc25") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hsbc25, 18f), 4000, null);
        }else if(data=="HsbcPaz") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hsbcPaz, 18f), 4000, null);
            Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        }else if(data=="HsbcJuarez") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hsbcJuarez, 18f), 4000, null);
        }else if(data=="HsbcReforma") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(hsbcReforma, 18f), 4000, null);
        }else if(data=="Banamex5") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(citi5mayo, 18f), 4000, null);
        }else if(data=="Banamex25") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(citi25, 18f), 4000, null);
        }else if(data=="BanamexJuarez") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(citiJuarez, 18f), 4000, null);
        }else if(data=="BanamexPaz") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(citiPaz, 18f), 4000, null);
        }else if(data=="BanamexReforma") {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(citiReforma, 18f), 4000, null);
        }

    }

    private void getLocation(){
        int permiso=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        if (permiso == PackageManager.PERMISSION_DENIED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    private void initPermission(){
        if(isMyLocationEnabled()==false){
            AlertDialog.Builder alerta=new AlertDialog.Builder(this);
            alerta.setMessage(getString(R.string.alertActivationGPSDescription))
                            .setCancelable(true)
                                    .setPositiveButton(R.string.acceptActivationGPSTitle, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            activationLocation();
                                        }
                                    }).setNegativeButton(R.string.ignoreActivationGPSTitle, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activateGPS=false;
                        }
                    });
            AlertDialog titulo=alerta.create();
            titulo.setTitle(getString(R.string.alertActivationGPSTitle));
            titulo.show();
        }else{

        }
    }

    private void activationLocation(){
        Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private Boolean isMyLocationEnabled(){
        LocationManager locationManager;
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void home(View view){
        goHome();
    }

    private void goHome(){
        Intent intent= new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void favorite(View view){
        goFavorite();
    }

    private void goFavorite(){
        Intent intent= new Intent(this,Favorite.class);
        startActivity(intent);
    }

    public void user(View view){
        goUser();
    }

    private void goUser(){
        Intent intent=new Intent(HomeActivity.this,UserInfo.class);
        startActivity(intent);
    }

    public void santander(View view){
        goSantander();
    }

    private void goSantander(){
        Intent intent= new Intent(this,Santander.class);
        startActivity(intent);
    }

    public void bancomer(View view){
        goBancomer();
    }

    private void goBancomer(){
        Intent intent= new Intent(this,Bancomer.class);
        startActivity(intent);
    }

    public void hsbc(View view){
        goHSBC();
    }

    private void goHSBC(){
        Intent intent= new Intent(this,Hsbc.class);
        startActivity(intent);
    }

    public void banamex(View view){
        goBanamex();
    }

    private void goBanamex(){
        Intent intent= new Intent(this,Banamex.class);
        startActivity(intent);
    }


    @Override
    public boolean onMyLocationButtonClick() {
        return true;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}