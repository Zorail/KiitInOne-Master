package app.kiit.kshitijsharma.allinone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.kiit.kshitijsharma.allinone.mainmenu.MenuCampusActivity;
import app.kiit.kshitijsharma.allinone.mainmenu.MenuTimetableActivity;
import app.kiit.kshitijsharma.allinone.odatabase.UserInformation;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
ImageView iv1,iv2,iv3,iv4,iv5;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dataref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        iv1= (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3= (ImageView) findViewById(R.id.imageView3);
        iv4= (ImageView) findViewById(R.id.imageView4);
        iv5= (ImageView) findViewById(R.id.imageView5);
        auth= FirebaseAuth.getInstance();
        dataref= FirebaseDatabase.getInstance().getReference();

        addinformation();
        iv1.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
    }

    private void addinformation() {
        try{
            Bundle b= getIntent().getExtras();
        if (b!=null) {
            String name = b.getString("N");
            String roll = b.getString("R");
            String branch = b.getString("B");
            String sem = b.getString("S");
            UserInformation userinfo = new UserInformation(name, roll, branch, sem);
            user = auth.getCurrentUser();
            dataref.child(user.getUid()).setValue(userinfo);
        }}catch (NullPointerException e){
            Toast.makeText(this,"WELCOME",Toast.LENGTH_SHORT).show();
        }
    }

    Intent i;
    @Override
    public void onClick(View view) {
        if (view==iv1){
        i = new Intent(this,MenuTimetableActivity.class);
        startActivity(i);
    }
        if (view==iv4){
            i= new Intent(this,MenuCampusActivity.class);
            startActivity(i);
        }
        if (view==iv5){
            auth.signOut();
            finish();
            startActivity(new Intent(this,FirstpageActivity.class));
        }

    }
}
