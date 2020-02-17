package thecodingstache.tedxuthlarissa.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import thecodingstache.tedxuthlarissa.R;

public class ProfileFragment extends Fragment {
    private GoogleSignInClient mGoogleSignInClient;
    private TextView profile_name;
    private ImageView imageView;
    private TextView logOut;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;

    public ProfileFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        getActivity().setTitle("Profile");
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        logOut = view.findViewById(R.id.logOut);
        profile_name = view.findViewById(R.id.profile_name);
        imageView = view.findViewById(R.id.profile_picture);
        updateUI(mFirebaseUser);
        logOut.setOnClickListener(v -> Logout());
        return view;
    }


    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener((Executor) this,
                task -> updateUI(null));
    }

    private void updateUI(FirebaseUser user) {
        Uri profileUri = user.getPhotoUrl();
        String name = user.getDisplayName();
        if (user != null) {
            if (profileUri != null) {
                Glide.with(this).load(profileUri).into(imageView);
            }
            profile_name.setText(name);
        }
        //final FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        // If the above were null, iterate the provider data
        // and set with the first non null data


    }
}
