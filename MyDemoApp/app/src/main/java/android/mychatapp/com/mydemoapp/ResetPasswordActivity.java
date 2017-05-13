package android.mychatapp.com.mydemoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button mResetPassword;
    private EditText mEmail;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_reset_password);

        mResetPassword=(Button)findViewById(R.id.send_reset_email);
        mEmail =(EditText)findViewById(R.id.email);
        mResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                if (!mEmail.getText().toString().trim().equals("")) {
                    auth.sendPasswordResetEmail(mEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ResetPasswordActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
                                       // progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(ResetPasswordActivity.this, SignupActivity.class));
                                    } else {
                                        Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    mEmail.setError("Enter email");
                   // progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
