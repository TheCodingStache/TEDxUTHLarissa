package thecodingstache.tedxuthlarissa.ListAdapter;

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
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ListViewHolder> {
    private List<Team> mSpeakersList;
    private SpeakersFragment mContext;

    public TeamAdapter(List<Team> speakersList, SpeakersFragment mContext) {
        this.mSpeakersList = speakersList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_speakers, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Team team = mSpeakersList.get(position);
        holder.name.setText("Full Name\n"+ team.getName());
        holder.occupation.setText("Title\n"+ team.getTitle());
        holder.speakers.setImageResource(team.getPhoto());
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
            name = view.findViewById(R.id.name);
            occupation = view.findViewById(R.id.occupation);
            title = view.findViewById(R.id.tedxTitle);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}