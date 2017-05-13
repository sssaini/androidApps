package android.mychatapp.com.mychatapplication;

import android.content.Intent;
import android.mychatapp.com.mychatapplication.Dialog.DialogUtil;
import android.mychatapp.com.mychatapplication.Model.MainActivity;
import android.mychatapp.com.mychatapplication.Model.User;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignInActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private Button mSignUpButton;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mEmailField =(EditText) findViewById(R.id.email);
        mPasswordField =  (EditText)findViewById(R.id.password);
        mSignUpButton = (Button)findViewById(R.id.sign_up);
        mSignInButton= (Button) findViewById(R.id.sign_in);

        mSignInButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);

        mAuthListener= createAuthListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.sign_in :
                signIn();
                break;
            case R.id.sign_up :
                signUp();
                 break;
        }

    }

    private void signIn(){

        Log.d(TAG, "signIn");
        if (!validForm()) {
            return;
        }

        DialogUtil.showProgressDialog(this);
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                DialogUtil.dissMissDialog();
                if (task.isSuccessful()) {
                    // if(task.getResult().getUser().isEmailVerified()){
                    //Toast.makeText(SignInActivity.this, " Sign In Successful", Toast.LENGTH_LONG).show();
                    onAuthSuccess(task.getResult().getUser());
                     //}else{
                    //Toast.makeText(SignInActivity.this, " please verify your email", Toast.LENGTH_LONG).show();
               // }

                } else {
                    Toast.makeText(SignInActivity.this, "Sign in Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void signUp(){

        Log.d(TAG, "Sign Up");

        if(!validForm()){

            return;
        }
        DialogUtil.showProgressDialog(this);
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                DialogUtil.dissMissDialog();
                if (task.isSuccessful()) {

                    task.getResult().getUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this ,"Verification mail have  send  to email please verify",Toast.LENGTH_LONG);
                            }
                        }
                    });

                    //onAuthSuccess(task.getResult().getUser());
                } else {
                    Toast.makeText(SignInActivity.this, "Sign Up Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validForm(){

        boolean valid=false;
        String email = mEmailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            mEmailField.setError(" Required");
            return valid;
        }else{
                mEmailField.setError(null);
        }
        String password = mPasswordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            mPasswordField.setError("Required");
            return valid;
        }else{
            mPasswordField.setError(null);
        }
        valid=true;
        return valid;
    }

    private FirebaseAuth.AuthStateListener  createAuthListener(){
       FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Toast.makeText((SignInActivity)getApplicationContext(), " Login Successfully", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        return authListener;
    }
    private void onAuthSuccess(FirebaseUser user  ){

       String userName = getUserNameFromEmail(user.getEmail());
        writeNewUser(user.getUid(),userName,user.getEmail());
        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();

    }

   public  String  getUserNameFromEmail(String email){
       if(email.contains("@"))
       return email.split("@")[0];
       else
           return email;
   }

   public void writeNewUser(String userId , String userName, String email){

       User user = new User(userName, email);

        mDatabase.child("users").child(userId).setValue(user);

   }

}
