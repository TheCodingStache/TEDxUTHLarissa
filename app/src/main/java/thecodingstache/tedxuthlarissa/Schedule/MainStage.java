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
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class MainStage extends Fragment {
    List<Team> directors;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        directors = new ArrayList<>();
        directors.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        directors.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));
        directors.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        directors.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));directors.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        directors.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));directors.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        directors.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));directors.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        directors.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_directors, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_directors);
        MainStageAdapter listAdapter = new MainStageAdapter(directors, MainStage.this);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
