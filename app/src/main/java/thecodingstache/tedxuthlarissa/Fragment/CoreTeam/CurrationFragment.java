package thecodingstache.tedxuthlarissa.Fragment.CoreTeam;

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

import thecodingstache.tedxuthlarissa.ListAdapter.TeamAdapter;
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class CurrationFragment extends Fragment {
    List<Team> curration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curration, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_curration);
        curration = new ArrayList<>();
        curration.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.speaker));
        curration.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.calendar));
        TeamAdapter listAdapter = new TeamAdapter(curration, CurrationFragment.this);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        return view;
    }
}