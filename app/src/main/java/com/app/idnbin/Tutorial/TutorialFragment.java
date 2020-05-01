package com.app.idnbin.Tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment {

    RecyclerView video_view;
    private String[]  tutorialname = {"Basics","CFD Trading","Technical Analysis","Fundamental Analysis","Market News","Crypto Digest","About Us"};
    private int[] tutoriallist = {R.drawable.ic_basics,R.drawable.ic_cfdtrading,R.drawable.ic_technicalanalysis,R.drawable.ic_fundamentalanalysis,R.drawable.ic_marketnews,R.drawable.ic_cryptodigest,R.drawable.ic_info};
    VideoTutorialAdapter videoTutorialAdapter;
    ArrayList<String> data = new ArrayList<>(Arrays.asList(tutorialname));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        video_view = view.findViewById(R.id.video_view);
        video_view.setLayoutManager(new LinearLayoutManager(getContext()));
        videoTutorialAdapter = new VideoTutorialAdapter(getContext(),data,tutoriallist);
        video_view.setAdapter(videoTutorialAdapter);
        return view;
    }
}
