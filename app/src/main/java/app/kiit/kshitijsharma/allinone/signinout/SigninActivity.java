package app.kiit.kshitijsharma.allinone.signinout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.kiit.kshitijsharma.allinone.MainMenuActivity;
import app.kiit.kshitijsharma.allinone.R;


public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etemail, etpass;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    /*GoogleApiClient googleApiClient;
    SignInButton googlesignin;
    LoginButton fblogin;
    CallbackManager mCallbackManager;
    static final String TAG = "GoogleActivity";
    static final int rc = 9001;*/
    FirebaseUser user;
    Button signin;
    TextView tv, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainMenuActivity.class));
        }
        user = firebaseAuth.getCurrentUser();
        signup = (TextView) findViewById(R.id.textViewsignup);
        tv = (TextView) findViewById(R.id.sign);
        Typeface type = Typeface.createFromAsset(this.getAssets(), "fonts/josefin_sans_regular.ttf");
        tv.setTypeface(type);
      /*  mCallbackManager = CallbackManager.Factory.create();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/
        etemail = (EditText) findViewById(R.id.etemail);
        progressDialog = new ProgressDialog(this);
        etpass = (EditText) findViewById(R.id.etpass);
        //googlesignin = (SignInButton) findViewById(R.id.googlesignin);
        //fblogin = (LoginButton) findViewById(R.id.fbsignin);
        //fblogin.setReadPermissions("email", "public_profile");
        signin = (Button) findViewById(R.id.buttonsignin);
        //googlesignin.setOnClickListener(this);
        //fblogin.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    private void userLogin() {
        String email = etemail.getText().toString().trim();
        String pass = etpass.getText().toString().trim();
        if (TextUtils.isEmpty(email))
            Toast.makeText(this, "PLEASE ENTER EMAIL", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(pass))
            Toast.makeText(this, "PLEASE ENTER PASWWORD", Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(SigninActivity.this, MainMenuActivity.class));
                        }
                    }
                });

    }
   /* private void googleSignIn(){
        //Intent SignInIntent= new Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        //startActivityForResult(SignInIntent,rc);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, rc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==rc){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
        }
    }
    void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        if(acct.getIdToken()==null){
            Toast.makeText(this,"Please enter correctly",Toast.LENGTH_SHORT).show();
            Intent signini=new Intent(SigninActivity.this,FirstpageActivity.class);
            startActivity(signini);
        }
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful() ){
                            finish();
                            Intent signini=new Intent(SigninActivity.this,MainMenuActivity.class);
                            startActivity(signini);
                        }
                        else{
                           Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void FBLogin(){
        fblogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(SigninActivity.this,MainMenuActivity.class));
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

    }
    void handleFacebookAccessToken(AccessToken token) {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SigninActivity.this,MainMenuActivity.class));
                        }
                        else{
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    @Override
    public void onClick(View view) {
        if (view == signin) {
            userLogin();
        }
       /* if (view==googlesignin){
            googleSignIn();
        }
        if (view==fblogin){
            FBLogin();
        }*/
        if (view == signup) {
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }


    }

/*    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }*/
}