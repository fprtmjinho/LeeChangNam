package com.example.donotcheat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ManagerSignIn extends AppCompatActivity {
    EditText code;
    Button join;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_sign_in);
        code = (EditText) findViewById(R.id.managerCode);
        join = (Button) findViewById(R.id.examCreate);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createExam(code.getText().toString());
            }
        });
    }
    void createExam(String code) {
        final int[] flag = {0};
        if (code == "") {
            Toast.makeText(ManagerSignIn.this, "코드를 입력해주세요.", Toast.LENGTH_LONG).show(); return;}
        if (code.length() < 4) {Toast.makeText(ManagerSignIn.this, "4자리 코드를 입력해주세요.", Toast.LENGTH_LONG).show(); return;}
        db.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String codes;
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                codes = (String) document.getData().get("code");
                                if (code.equals(codes) == true){
                                    flag[0] = 1;
                                }
                            }
                        }
                    }
                });
        if (flag[0]==1){Toast.makeText(ManagerSignIn.this, "중복된 코드 입니다.", Toast.LENGTH_LONG).show(); return;}
        else if(flag[0]==0){

        }
    }
}