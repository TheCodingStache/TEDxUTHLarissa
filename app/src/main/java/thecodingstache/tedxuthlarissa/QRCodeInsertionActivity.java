//package thecodingstache.tedxuthlarissa;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//import java.io.Serializable;
//
//import thecodingstache.tedxuthlarissa.Database.DBHelper;
//
//public class QRCodeInsertionActivity extends AppCompatActivity implements Serializable {
//    Button scan;
//    Button enterTicket;
//    IntentResult intentResult;
//    RelativeLayout relativeLayout;
//    DBHelper helper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qrcode_insertion);
//        relativeLayout = new RelativeLayout(this);
//        relativeLayout = findViewById(R.id.relativeqr);
//        scan = findViewById(R.id.scan);
//        enterTicket = findViewById(R.id.enterTicket);
//        helper = new DBHelper(this);
//        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
//        intentIntegrator.setBeepEnabled(true);
//        intentIntegrator.setCameraId(0);
//        intentIntegrator.setOrientationLocked(true);
//        intentIntegrator.setCaptureActivity(CaptureActivityPortrait.class);
//        scan.setOnClickListener(v -> {
//            intentIntegrator.initiateScan();
//
//        });
//
//        enterTicket.setOnClickListener(v -> insertCode());
//    }
//
//    private void insertCode() {
//        Intent ticketCode = new Intent(getApplicationContext(), TicketCodeValidation.class);
//        startActivity(ticketCode);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//}
