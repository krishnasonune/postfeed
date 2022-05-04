package com.example.postfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminLoginActivity extends AppCompatActivity {
    
    private EditText name, phone, pass, village;
    private Button next;
    private DatabaseReference ref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ref = FirebaseDatabase.getInstance().getReference();
        
        name = (EditText) findViewById(R.id.name1);
        phone = (EditText) findViewById(R.id.phn1);
        pass = (EditText) findViewById(R.id.pwd1);
        village = (EditText) findViewById(R.id.vill1);
        next = (Button) findViewById(R.id.next1);
        
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(name.getText().toString(),
                        pass.getText().toString(),
                        phone.getText().toString(),
                        village.getText().toString());
            }
        });
        
    }

    private void addData(String nam, String pwd, String phn, String vill) {

        HashMap<String, Object> addData = new HashMap<>();
        addData.put("name", nam);
        addData.put("password", pwd);
        addData.put("phone", phn);
        addData.put("village", vill);
        
        ref.child("Admins").child("Villages").child(vill)
                .child(phn).updateChildren(addData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminLoginActivity.this, "created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        
    }
}