package thecodingstache.tedxuthlarissa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.Serializable;

import thecodingstache.tedxuthlarissa.Fragment.ProfileFragment;

public class HomeScreen extends AppCompatActivity implements Serializable {
    static final int GOOGLE_SIGN = 123;
    FirebaseAuth mFirebaseAuth;
    Button googleLogin;
    ProgressDialog loadingBar;
    GoogleSignInClient mGoogleSignInClient;
    RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        googleLogin = findViewById(R.id.googleLogin);
        loadingBar = new ProgressDialog(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mRelativeLayout = findViewById(R.id.relative);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        Button button = findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                startActivity(intent);

            }
        });
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });

    }

    private void signInGoogle() {
        loadingBar.setTitle("Γίνεται σύνδεση");
        loadingBar.setMessage("Παρακαλώ περιμένετε");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(googleSignInIntent, GOOGLE_SIGN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle" + account.getId());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "Sign in success");
                            loadingBar.dismiss();
                            Toast.makeText(HomeScreen.this, "Welcome back! ", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                            openMainActivity();
                            finish();

                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(HomeScreen.this, "Something went wrong... try again  ", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "Sing in failed");
                            updateUI(user);
                        }
                    }
                });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String photo = String.valueOf(user.getPhotoUrl());
            Intent intent = new Intent(HomeScreen.this, ProfileFragment.class);
            intent.putExtra("name", name);
            intent.putExtra("photo", photo);
        }
    }
}

