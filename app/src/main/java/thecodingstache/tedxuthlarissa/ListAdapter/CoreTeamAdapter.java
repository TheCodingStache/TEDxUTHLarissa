package thecodingstache.tedxuthlarissa.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CoreTeamFragment;
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class CoreTeamAdapter extends RecyclerView.Adapter<CoreTeamAdapter.ListViewHolder> {
    private List<Team> mTeamList;
    private CoreTeamFragment mContext;

    public CoreTeamAdapter(List<Team> mTeamList, CoreTeamFragment mContext) {
        this.mTeamList = mTeamList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.curration_item, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Team team = mTeamList.get(position);
        holder.name.setText("Full Name\n"+ team.getName());
        holder.title.setText("Title\n"+ team.getTitle());
        holder.team.setImageResource(team.getPhoto());
    }


    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView team;
        private TextView name;
        private TextView title;

        public ListViewHolder(View view) {
            super(view);
            team = view.findViewById(R.id.curration);
            name = view.findViewById(R.id.name);
            title = view.findViewById(R.id.title);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
