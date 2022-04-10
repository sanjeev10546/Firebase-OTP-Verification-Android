package com.example.otpverfication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyenterotptwo extends AppCompatActivity {
    EditText inputno1, inputno2, inputno3, inputno4, inputno5, inputno6;
    Button verifybuttonclick;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyenterotptwo);
        verifybuttonclick = findViewById(R.id.submitotp);
        inputno1 = findViewById(R.id.inputotp1);
        inputno2 = findViewById(R.id.inputotp2);
        inputno3 = findViewById(R.id.inputotp3);
        inputno4 = findViewById(R.id.inputotp4);
        inputno5 = findViewById(R.id.inputotp5);
        inputno6 = findViewById(R.id.inputotp6);


        TextView textView = findViewById(R.id.textmobileshowno);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend = getIntent().getStringExtra("backendotp");
        final ProgressBar progressBar = findViewById(R.id.progressbar);

        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputno1.getText().toString().trim().isEmpty() && !inputno2.getText().toString().trim().isEmpty() && !inputno3.getText().toString().trim().isEmpty() && !inputno4.getText().toString().trim().isEmpty() && !inputno5.getText().toString().trim().isEmpty() && !inputno6.getText().toString().trim().isEmpty()) {
                    String entercodeotp = inputno1.getText().toString() +
                            inputno2.getText().toString() +
                            inputno3.getText().toString() +
                            inputno4.getText().toString() +
                            inputno5.getText().toString() +
                            inputno6.getText().toString();

                    if (getotpbackend != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifybuttonclick.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        verifybuttonclick.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(verifyenterotptwo.this, "Enter the correct Otp", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                    } else {
                        Toast.makeText(verifyenterotptwo.this, "plese check internet conection", Toast.LENGTH_SHORT).show();
                    }


//                    Toast.makeText(verifyenterotptwo.this,"otp verify",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(verifyenterotptwo.this, "please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberotpmove();
        TextView resend;
        resend=findViewById(R.id.resendotp);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60, TimeUnit.SECONDS,
                        verifyenterotptwo.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verifyenterotptwo.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               getotpbackend = newbackendotp;
                               Toast.makeText(verifyenterotptwo.this,"otp sended succussfully",Toast.LENGTH_SHORT).show();

                                // super.onCodeSent(s, forceResendingToken);
                            }
                        }
                );

            }
        });
    }

    private void numberotpmove() {
        inputno1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputno2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputno2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputno3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputno3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputno4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputno4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputno5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputno5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    inputno6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}