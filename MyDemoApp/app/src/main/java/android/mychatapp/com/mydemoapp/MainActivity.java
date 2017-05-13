package android.mychatapp.com.mydemoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private EditText mRun;
    private EditText mWicket;
    private EditText mOver;
    private EditText mTeam;
    private Button mSubmit;
    private EditText mComment;
    private ImageButton mSend;
    private DatabaseReference mDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        mRun = (EditText) findViewById(R.id.run);
        mWicket = (EditText) findViewById(R.id.wicket);
        mOver = (EditText) findViewById(R.id.over);
        mTeam = (EditText) findViewById(R.id.team);
        mSubmit = (Button) findViewById(R.id.submit_score);
        mComment = (EditText) findViewById(R.id.comment_message);
        mSend = (ImageButton) findViewById(R.id.send);
        mSend.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.about:
                // about
                //startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            case R.id.help:
                // Pending for implement
                // startActivity(new Intent(MainActivity.this, HelpActivity.class));
                break;
            case R.id.logout:
                signOut();
                break;
            case R.id.change_email:
                // pending for implement
                // startActivity(new Intent(MainActivity.this, ChangeEmaiActivity.class));
                break;
            case R.id.change_password:
                //startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.delete_account:
                // Delete AccountAcititvty
                break;
            default:
                return super.onOptionsItemSelected(item);

        }

        return true;
    }

    // sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.submit_score:
                submitScore();
                break;
            case R.id.send:
                submitComment();
                break;
        }
    }


    public void submitComment() {

        String thiscomment = mComment.getText().toString();

        if (TextUtils.isEmpty(thiscomment)) {
            mComment.setError("Required");
            return;
        }

        Comment comment = new Comment(thiscomment);
        mDatabase.child("comment").push().setValue(comment);
        mComment.setText(null);

    }

    public void submitScore() {

        final String team = mTeam.getText().toString();
        final String runString = mRun.getText().toString();
        final String wicketstring = mWicket.getText().toString();
        final String overstring = mOver.getText().toString();
        Log.d("overshiv", overstring);
        if (TextUtils.isEmpty(team)) {
            mTeam.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(runString)) {
            mRun.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(wicketstring)) {
            mWicket.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(overstring)) {
            mOver.setError("Required");
            return;
        }
        int run = Integer.parseInt(runString);
        int wicket = Integer.parseInt(wicketstring);
        String overData[] = overstring.split("\\.");
        Log.d("shiv", overData.toString());
        int len = overData.length;
        int over = 0;
        int ball = 0;
        Log.d("Shiv", len + "");
        if (len == 1) {
            ball = 0;
            over = Integer.parseInt(overData[0]);
        } else if (len == 2) {
            over = Integer.parseInt(overData[0]);
            ball = Integer.parseInt(overData[1]);
        }

        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        LiveScore newscore = new LiveScore(team, run, wicket, over, ball);

        Map<String, Object> scoremap = newscore.toMap();
        Map<String, Object> update = new HashMap<>();
        update.put("/livescore", scoremap);
        mDatabase.updateChildren(update);
        return;

    }
}
