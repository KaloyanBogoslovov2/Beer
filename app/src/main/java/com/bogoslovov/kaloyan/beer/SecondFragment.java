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

import com.bogoslovov.kaloyan.beer.data.BreweryDB;

/**
 * Created by kaloqn on 3/21/17.
 */

public class SecondFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_second, container, false);

        initListView(rootView);

        return rootView;
    }

    private void initListView(final View rootView){

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                getContext(),
                R.layout.beer_name,
                R.id.beer_name_text_view,
                new String[]{"Aloha Brewers Guild"}
        );

        ListView secondListView = (ListView) rootView.findViewById(R.id.second_list_view);
        TextView empty = (TextView) rootView.findViewById(R.id.second_empty);
        secondListView.setEmptyView(empty);
        secondListView.setAdapter(adapter);

        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(),DetailsActivity.class);
            intent.putExtra("url",Constants.GUILD_URL);
            startActivity(intent);
            }
        });

    }

}
