package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.boletinhos.utils.Globais;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText    edRa;
    private EditText    edNome;
    private Spinner     spDisciplinas;
    private String      disciplinaSelec;

    private Spinner     spBimestres;

    private String      bimestreSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edRa = findViewById(R.id.edRa);
        edNome = findViewById(R.id.edNome);
        spDisciplinas = findViewById(R.id.spDisciplina);

        String[] vetorDisciplinas   = new String[]{"","Programação P/ Disp. Móveis"};
        String[] vetorBimestres     = new String[]{"","1° Bimestre","2° Bimestre","3° Bimestre","4° Bimestre"};

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                vetorDisciplinas);

        spDisciplinas.setAdapter(adapter);

        spDisciplinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disciplinaSelec = (String) spDisciplinas.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spBimestres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bimestreSelec = (String) spBimestres.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (Globais.listaAlunos == null) {
            Globais.listaAlunos = new ArrayList<>();
        }

    }
}