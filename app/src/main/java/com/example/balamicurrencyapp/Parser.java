package com.example.balamicurrencyapp;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

    public static ArrayList<String> parseJSON(String json) {
        ArrayList<String> list = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(json);
            JSONObject rates = root.getJSONObject("rates");

            Iterator<String> keys = rates.keys();
            while (keys.hasNext()) {
                String currency = keys.next();
                double value = rates.getDouble(currency);

                list.add(currency + " – " + value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
