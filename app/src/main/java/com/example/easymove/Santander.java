package com.example.easymove;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Santander extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_santander);
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
        Intent intent=new Intent(this,UserInfo.class);
        startActivity(intent);
    }
    public void reformaSan(View view){
        goSanReforma();
    }

    private void goSanReforma(){
        Intent intent= new Intent(this,SanReforma.class);
        startActivity(intent);
    }
    public void laPazSan(View view){
        goSanLaPaz();
    }

    private void goSanLaPaz(){
        Intent intent= new Intent(this,SanLaPaz.class);
        startActivity(intent);
    }
    public void uapep(View view){
        goUpaep();
    }

    private void goUpaep(){
        Intent intent= new Intent(this,SanUpa.class);
        startActivity(intent);
    }
    public void juarez(View view){
        goAvJuarez();
    }

    private void goAvJuarez(){
        Intent intent= new Intent(this,SanJuarez.class);
        startActivity(intent);
    }
    public void oriente14(View view){
        go14oriente();
    }

    private void go14oriente(){
        Intent intent= new Intent(this,San14.class);
        startActivity(intent);
    }
}