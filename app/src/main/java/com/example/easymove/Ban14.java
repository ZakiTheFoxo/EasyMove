package com.example.easymove;

import static java.nio.file.Paths.get;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;

public class Ban14 extends AppCompatActivity {

    private TextView txtType,txtService,txtMoney;
    private EditText edtType,edtService,edtMoney;
    private String money,service,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban14);
        edtType=findViewById(R.id.edtType);
        edtService=findViewById(R.id.edtService);
        edtMoney=findViewById(R.id.edtMoney);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("bancomer").document("14 oriente").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    txtType=findViewById(R.id.txtType);
                    txtType.setText(documentSnapshot.getString("Tipo").toString());

                    txtService=findViewById(R.id.txtService);
                    txtService.setText(documentSnapshot.getString("Fuera de servicio").toString());

                    txtMoney=findViewById(R.id.txtMoney);
                    txtMoney.setText(documentSnapshot.getString("Dinero").toString());

                }
            }
        });

    }
    public void location(View view){
        locationBank();
    }

    private void locationBank() {
        Intent intent= new Intent(this,HomeActivity.class);
        intent.putExtra("Banco","Bancomer14");
        startActivity(intent);
    }
    public void saveData(View view){
        updateData();
    }

    private void updateData(){
        money=edtMoney.getText().toString();
        service=edtService.getText().toString();
        type=edtType.getText().toString();

        if(money.isEmpty()){
            money=txtMoney.getText().toString();

        }else{
            money=edtMoney.getText().toString();
        }

        if(service.isEmpty()){
            service=txtService.getText().toString();

        }else{
            service=edtService.getText().toString();
        }

        if(type.isEmpty()){
            type=txtType.getText().toString();

        }else{
            type=edtType.getText().toString();
        }

        String collection="bancomer";
        FirebaseFirestore dbUpdate= FirebaseFirestore.getInstance();
        dbUpdate.collection(collection).document("14 oriente")
                .update("Dinero",money);

        dbUpdate.collection(collection).document("14 oriente")
                .update("Fuera de servicio",service);

        dbUpdate.collection(collection).document("14 oriente")
                .update("Tipo",type);

        edtType.setVisibility(View.GONE);
        txtType.setVisibility(View.VISIBLE);
        edtService.setVisibility(View.GONE);
        txtService.setVisibility(View.VISIBLE);
        edtMoney.setVisibility(View.GONE);
        txtMoney.setVisibility(View.VISIBLE);

        Intent intent=new Intent(this,Bancomer.class);
        startActivity(intent);

        Toast.makeText(this,"Datos guardados correctamente", Toast.LENGTH_LONG).show();

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
    public void availableT(View view){
        availableType();
    }

    private void availableType() {
        edtType.setVisibility(View.VISIBLE);
        txtType.setVisibility(View.GONE);

    }
    public void availableS(View view){
        availableService();
    }

    private void availableService() {
        edtService.setVisibility(View.VISIBLE);
        txtService.setVisibility(View.GONE);
    }
    public void availableM(View view){
        availableMoney();
    }

    private void availableMoney() {
        edtMoney.setVisibility(View.VISIBLE);
        txtMoney.setVisibility(View.GONE);
    }
}






