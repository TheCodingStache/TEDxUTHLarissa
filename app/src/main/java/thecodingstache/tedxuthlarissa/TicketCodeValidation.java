//package thecodingstache.tedxuthlarissa;
//
//import android.os.Bundle;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import thecodingstache.tedxuthlarissa.Fragment.ProfileFragment;
//
//public class TicketCodeValidation extends AppCompatActivity {
//    Class fragmentClass;
//    public static Fragment mFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        fragmentClass = ProfileFragment.class;
//        setContentView(R.layout.activity_ticket_code_validation);
//        EditText ed = findViewById(R.id.e);
//        ed.setOnClickListener(v -> {
//            try {
//                mFragment = (Fragment) fragmentClass.newInstance();
//            } catch (IllegalAccessException | InstantiationException e) {
//                e.printStackTrace();
//            }
//            if (mFragment != null) {
//                Bundle args = new Bundle();
//                String x = ed.getText().toString();
//                args.putString("qr", x);
//                FragmentManager fragmentManager = mFragment.getParentFragmentManager();
//                fragmentManager.beginTransaction()
//                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                        .replace(R.id.frameLayout, mFragment).commit();
//            }
//
//        });
//
//    }
//}
