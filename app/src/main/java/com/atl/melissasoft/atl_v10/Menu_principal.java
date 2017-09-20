package com.atl.melissasoft.atl_v10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Menu_principal extends Fragment {
    public Menu_principal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se muestra el título de la vista
        getActivity().setTitle("Inicio");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        Button btn_diccionario_menu = (Button) view.findViewById(R.id.btn_diccionario_menu);
        Button btn_lecciones_menu = (Button) view.findViewById(R.id.btn_lecciones_menu);
        Button btn_logros_menu = (Button) view.findViewById(R.id.btn_logros);

        View.OnClickListener click_listener_diccionario = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().setTitle("Diccionario");
                FragmentTransaction fragmentTrans = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame_container, new Diccionario());
                fragmentTrans.commit();
            }
        };

        View.OnClickListener click_listener_lecciones = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().setTitle("Lecciones");
                FragmentTransaction fragmentTrans = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame_container, new Lecciones());
                fragmentTrans.commit();
            }
        };

        View.OnClickListener click_listener_logros = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().setTitle("Logros");
                FragmentTransaction fragmentTrans = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame_container, new Logros());
                fragmentTrans.commit();
            }
        };

        btn_diccionario_menu.setOnClickListener(click_listener_diccionario);
        btn_lecciones_menu.setOnClickListener(click_listener_lecciones);
        btn_logros_menu.setOnClickListener(click_listener_logros);

        /*
        btn_diccionario_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTrans = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame_container, new Diccionario());
                fragmentTrans.commit();
            }
        });
        */


        // Inflate the layout for this fragment
        return view;
    }
}
