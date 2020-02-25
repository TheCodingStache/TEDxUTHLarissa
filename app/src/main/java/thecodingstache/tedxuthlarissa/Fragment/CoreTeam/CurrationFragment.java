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

import thecodingstache.tedxuthlarissa.ListAdapter.CurrationTeamAdapter;
import thecodingstache.tedxuthlarissa.ListAdapter.TeamAdapter;
import thecodingstache.tedxuthlarissa.Model.Team;
import thecodingstache.tedxuthlarissa.R;

public class CurrationFragment extends Fragment {
    List<Team> curration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curration = new ArrayList<>();
        curration.add(new Team("Kostas Kalaitzidis", "Currator/Lead Organiazer", R.drawable.kostas));
        curration.add(new Team("Ergys Plakas", "Co-organizer/Production", R.drawable.ergys));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curration, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_curration);
        CurrationTeamAdapter listAdapter = new CurrationTeamAdapter(curration, CurrationFragment.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

}