package thecodingstache.tedxuthlarissa.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

import thecodingstache.tedxuthlarissa.HomeScreen;
import thecodingstache.tedxuthlarissa.Model.User;
import thecodingstache.tedxuthlarissa.R;

public class ProfileFragment extends Fragment {
    private static final String TAG = "Name" ;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView profile_name;
    private ImageView imageView;
    private TextView logOut;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    static final int GOOGLE_SIGN_IN = 123;
    RelativeLayout mRelativeLayout;

    public ProfileFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        getActivity().setTitle("Profile");
        logOut = view.findViewById(R.id.logOut);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);
        mRelativeLayout = view.findViewById(R.id.fragment_profile_layout);
        profile_name = view.findViewById(R.id.profile_name);
        imageView = view.findViewById(R.id.profile_picture);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        updateUI();
        logOut.setOnClickListener(v -> Logout());
        return view;
    }


    private void Logout() {
        mFirebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent openHome = new Intent(getContext(), HomeScreen.class);
            startActivity(openHome);
        });
    }


    private void updateUI() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String displayName = user.getDisplayName();
            Uri profileUri = user.getPhotoUrl();
            profile_name.setText(displayName);
            if (profileUri != null) {
                Glide.with(this).load(profileUri).into(imageView);
            }
        }
        //final FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        // If the above were null, iterate the provider data
        // and set with the first non null data


    }
}
