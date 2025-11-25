package com.example.balamicurrencyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewRates;
    private EditText edtFilter;

    private ArrayList<String> rateList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRates = findViewById(R.id.listViewRates);
        edtFilter = findViewById(R.id.edtFilter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rateList);
        listViewRates.setAdapter(adapter);

        // Load currency rates
        new DataLoader(this).execute("https://api.exchangerate.host/latest");

        // Filtering functionality
        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    // Called from DataLoader when the API data finishes downloading
    public void updateRates(ArrayList<String> newRates) {
        rateList.clear();
        rateList.addAll(newRates);
        adapter.notifyDataSetChanged();
    }
}
