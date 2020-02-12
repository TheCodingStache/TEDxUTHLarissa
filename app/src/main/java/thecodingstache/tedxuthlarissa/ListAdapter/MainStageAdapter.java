package thecodingstache.tedxuthlarissa.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import thecodingstache.tedxuthlarissa.Model.Schedule;
import thecodingstache.tedxuthlarissa.R;
import thecodingstache.tedxuthlarissa.Schedule.MainStage;

public class MainStageAdapter extends RecyclerView.Adapter<MainStageAdapter.ListViewHolder> {
    private List<Schedule> mSchedules;
    private MainStage mContext;

    public MainStageAdapter(List<Schedule> mSchedules, MainStage mContext) {
        this.mSchedules = mSchedules;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_stage_item, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Schedule schedule = mSchedules.get(position);
        holder.title.setText(schedule.getTitleProgram());
        holder.description.setText(schedule.getDescription());
        holder.time.setText(schedule.getTime());


    }


    @Override
    public int getItemCount() {
        return mSchedules.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private TextView time;


        public ListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_program);
            description = view.findViewById(R.id.description);
            time  =view.findViewById(R.id.timeStage);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}