package com.example.easymove;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Banamex extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banamex);
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
    public void reformaCiti(View view){
        goCitiReforma();
    }

    private void goCitiReforma(){
        Intent intent=new Intent(this,CitiReforma.class);
        startActivity(intent);
    }
    public void laPazCiti(View view){
        goCitiLaPaz();
    }

    private void goCitiLaPaz(){
        Intent intent=new Intent(this,CitiPaz.class);
        startActivity(intent);
    }
    public void mayo5(View view){
        go5mayo();
    }

    private void go5mayo(){
        Intent intent=new Intent(this,Citi5.class);
        startActivity(intent);
    }
    public void juarez(View view){
        goAvJuarez();
    }

    private void goAvJuarez(){
        Intent intent=new Intent(this,CitiJuarez.class);
        startActivity(intent);
    }
    public void poniente25(View view){
        go25poniente();
    }

    private void go25poniente(){
        Intent intent=new Intent(this,Citi25.class);
        startActivity(intent);
    }
}