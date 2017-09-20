package com.atl.melissasoft.atl_v10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;


public class Logros extends Fragment {

    public Logros() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se muestra el título de la vista
        getActivity().setTitle("Logros");
    }

    int logros = 0;
    int numLogrosPorFila = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT Valor FROM Usuario WHERE Atributo like 'logros';";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int valorColumn = cursor.getColumnIndex("Valor");
        logros = Integer.parseInt(cursor.getString(valorColumn));

        //rows = logros > numLogrosPorFila ? rows + 1 : rows;
        View root = inflater.inflate(R.layout.fragment_logros, null);
        TableLayout layout = (TableLayout) root;

        int rows = logros / numLogrosPorFila;
        for (int i = 0; i < rows; i++) {
            layout.addView(crearTableRowCompletas());
        }

        rows = logros - (rows * numLogrosPorFila);
        layout.addView(crearTableRowRestante(rows));


        // Inflate the layout for this fragment
        return root;
    }

    public ImageView crearImageView() {
        ImageView logro = new ImageView(getActivity());
        logro.setImageResource(R.mipmap.ic_logros);
        return logro;
    }

    public TableRow crearTableRowCompletas() {
        TableRow row = new TableRow(getActivity());
        for (int i = 0; i < numLogrosPorFila; i++) {
            row.addView(crearImageView());
        }
        return row;
    }

    public TableRow crearTableRowRestante(int logrosRestantes) {
        TableRow row = new TableRow(getActivity());
        for (int i = 0; i < logrosRestantes; i++) {
            row.addView(crearImageView());
        }
        return row;
    }
}
