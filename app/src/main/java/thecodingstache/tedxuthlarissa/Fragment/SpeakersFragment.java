package thecodingstache.tedxuthlarissa.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import thecodingstache.tedxuthlarissa.ListAdapter.MyAdapter;
import thecodingstache.tedxuthlarissa.Model.Speakers;
import thecodingstache.tedxuthlarissa.R;

public class SpeakersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speakers, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        getActivity().setTitle("Speakers");
        List<Speakers> speakersArrayList = new ArrayList<>();
        speakersArrayList.add(new Speakers("Achilleas Gravanis",
                "Professor of Pharmacology Faculty of Medicine University of Crete / Collaborative Researcher IMBB-FORTH"
                , R.drawable.gravanispost, "Στους έξι δρόμους"));

//        mSpeakersArrayList.add(new Speakers("Fotis Filippopoulos",
//                "Corporate Innovation Specialist"
//                , R.drawable.fotis, "The Anatomy of Momentum Mindset"));

        speakersArrayList.add(new Speakers("Marina Hatsopoulos",
                "Board Chair, Levitronix Technologies"
                , R.drawable.marinapost, "From the Ashes of Crisis Arises Opportunity"));

        speakersArrayList.add(new Speakers("Constantine Venetopoulos",
                "Director"
                ,R.drawable.venetopoulospost, "The Anatomy of Momentum Mindset"));

        speakersArrayList.add(new Speakers("Conor McGann",
                "Mobile UX & Retail Specialist at Google Ireland"
                , R.drawable.conorpost, "TBA"));

        speakersArrayList.add(new Speakers("Eleftherios Beltsios",
                "MD Candidate"
                , R.drawable.leftpost, "Rediscover yourself by getting out of your comfort zone"));

        speakersArrayList.add(new Speakers("Lucy Xu",
                "Founder, The Port"
                , R.drawable.lucypost, "What is your Odyssey? The power of crossing worlds"));

        speakersArrayList.add(new Speakers("Orestis Omran",
                "Counsel Head of EU-Greek Practice"
                , R.drawable.orestispost, "TBA"));

        speakersArrayList.add(new Speakers("Christos Tsagkaris",
                "MD Candidate"
                ,R.drawable.tsagkarispost, "Building the common space of Scientific Communication:\n" +
                "Perspectives from the European Code Against Cancer"));

        speakersArrayList.add(new Speakers("Konstantinos Giamalis",
                "CPO @ efood (Chief Product Officer)"
                , R.drawable.giamalispost, "An Unusual Story of IMPEL"));

        speakersArrayList.add(new Speakers("Eleni Matraki",
                "Lawyer/ Accredited Life, Wellness & Business Coach"
                , R.drawable.matrakipost, "Mind The Mindset"));

        speakersArrayList.add(new Speakers("Valentina Kordi",
                "Mindset & High-Performance Coach for Executives, Entrepreneurs and Teams"
                ,R.drawable.kordipost, "Your vehicle to success: How to make your dream possible?"));

        speakersArrayList.add(new Speakers("Zanettos Louka",
                "Editor/Owner of Intelligence Media"
                , R.drawable.zanettospost, "(ΔΕΝ) Θα τα καταφέρεις"));

        speakersArrayList.add(new Speakers("Vassilis Gerogiannis",
                "Professor of UTH Larissa faculty of Digital Systems"
                ,R.drawable.gerogianpost, "Το Επόμενο Βήμα"));

        speakersArrayList.add(new Speakers("Dimitris Koutsioulis",
                "CEO, EnzyQuest"
                , R.drawable.koutsioulispost, "TBA"));


        MyAdapter listAdapter = new MyAdapter(speakersArrayList, SpeakersFragment.this);
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
