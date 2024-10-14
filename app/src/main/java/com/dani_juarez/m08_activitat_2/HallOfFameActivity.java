package com.dani_juarez.m08_activitat_2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HallOfFameActivity extends AppCompatActivity {

    private RecyclerView fameListRecyclerView;
    private HallOfFameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        fameListRecyclerView = findViewById(R.id.fame_list);
        fameListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Recollir el atempt list
        ArrayList<Integer> attemptsList = getIntent().getIntegerArrayListExtra("attempts_data");

        if (attemptsList == null) {
            attemptsList = new ArrayList<>();
        }

        // Adaptador RecylceVIew
        adapter = new HallOfFameAdapter(attemptsList);
        fameListRecyclerView.setAdapter(adapter);

        // Return
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());
    }
}
