package com.example.balamicurrencyapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataLoader extends AsyncTask<String, Void, ArrayList<String>> {

    private MainActivity activity;

    public DataLoader(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<String> doInBackground(String... urls) {

        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Log.d("API_DATA", response.toString());  // DEBUG

            return Parser.parseJSON(response.toString());

        } catch (Exception e) {
            Log.e("API_ERROR", e.toString());
        }

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        activity.updateRates(result);
    }
}
