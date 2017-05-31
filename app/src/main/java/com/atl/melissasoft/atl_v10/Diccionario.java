package com.atl.melissasoft.atl_v10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class Diccionario extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static ArrayList<DictObjectModel> data;
    DatabaseHelper db;
    ArrayList<String> wordcombimelist;
    ArrayList<String> meancombimelist;
    LinkedHashMap<String, String> namelist;
    SearchView searchView;
    private View fragmentView;

    public Diccionario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void fetchData() {
        db = new DatabaseHelper(getActivity());
        try {

            db.createDataBase();
            db.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }

        namelist = new LinkedHashMap<>();
        int ii;
        SQLiteDatabase sd = db.getReadableDatabase();
        Cursor cursor = sd.query("Diccionario" ,null, null, null, null, null, null);
        ii=cursor.getColumnIndex("palabra_espanol");
        wordcombimelist = new ArrayList<String>();
        meancombimelist = new ArrayList<String>();
        while (cursor.moveToNext()) {
            namelist.put(cursor.getString(ii), cursor.getString(cursor.getColumnIndex("palabra_nahuatl")));
        }
        Iterator entries = namelist.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            wordcombimelist.add(String.valueOf(thisEntry.getKey()));
            meancombimelist.add("- " + String.valueOf(thisEntry.getValue()));
        }

        for (int i = 0; i < wordcombimelist.size(); i++) {
            data.add(new DictObjectModel(wordcombimelist.get(i), meancombimelist.get(i)));
        }
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_diccionario, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        db = new DatabaseHelper(getActivity());
        searchView = (SearchView) fragmentView.findViewById(R.id.searchView);
        searchView.setQueryHint("Busca aquí");
        searchView.setQueryRefinementEnabled(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DictObjectModel>();
        fetchData();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                newText = newText.toLowerCase();

                final ArrayList<DictObjectModel> filteredList = new ArrayList<DictObjectModel>();

                for (int i = 0; i < wordcombimelist.size(); i++) {
                    final String text = wordcombimelist.get(i).toLowerCase();

                    if (text.contains(newText)) {
                        filteredList.add(new DictObjectModel(wordcombimelist.get(i), meancombimelist.get(i)));
                    }
                }
                adapter = new CustomAdapter(filteredList);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        // Inflate the layout for this fragment
        return fragmentView;
    }


}
