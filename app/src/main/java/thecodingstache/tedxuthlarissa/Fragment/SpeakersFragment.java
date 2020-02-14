package thecodingstache.tedxuthlarissa.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thecodingstache.tedxuthlarissa.ListAdapter.MyAdapter;
import thecodingstache.tedxuthlarissa.Model.Speakers;
import thecodingstache.tedxuthlarissa.R;

public class SpeakersFragment extends Fragment {
    List<Speakers> mSpeakersArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speakers, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        getActivity().setTitle("Speakers");
        mSpeakersArrayList = new ArrayList<>();
        mSpeakersArrayList.add(new Speakers("Achilleas Gravanis",
                "Professor of Pharmacology Faculty of Medicine University of Crete / Collaborative Researcher IMBB-FORTH"
                , R.drawable.gravanis, "Στους εξι δρόμους"));
        mSpeakersArrayList.add(new Speakers("Fotis Filippopoulos",
                "Corporate Innovation Specialist"
                , R.drawable.fotis, "The Anatomy of Momentum Mindset"));
        mSpeakersArrayList.add(new Speakers("Marina Hatsopoulos",
                "Board Chair, Levitronix Technologies"
                , R.drawable.marina, "From the FAshes of Crisis Arises Opportunity"));
        mSpeakersArrayList.add(new Speakers("Constantine Venetopoulos",
                "Director"
                , R.drawable.venetopoulos, "The Anatomy of Momentum Mindset"));
        mSpeakersArrayList.add(new Speakers("Conor McGann",
                "Mobile UX & Retail Specialist at Google Ireland"
                , R.drawable.conor, "TBA"));
        mSpeakersArrayList.add(new Speakers("Eleftherios Beltsios",
                "MD Candidate"
                , R.drawable.beltsios, "Building the common space of Scientific Communication:"));
        mSpeakersArrayList.add(new Speakers("Lucy Xu",
                "Founder, The Port"
                , R.drawable.lucy, "What is your Odyssey? The power of crossing worlds"));
        mSpeakersArrayList.add(new Speakers("Orestis Omran",
                "Counsel Head of EU-Greek Practice"
                , R.drawable.orestis, "TBA"));
        mSpeakersArrayList.add(new Speakers("Christos Tsagkaris",
                "MD Candidate"
                , R.drawable.tsagkaris, "Building the common space of Scientific Communication:\n" +
                "Perspectives from the European Code Against Cancer"));
        mSpeakersArrayList.add(new Speakers("Konstantinos Giamalis",
                "CPO @ efood (Chief Product Officer)"
                , R.drawable.giammalis, "An Unusual Story of IMPEL"));
        mSpeakersArrayList.add(new Speakers("Eleni Matraki",
                "Lawyer/ Accredited Life, Wellness & Business Coach"
                , R.drawable.matraki, "Mind The Mindset"));
        mSpeakersArrayList.add(new Speakers("Valentina Kordi",
                "Mindset & High-Performance Coach for Executives, Entrepreneurs and Teams"
                , R.drawable.valentina, "Your vehicle to success: How to make your dream possible?"));
        mSpeakersArrayList.add(new Speakers("Zanettos Louka",
                "Editor/Owner of Intelligence Media"
                , R.drawable.zanettos, "(ΔΕΝ) Θα τα καταφέρεις"));
        mSpeakersArrayList.add(new Speakers("Vassilis Gerogiannis",
                "Professor of UTH Larissa faculty of Digital Systems"
                , R.drawable.gerogiannis, "Το Επόμενο Βήμα"));
        mSpeakersArrayList.add(new Speakers("Dimitris Koutsioulis",
                "CEO, EnzyQuest"
                , R.drawable.speakers, "TBA"));


        MyAdapter listAdapter = new MyAdapter(mSpeakersArrayList,SpeakersFragment.this);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        return view;
    }
}
