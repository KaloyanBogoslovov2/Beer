package com.bogoslovov.kaloyan.beer.data;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.bogoslovov.kaloyan.beer.Constants;
import com.bogoslovov.kaloyan.beer.DetailsActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kaloqn on 3/27/17.
 */

public class BreweryDB extends AsyncTask<Object, Object, Response> {

    private Activity activity;
    private int fragment=0;
    private DataDTO data;

    public BreweryDB(Activity activity){
        this.activity = activity;
    }

    @Override
    protected Response doInBackground(Object... params) {

        setFragment(params[0]);
        String url = Constants.BASE_URL+params[0]+Constants.BREWERY_DB_KEY+Constants.FORMAT;
        System.out.println(url);
        Response response=null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            switch (fragment){
                case 1:
                    MainDTO beerData = objectMapper.readValue(response.body().string(),MainDTO.class);
                    data = beerData.getData();
                    break;
                case 2:
                    MainDTO informationData = objectMapper.readValue(response.body().string(),MainDTO.class);
                    data = informationData.getData();
                    break;
                default:
                    System.out.println("No fragment specified error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private void setFragment(Object object){
        String frag = object.toString();
        if (frag.equals(Constants.BEER_URL)){
            fragment=1;
            System.out.println("fragment:1");
        }else if(frag.equals(Constants.GUILD_URL)){
            fragment=2;
            System.out.println("fragment:2");
        }
    }

    @Override
    protected void onPostExecute(Response response) {

        int responseCode = response.code();
        switch(responseCode){
            case 200:
                System.out.println("response 200");
                System.out.println(data.getName()+" "+data.getDescription());
                Intent intent = new Intent(activity,DetailsActivity.class);
                intent.putExtra("title",data.getName());
                intent.putExtra("information",data.getDescription());
                activity.startActivity(intent);


        }
    }
}
