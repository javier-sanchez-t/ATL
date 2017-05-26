package com.atl.melissasoft.atl_v10;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Lecciones_nivel_basico extends Fragment {
    public Lecciones_nivel_basico() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecciones_nivel_basico, container, false);
        Button btn_correcto = (Button) view.findViewById(R.id.btn_correcto);
        Button btn_incorrecto1 = (Button) view.findViewById(R.id.btn_incorrecto1);
        Button btn_incorrecto2 = (Button) view.findViewById(R.id.btn_incorrecto2);
        Button btn_incorrecto3 = (Button) view.findViewById(R.id.btn_incorrecto3);

        btn_correcto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Respuesta correcta! :D", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("Valor","1");
                db.update("Usuario", cv, "Atributo like 'logros'", null);
            }
        });


        View.OnClickListener onlick_incorrecto = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Respuesta incorrecta! XD", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("Valor","0");
                db.update("Usuario", cv, "Atributo like 'logros'", null);

            }
        };

        btn_incorrecto1.setOnClickListener(onlick_incorrecto);
        btn_incorrecto2.setOnClickListener(onlick_incorrecto);
        btn_incorrecto3.setOnClickListener(onlick_incorrecto);



        // Inflate the layout for this fragment
        return view;
    }
}
