package com.example.easymove;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Bancomer extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bancomer);
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
    public void reformaBan(View view){
        goBanReforma();
    }

    private void goBanReforma(){
        Intent intent=new Intent(this,BanReforma.class);
        startActivity(intent);
    }
    public void laPazBan(View view){
        goBanLaPaz();
    }

    private void goBanLaPaz(){
        Intent intent=new Intent(this,BanLaPaz.class);
        startActivity(intent);
    }
    public void poniente2(View view){
        go2poniente();
    }

    private void go2poniente(){
        Intent intent=new Intent(this,Ban2.class);
        startActivity(intent);
    }
    public void juarez(View view){
        goAvJuarez();
    }

    private void goAvJuarez(){
        Intent intent=new Intent(this,BanJuarez.class);
        startActivity(intent);
    }
    public void oriente14(View view){
        go14oriente();
    }

    private void go14oriente(){
        Intent intent=new Intent(this,Ban14.class);
        startActivity(intent);
    }
}