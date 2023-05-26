package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private EditText    edRa;
    private EditText    edNome;
    private Spinner     spDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edRa = findViewById(R.id.edRa);
        edNome = findViewById(R.id.edNome);
        spDisciplinas = findViewById(R.id.spDisciplina);

        String[] vetorDisciplinas = new String[]{"","Programação P/ Disp. Móveis"};

    }
}