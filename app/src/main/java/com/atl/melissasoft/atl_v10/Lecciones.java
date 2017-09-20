package com.atl.melissasoft.atl_v10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Lecciones extends Fragment {

    public Lecciones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se muestra el título de la vista
        getActivity().setTitle("Lecciones");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecciones, container, false);
        Button btn_nivel_basico = (Button) view.findViewById(R.id.btn_nivel_basico);

        View.OnClickListener click_listener_basico = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Lecciones básicas");
                FragmentTransaction fragmentTrans = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame_container, new Lecciones_nivel_basico());
                fragmentTrans.commit();
            }
        };

        btn_nivel_basico.setOnClickListener(click_listener_basico);

        // Inflate the layout for this fragment
        return view;
    }
}
