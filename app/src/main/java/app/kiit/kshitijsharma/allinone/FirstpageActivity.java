package app.kiit.kshitijsharma.allinone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.kiit.kshitijsharma.allinone.signinout.SigninActivity;
import app.kiit.kshitijsharma.allinone.signinout.SignupActivity;

public class FirstpageActivity extends AppCompatActivity implements View.OnClickListener {
Button b1,b2;
    FirebaseAuth auth;
    FirebaseUser user;
    static final int rc=1,rc1=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(this,MainMenuActivity.class));
        }
        b1= (Button) findViewById(R.id.button1);
        b2= (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(FirstpageActivity.this,SigninActivity.class);
                startActivityForResult(i,rc);
            }
        });
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i= new Intent(this,SignupActivity.class);
        startActivityForResult(i,rc1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==rc||requestCode==rc1)
            finish();
    }
}
