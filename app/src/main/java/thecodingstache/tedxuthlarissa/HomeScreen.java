package thecodingstache.tedxuthlarissa;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

public class HomeScreen extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    static final int GOOGLE_SIGN = 123;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private Button googleLogin;
    private ProgressDialog loadingBar;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;
    private Button facebookLogIn;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            String[] perms = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
            if (!EasyPermissions.hasPermissions(this, perms)) {
                EasyPermissions.requestPermissions(this, "All permissions are required in oder to run this application", REQUEST_CODE, perms);
            }
            googleLogin = findViewById(R.id.googleLogin);
            TextView skipLogin = findViewById(R.id.skip_login);
            mLinearLayout = findViewById(R.id.relativeHome);
            loadingBar = new ProgressDialog(this);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            Button button = findViewById(R.id.login);
            mFirebaseAuth = FirebaseAuth.getInstance();
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                    .Builder()
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


            FacebookSdk.sdkInitialize(getApplicationContext());
            mCallbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handleFacebookToken(loginResult.getAccessToken());
                    Toast.makeText(HomeScreen.this, "Welcome to TEDxUTHLarissa! ", Toast.LENGTH_SHORT).show();
                    openMainActivity();
                    finishAffinity();
                }

                @Override
                public void onCancel() {
                    Snackbar.make(mLinearLayout, "cancelled", Snackbar.LENGTH_LONG).show();

                }

                @Override
                public void onError(FacebookException error) {
                    Log.d("HomeScreen", "onError: " + error.getMessage());
                    Snackbar.make(mLinearLayout, "Error", Snackbar.LENGTH_LONG).show();

                }
            });




            facebookLogIn = findViewById(R.id.facebookLogin);
            facebookLogIn.setOnClickListener(v -> LoginManager.getInstance()
                    .logInWithReadPermissions(HomeScreen.this, Arrays.asList("email", "public_profile")));

            button.setOnClickListener(v -> {
                Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                startActivity(intent);

            });
            googleLogin.setOnClickListener(v -> signInGoogle());
            skipLogin.setOnClickListener(v -> openMainActivity());

        }
    }


    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser facebookUser = mFirebaseAuth.getCurrentUser();
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
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount>
                    task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle" + account.getId());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "Sign in success");
                        loadingBar.dismiss();
                        Toast.makeText(HomeScreen.this, "Welcome to TEDxUTHLarissa! ", Toast.LENGTH_SHORT).show();
                        openMainActivity();
                        finishAffinity();
                    } else {
                        Toast.makeText(HomeScreen.this, "Something went wrong... try again  ", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "Sing in failed");
                        loadingBar.dismiss();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.requestPermissions(this, "All permissions are required to run this application", requestCode, permissions);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        new AppSettingsDialog.Builder(this).build().show();
    }
}

