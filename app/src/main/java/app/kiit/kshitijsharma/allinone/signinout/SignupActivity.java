package app.kiit.kshitijsharma.allinone.signinout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.kiit.kshitijsharma.allinone.MainMenuActivity;
import app.kiit.kshitijsharma.allinone.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText etfname,etemail,etpass,etcpass, etroll;
    Button bsignin;
    static final int rc =1;
    String branch,semester;
    RadioGroup rgbranch;
    FirebaseAuth auth;
    String sem[] ={"Select","1","2","3","4","5","6","7","8"};
    ProgressDialog pro;
    Spinner spsemester;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etemail= (EditText) findViewById(R.id.editTextemail);
        etfname = (EditText) findViewById(R.id.editTextfullname);
        etroll = (EditText) findViewById(R.id.editTextrollno);
        spsemester = (Spinner) findViewById(R.id.spinnersem);
        rgbranch = (RadioGroup) findViewById(R.id.radioGroupbranch);
        etpass= (EditText) findViewById(R.id.editTextpass);
        etcpass= (EditText) findViewById(R.id.editTextcpass);
        bsignin= (Button) findViewById(R.id.buttonup);
        pro= new ProgressDialog(this);
        rgbranch.setOnCheckedChangeListener(this);
        bsignin.setOnClickListener(this);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,sem);
        spsemester.setAdapter(adapter);
        spsemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                    Toast.makeText(SignupActivity.this,"Please Select",Toast.LENGTH_SHORT).show();
                else
                    semester = sem[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(user!=null){
            finish();
            startActivity(new Intent(SignupActivity.this,MainMenuActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if (view==bsignin){
            signin();
        }
    }

    private void signin() {
        String email= etemail.getText().toString().trim();
        String pass= etpass.getText().toString().trim();
        String cpass= etcpass.getText().toString().trim();
        if (TextUtils.isEmpty(email))
            Toast.makeText(SignupActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(pass))
            Toast.makeText(SignupActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(cpass))
            Toast.makeText(SignupActivity.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
        if (pass.equals(cpass)){
            pro.setMessage("Please Wait...");
            pro.show();
            auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pro.dismiss();
                            if (task.isSuccessful()){
                                finish();
                                Intent signupintent = new Intent(SignupActivity.this,MainMenuActivity.class);
                                signupintent.putExtra("N",etfname.getText().toString().trim());
                                signupintent.putExtra("R",etroll.getText().toString().trim());
                                signupintent.putExtra("B",branch);
                                signupintent.putExtra("S",semester);
                                startActivity(signupintent);
                            }
                        }
                    });
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i==R.id.radioButtonIT)
            branch="IT";
        if (i==R.id.radioButtonCS)
            branch="CS";
    }
}
