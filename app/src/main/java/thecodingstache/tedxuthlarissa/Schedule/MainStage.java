package thecodingstache.tedxuthlarissa.Schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thecodingstache.tedxuthlarissa.ListAdapter.MainStageAdapter;
import thecodingstache.tedxuthlarissa.Model.Schedule;
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class MainStage extends Fragment {
    List<Schedule> mSchedules;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSchedules = new ArrayList<>();
        mSchedules.add(new Schedule("Registration Desk Opens","Arrive at the venue and get checked in","09:00"));
        mSchedules.add(new Schedule("Doors Open","Doors open 20 mins before registration ends","10:40"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));
        mSchedules.add(new Schedule("hahahah","hahahahahahaha","12:12"));


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stage_main, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_main_stage);
        MainStageAdapter listAdapter = new MainStageAdapter(mSchedules, MainStage.this);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
