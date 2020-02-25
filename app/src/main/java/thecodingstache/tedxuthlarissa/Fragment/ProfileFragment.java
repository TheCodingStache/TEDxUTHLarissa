package thecodingstache.tedxuthlarissa.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import androidmads.library.qrgenearator.QRGEncoder;
import thecodingstache.tedxuthlarissa.Database.DBHelper;
import thecodingstache.tedxuthlarissa.HomeScreen;
import thecodingstache.tedxuthlarissa.Model.DatabaseItem;
import thecodingstache.tedxuthlarissa.R;

public class ProfileFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    IntentIntegrator scan;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<DatabaseItem> arrayList;
    IntentResult intentResult;
    private static final String TAG = "Create Profile";
    private GoogleSignInClient mGoogleSignInClient;
    private TextView profile_name;
    private ImageView imageView;
    RecyclerView.LayoutManager mLayoutManager;
    private TextView logOut;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    static final int GOOGLE_SIGN_IN = 123;
    Button generate;
    DBHelper helper;
    ImageView ticket;
    String ticketText;
    Bitmap image;
    RelativeLayout mRelativeLayout;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    EditText ticketEditText;

    public ProfileFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        getActivity().setTitle("Profile");

        logOut = view.findViewById(R.id.logOut);
        mRelativeLayout = view.findViewById(R.id.fragment_profile);
        generate = view.findViewById(R.id.generate);
        ticket = view.findViewById(R.id.ticket);
        ticketEditText = view.findViewById(R.id.ticketEditText);
        helper = new DBHelper(getActivity());
        FacebookSdk.sdkInitialize(getContext());
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);
        profile_name = view.findViewById(R.id.profile_name);
        imageView = view.findViewById(R.id.profile_picture);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        if (mFirebaseUser != null) {
            String displayName = mFirebaseUser.getDisplayName();
            for (UserInfo userInfo : mFirebaseUser.getProviderData()) {
                if (displayName == null && userInfo.getDisplayName() != null) {
                    displayName = userInfo.getDisplayName();

                }
            }
        }
        logOut.setOnClickListener(v -> {
            Logout();
            facebookLogout();
            getActivity().finishAffinity();
        });
        File imgFile = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/" + getActivity().getApplicationContext().getPackageName() + "/files/TEDxQR.png");
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ticket.setImageBitmap(myBitmap);
        }
        generate.setVisibility(View.GONE);
        ticketEditText.setVisibility(View.GONE);
        ticket.setVisibility(View.GONE);
        updateUI();
        generate.setOnClickListener(v -> {
            String text = ticketEditText.getText().toString();
            try {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                storeImage(bitmap);
                ticket.setImageBitmap(bitmap);
                createUserProfile(mFirebaseUser.getDisplayName(), mFirebaseUser.getUid(), mFirebaseUser.getEmail(), text);
                Snackbar.make(mRelativeLayout, "Your ticket generated successfully!", Snackbar.LENGTH_SHORT).show();
            } catch (WriterException e) {
                e.printStackTrace();
            }


        });

        return view;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getApplicationContext().getPackageName()
                + "/files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        String mImageName = "TEDxQR" + ".png";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Logout() {
        mFirebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent openHome = new Intent(getContext(), HomeScreen.class);
            startActivity(openHome);
            getActivity().finish();
        });
    }

    private void facebookLogout() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        Intent openHome = new Intent(getContext(), HomeScreen.class);
        startActivity(openHome);
        getActivity().finish();

    }


    private void updateUI() {
        showUI();
        String facebookUserId = "";
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            for (UserInfo profile : mFirebaseUser.getProviderData()) {
                String providerId = profile.getProviderId();
                if (providerId.equals("facebook.com")) {
                    if (!mFirebaseUser.isAnonymous()) {
                        if (FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                            facebookUserId = profile.getUid();
                            if (getContext() != null) {
                                String displayName = mFirebaseUser.getEmail();
                                Uri profileUri = mFirebaseUser.getPhotoUrl();
                                String photoUrl = "";
                                if (profileUri != null) {
                                    photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";
                                }
                                profile_name.setText(displayName);
                                Glide.with(this).load(photoUrl).into(imageView);
                            }
                        }
                    }
                } else if (providerId.equals("google.com")) {
                    if (getContext() != null) {
                        String displayName = mFirebaseUser.getEmail();
                        Uri profileUri = mFirebaseUser.getPhotoUrl();
                        String photo = "";
                        if (profileUri != null) {
                            photo = profileUri.toString().replace("s96-c", "s400-c");
                        }
                        profile_name.setText(displayName);
                        Glide.with(this).load(photo).into(imageView);
                        showUI();
                    }
                }
            }
        }
    }


    private void createUserProfile(final String username, final String uid, final String email, String ticket) {
        final DatabaseReference RootRef;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("user").child(uid).exists())) {
                    for (UserInfo profile : mFirebaseUser.getProviderData()) {
                        String providerId = profile.getProviderId();
                        if (providerId.equals(profile.getProviderId())) {
                            if (!mFirebaseUser.isAnonymous()) {
                                if (getContext() != null) {
                                    HashMap<String, Object> userdataMap = new HashMap<>();
                                    userdataMap.put("username", username);
                                    userdataMap.put("uid", uid);
                                    userdataMap.put("email", email);
                                    userdataMap.put("ticket", ticket);
                                    RootRef.child("users").child(uid).updateChildren(userdataMap).addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Profile Created Successfully");
                                        } else {
                                            Log.d(TAG, "An Error Occurred");
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUI() {
        generate.setVisibility(View.VISIBLE);
        ticketEditText.setVisibility(View.VISIBLE);
        ticket.setVisibility(View.VISIBLE);
    }

    private void hideUI() {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}


//
//    private void facebookPhotoProfile() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        // find the Facebook profile and get the user's id
//        if (user != null) {
//            for (UserInfo profile : user.getProviderData()) {
//                // check if the provider id matches "facebook.com"
//                if (FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
//                    facebookUserId = profile.getUid();
//                }
//            }
//        }
//          String photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";

//        // construct the URL to the profile picture, with a custom height
//        // alternatively, use '?type=small|medium|large' instead of ?height=
//        Glide.with(this).load(photoUrl).into(imageView);
//    }
