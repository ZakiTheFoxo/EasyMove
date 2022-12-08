package com.example.easymove;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.util.PatternsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.properties.Delegates;

public class SignInActivity extends AppCompatActivity{


    private String email,password,firstName,last,user;
    private TextInputEditText emailSignIn,passwordSignIn,name,lastName,userName;
    private TextInputLayout emailLayout, passwordLayout,nameLayout,lastLayout,userLayout;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        emailSignIn=findViewById(R.id.emailSignIn);
        passwordSignIn=findViewById(R.id.passwordSignIn);
        name=findViewById(R.id.name);
        lastName=findViewById(R.id.lastName);
        userName=findViewById(R.id.userName);
        emailLayout=findViewById(R.id.emailLayout);
        passwordLayout=findViewById(R.id.passwordLayout);
        nameLayout=findViewById(R.id.nameLayout);
        lastLayout=findViewById(R.id.lastLayout);
        userLayout=findViewById(R.id.userLayout);

    }

    public void register(View view){
        if(!validateEmail() | !validateName() | !validatePassword() | !validateUser() | !validateUser() | !validateLast()){
            return;
        }
        registerUser();
    }
    private void registerUser(){

        email=emailSignIn.getText().toString().trim();
        password=passwordSignIn.getText().toString().trim();
        firstName=name.getText().toString().trim();
        last=lastName.getText().toString().trim();
        user=userName.getText().toString().trim();


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Map<String,Object>map=new HashMap<>();
                map.put("id",email);
                map.put("nombre",firstName);
                map.put("apellido",last);
                map.put("email",email);
                map.put("nombreUsuario",user);

                db.collection("users").document(email).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        goLogIn();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this,"Algo salio mal",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignInActivity.this,"Algo salio mal",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void goLogIn(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private boolean validateName(){
        firstName=name.getText().toString();
        if(firstName.isEmpty()){
            nameLayout.setError("EL campo no puede estar vacio");
            return false;
        }else{
            nameLayout.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        email=emailSignIn.getText().toString();
        if(email.isEmpty()){
            emailLayout.setError("EL campo no puede estar vacio");
            return false;
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            emailLayout.setError("Por favor ingresa un correo valido");
            return false;
        }else{
            emailLayout.setError(null);
            return true;
        }
    }
    private boolean validateLast(){
        last=lastName.getText().toString();
        if(last.isEmpty()){
            lastLayout.setError("EL campo no puede estar vacio");
            return false;
        }else{
            lastLayout.setError(null);
            return true;
        }
    }
    private boolean validateUser(){
        user=userName.getText().toString();
        if(user.isEmpty()){
            userLayout.setError("EL campo no puede estar vacio");
            return false;
        }else{
            userLayout.setError(null);
            return true;
        }
    }


    private boolean validatePassword(){
        password=passwordSignIn.getText().toString();
         Pattern PASSWORD_PATTERN
                = Pattern.compile(
            "^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[@#$%^&+=])"+
                    "(?=\\S+$)"+
                    ".{4,}"+
                    "$"
        );
        if(password.isEmpty()){
            passwordLayout.setError("El campo no puede estar vacio");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            passwordLayout.setError("Contraseña insegura");
            return false;
        }else{
            passwordLayout.setError(null);
            return true;
        }
    }

}