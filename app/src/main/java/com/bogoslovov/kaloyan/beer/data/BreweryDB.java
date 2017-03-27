package com.bogoslovov.kaloyan.beer.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bogoslovov.kaloyan.beer.Constants;
import com.bogoslovov.kaloyan.beer.R;
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
        Log.i("URL",url);
        Response response=null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            response = client.newCall(request).execute();
            if (response.code()==200) {
                setDataDTO(response);
            }else{
                Toast.makeText(activity, "Error:"+response.code(),Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(activity, "IOException",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return response;
    }

    private void setFragment(Object object){
        String frag = object.toString();
        if (Constants.BEER_URL.equals(frag)){
            fragment=1;
        }else if(Constants.GUILD_URL.equals(frag)){
            fragment=2;
        }
    }

    private void setDataDTO(Response response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (fragment) {
                case 1:
                    MainDTO beerData = objectMapper.readValue(response.body().string(), MainDTO.class);
                    data = beerData.getData();
                    break;
                case 2:
                    MainDTO informationData = objectMapper.readValue(response.body().string(), MainDTO.class);
                    data = informationData.getData();
                    break;
                default:
                    Log.e("error","no fragment is specified");
                    Toast.makeText(activity, "No fra",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(activity, "IOException",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Response response) {

        int responseCode = response.code();
        switch(responseCode){
            case 200:
                TextView title = (TextView) activity.findViewById(R.id.beer_title);
                TextView information = (TextView) activity.findViewById(R.id.beer_information);
                title.setText(data.getName());
                information.setText(data.getDescription());
                break;
            default:
                Toast.makeText(activity, "Error:"+response.code(),Toast.LENGTH_SHORT).show();
        }
    }
}
