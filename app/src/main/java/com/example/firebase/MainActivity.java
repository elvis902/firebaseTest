package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    EditText name, phone, email;
    Button btn;
    Member member;
    int maxId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        btn = findViewById(R.id.btn_send);

        member = new Member();

        ref = database.getInstance().getReference().child("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (int)snapshot.getChildrenCount();
                }
                else{
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setName(name.getText().toString());
                member.setPhone(phone.getText().toString());
                member.setEmail(email.getText().toString());

                ref.child(String.valueOf(maxId+1)).setValue(member);
            }
        });
    }
}