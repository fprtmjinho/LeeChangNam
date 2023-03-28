package com.example.donotcheat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText codeText;
    Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        codeText = (EditText) findViewById(R.id.code);
        joinButton = (Button) findViewById(R.id.join);
        joinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                joinExam(codeText.getText().toString());//관리자의 코드와 동일한지 비교하는 함수 구현해야함

            }
        });
    }
    void joinExam(String code) {
        if (code == "") {
            Toast.makeText(SignIn.this, "코드를 입력해주세요.", Toast.LENGTH_LONG).show(); return;}
        if (code.length() < 4) {Toast.makeText(SignIn.this, "4자리 코드를 입력해주세요.", Toast.LENGTH_LONG).show(); return;}

        Intent intent = new Intent(getApplicationContext(), Exam.class); // 시험 뷰로 넘어가야함
        finish();
        startActivity(intent);
    }
}