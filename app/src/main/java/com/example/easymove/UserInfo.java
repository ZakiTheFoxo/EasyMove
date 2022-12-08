package com.example.easymove;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfo extends AppCompatActivity{

    private TextView txtName,txtLast,txtUser,txtEmail;
    FirebaseFirestore fstore;
    private String idUser;
    FirebaseAuth mAuth;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser user=mAuth.getCurrentUser();
        fstore=FirebaseFirestore.getInstance();
        idUser=user.getEmail();

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("users").document(idUser).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    txtName=findViewById(R.id.txtName);
                    txtName.setText(documentSnapshot.getString("nombre").toString());

                    txtLast=findViewById(R.id.txtLast);
                    txtLast.setText(documentSnapshot.getString("apellido").toString());

                    txtUser=findViewById(R.id.txtUser);
                    txtUser.setText(documentSnapshot.getString("nombreUsuario").toString());

                    txtEmail=findViewById(R.id.txtEmail);
                    txtEmail.setText(documentSnapshot.getString("email").toString());

                }
            }
        });

    }

    public void callLogOut(View view){
        logOut();
    }

    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
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
}