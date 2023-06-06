package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.boletinhos.models.Aluno;
import com.example.boletinhos.models.Disciplina;
import com.example.boletinhos.utils.Globais;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText            edRa;
    private EditText            edNome;
    private Spinner             spDisciplinas;
    private EditText            edNota;
    private Spinner             spBimestre;
    private Button              btAdicionar;
    private Button              btVerNotas;
    private Button              btVerMedias;

    private ArrayList<Aluno>    alunos = new ArrayList<>();
    private Disciplina          disciplinaSelec = new Disciplina();
    private Integer             posDisciplina = -1;
    private Integer             bimestreSelec = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edRa = findViewById(R.id.edRa);
        edNome = findViewById(R.id.edNome);
        spDisciplinas = findViewById(R.id.spDisciplina);
        edNota = findViewById(R.id.edNota);
        spBimestre = findViewById(R.id.spBimestre);
        btAdicionar = findViewById(R.id.btAdicionar);
        btVerNotas = findViewById(R.id.btVerNotas);
        btVerMedias = findViewById(R.id.btVerMedias);

        if (Globais.listaAlunos == null) {
            Globais.listaAlunos = new ArrayList<>();
        }

        Aluno aluno = new Aluno();

        String[] vetorDisciplinas   = new String[]{"","Programação P/ Disp. Móveis"};
        String[] vetorBimestres     = new String[]{"","1° Bimestre","2° Bimestre","3° Bimestre","4° Bimestre"};

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                vetorDisciplinas);

        spDisciplinas.setAdapter(adapterDisciplinas);

        ArrayAdapter adapterBimestres = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                vetorBimestres);

        spBimestre.setAdapter(adapterBimestres);

        spDisciplinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean naoVazio = !((String) spDisciplinas.getItemAtPosition(i)).equals("");

                if (naoVazio){
                    disciplinaSelec = null;
                    for (Disciplina disciplina:aluno.getDisciplinas()) {
                        if (disciplina.getDisciplina().equals((String) spDisciplinas.getItemAtPosition(i))){
                            disciplinaSelec = disciplina;
                            break;
                        }
                    }
                    if (disciplinaSelec == null){
                        disciplinaSelec = new Disciplina((String) spDisciplinas.getItemAtPosition(i));
                        aluno.getDisciplinas().add(disciplinaSelec);
                    }
                    posDisciplina = i;
                } else if (posDisciplina >= 0) {
                    spDisciplinas.setSelection(posDisciplina);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spBimestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bimestre = (String) spBimestre.getItemAtPosition(i);

                if (!bimestre.trim().equals("")){
                    switch (bimestre){
                        case "1° Bimestre":
                            bimestreSelec = 0;
                            break;
                        case "2° Bimestre":
                            bimestreSelec = 1;
                            break;
                        case "3° Bimestre":
                            bimestreSelec = 2;
                            break;
                        case "4° Bimestre":
                            bimestreSelec = 3;
                            break;
                    }
                } else {
                    spBimestre.setSelection(bimestreSelec+1);
                }
                System.out.println(bimestreSelec);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}