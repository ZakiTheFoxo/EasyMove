package com.example.easymove;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Hsbc extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsbc);
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
    public void reformaH(View view){
        goHReforma();
    }

    private void goHReforma(){
        Intent intent= new Intent(this,HsbcReforma.class);
        startActivity(intent);
    }
    public void laPazH(View view){
        goHLaPaz();
    }

    private void goHLaPaz(){
        Intent intent= new Intent(this,HsbcPaz.class);
        startActivity(intent);
    }
    public void poniente25(View view){
        go25poniente();
    }

    private void go25poniente(){
        Intent intent= new Intent(this,Hsbc25.class);
        startActivity(intent);
    }
    public void juarez(View view){
        goAvJuarez();
    }

    private void goAvJuarez(){
        Intent intent= new Intent(this,HsbcJuarez.class);
        startActivity(intent);
    }
    public void oriente14(View view){
        go14oriente();
    }

    private void go14oriente(){
        Intent intent= new Intent(this,Hsbc14.class);
        startActivity(intent);
    }
}