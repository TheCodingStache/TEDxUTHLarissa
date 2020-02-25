//package thecodingstache.tedxuthlarissa.ListAdapter;
//
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.text.util.Linkify;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.journeyapps.barcodescanner.BarcodeEncoder;
//
//import java.util.List;
//
//import thecodingstache.tedxuthlarissa.Model.DatabaseItem;
//import thecodingstache.tedxuthlarissa.R;
//
//
//public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.MyAdapterViewHolder> {
//    List<DatabaseItem> listItemsArrayList;
//    Context context;
//
//    public DatabaseAdapter(List<DatabaseItem> listItemsArrayList, Context context) {
//        this.listItemsArrayList = listItemsArrayList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
//        return new MyAdapterViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(MyAdapterViewHolder holder, int position) {
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        DatabaseItem listItem = listItemsArrayList.get(position);
//        holder.code.setText(listItem.getCode());
//        try {
//            BitMatrix bitMatrix = multiFormatWriter.encode(listItem.getCode(), BarcodeFormat.QR_CODE, 200, 200);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            holder.barcode.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        Linkify.addLinks(holder.code, Linkify.ALL);
//    }
//
//    @Override
//    public int getItemCount() {
//        return listItemsArrayList.size();
//    }
//
//    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
//        TextView code;
//        ImageView barcode;
//        CardView cardView;
//
//        public MyAdapterViewHolder(final View itemView) {
//            super(itemView);
//            code = itemView.findViewById(R.id.ticket);
//            barcode = itemView.findViewById(R.id.barcode);
//            cardView = itemView.findViewById(R.id.cardView);
//        }
//    }
//}
//
