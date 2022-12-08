package com.example.easymove;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.util.PatternsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kotlin.properties.Delegates;

public class MainActivity extends AppCompatActivity {


    String userEmail;
    public String providerSession;

    private String email,password;
    private TextInputEditText emailInit,passwordInit;
    private FirebaseAuth mAuth;
    private Button buttonInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_EasyMove);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInit=findViewById(R.id.emailInit);
        passwordInit=findViewById(R.id.passwordInit);
        buttonInit=findViewById(R.id.buttonInit);
        mAuth=FirebaseAuth.getInstance();

        manageButtonLogin();
        emailInit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                manageButtonLogin();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordInit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                manageButtonLogin();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextView txtSignIn=findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null) {
            goHome(currentUser.getEmail().toString(),currentUser.getProviderId());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain=new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(startMain.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    private void manageButtonLogin(){
        Button buttonInit=findViewById(R.id.buttonInit);
        email=emailInit.getText().toString();
        password=passwordInit.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("Usuario",email);

        if(TextUtils.isEmpty(password)||validateEmail()==false){
            buttonInit.setBackgroundColor(ContextCompat.getColor(this,R.color.gray));
            buttonInit.setEnabled(false);
        }else{
            buttonInit.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_botton));
            buttonInit.setEnabled(true);
        }
    }

    public void login(View view){
        loginUser();
    }

    private void loginUser(){
        email=emailInit.getText().toString();
        password=passwordInit.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                goHome(email,"email");
                finish();
            }else{
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
                });
    }

    private void goHome(String email, String provider){
        userEmail=email;
        providerSession=provider;

        Intent intent= new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    public void forgotPassword(View view){
        resetPassword();
    }
    private boolean validateEmail(){
        email=emailInit.getText().toString();
        if(email.isEmpty()){
            return false;
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            return false;
        }else{
            return true;
        }
    }

    private void resetPassword(){
        String e=emailInit.getText().toString();
        mAuth.setLanguageCode("es");

        if(!TextUtils.isEmpty(e)){
            mAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Correo enviado a "+e,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this,"No se encontro al usuario con este correo",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else Toast.makeText(this,"Por favor indica un email",Toast.LENGTH_LONG).show();
    }
}


