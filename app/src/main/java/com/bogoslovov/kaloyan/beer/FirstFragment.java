package com.bogoslovov.kaloyan.beer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bogoslovov.kaloyan.beer.data.BreweryDB;

import static android.R.attr.data;

/**
 * Created by kaloqn on 3/21/17.
 */

public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        initListView(rootView);

        return rootView;
    }

    private void initListView(final View rootView){

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                getContext(),
                R.layout.beer_name,
                R.id.beer_name_text_view,
                new String[]{"Naughty 90"}
        );

        ListView firstListView = (ListView) rootView.findViewById(R.id.first_list_view);
        TextView empty = (TextView) rootView.findViewById(R.id.first_empty);
        firstListView.setEmptyView(empty);
        firstListView.setAdapter(adapter);

        firstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(),DetailsActivity.class);
            intent.putExtra("url",Constants.BEER_URL);
            startActivity(intent);
            }
        });

    }
}
