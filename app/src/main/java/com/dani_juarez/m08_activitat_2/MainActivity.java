package com.dani_juarez.m08_activitat_2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int actual_tries = 0;
    private int random_number = 0;
    private ArrayList<Integer> attemptsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random rand = new Random();

        random_number = rand.nextInt(11);

        final Button button = findViewById(R.id.check_num_button);
        final EditText input_num = findViewById(R.id.input_num);

        //Fer clic en cas de donar un enter al teclat
        input_num.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    button.performClick();
                    return true;
                }
                return false;
            }
        });

        //Fer click per obrir el hall of fame
        Button hallOfFameButton = findViewById(R.id.hall_of_fame);

        hallOfFameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HallOfFameActivity.class);


                Collections.sort(attemptsList);
                intent.putIntegerArrayListExtra("attempts_data", attemptsList);
                startActivity(intent);
            }
        });

        //Fer click per jugar
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String input_num_value = input_num.getText().toString();

                if (!input_num_value.isEmpty()) {
                    int num_value = Integer.parseInt(input_num_value);

                    if (num_value > 10 || num_value < 0){
                        Toast.makeText(MainActivity.this, "El valor ha de ser entre el 0 y el 10", Toast.LENGTH_SHORT).show();
                    }else {

                        actual_tries++;

                        if(num_value == random_number){

                            attemptsList.add(actual_tries);

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("¡Has guanyat!")
                                    .setMessage("Ara pots tornar a intentar guaynar en menys intents \n total : " + actual_tries + " intents!")
                                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                    .show();

                            random_number = rand.nextInt(11);

                            //Funcions per actualiztar historic y texte d'amunt
                            updateActualTriesText(0);
                            updateAttemptsHistory();

                            actual_tries = 0;
                        }else {
                            if (num_value > random_number ){
                                Toast.makeText(MainActivity.this, "El numero misterios es mes petit", Toast.LENGTH_SHORT).show();
                            } else if ( num_value < random_number) {
                                Toast.makeText(MainActivity.this, "El numero misterios es mes gran", Toast.LENGTH_SHORT).show();
                            }

                            //Funcio per actualiztar texte d'amunt
                            updateActualTriesText(actual_tries);
                        }

                        input_num.setText("");
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Has d'introduir almenys un valor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateAttemptsHistory() {
        TextView historyTextView = findViewById(R.id.attempts_history);

        StringBuilder historyText = new StringBuilder("Historial d'intents: \n");
        for (int i = 0; i < attemptsList.size(); i++) {
            historyText.append("Partida ").append(i + 1).append(": ").append(attemptsList.get(i)).append(" intents\n");
        }

        historyTextView.setText(historyText.toString());
    }

    private void updateActualTriesText(Integer tries){
        TextView actual_try_text = findViewById(R.id.actual_try);

        actual_try_text.setText("Numero d'intents actuals: " + tries);
    }
}