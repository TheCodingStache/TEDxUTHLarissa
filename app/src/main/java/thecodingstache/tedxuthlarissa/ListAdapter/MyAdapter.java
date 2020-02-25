package thecodingstache.tedxuthlarissa.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thecodingstache.tedxuthlarissa.Fragment.SpeakersFragment;
import thecodingstache.tedxuthlarissa.Model.Speakers;
import thecodingstache.tedxuthlarissa.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ListViewHolder> {
    private List<Speakers> mSpeakersList;
    private SpeakersFragment mContext;

    public MyAdapter(List<Speakers> speakersList, SpeakersFragment mContext) {
        this.mSpeakersList = speakersList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaker_item, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Speakers speakers = mSpeakersList.get(position);
//        holder.name.setText(speakers.getName());
//        holder.occupation.setText(speakers.getOccupation());
        holder.speakers.setImageResource(speakers.getImage());
        holder.title.setText("TEDx Talk\n" + speakers.getTitle());
    }


    @Override
    public int getItemCount() {
        return mSpeakersList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView speakers;
        private TextView name;
        private TextView occupation;
        private TextView title;

        public ListViewHolder(View view) {
            super(view);
            speakers = view.findViewById(R.id.speakers);
//            name = view.findViewById(R.id.name);
//            occupation = view.findViewById(R.id.occupation);
            title = view.findViewById(R.id.tedxTitle);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
