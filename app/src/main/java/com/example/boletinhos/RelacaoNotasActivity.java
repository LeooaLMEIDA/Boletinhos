package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.boletinhos.adapters.AlunoAdapter;
import com.example.boletinhos.adapters.NotasAdapter;
import com.example.boletinhos.models.Aluno;
import com.example.boletinhos.utils.Globais;

import java.util.ArrayList;

public class RelacaoNotasActivity extends AppCompatActivity {

    private Spinner spAlunos;
    private ListView lvNotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_notas);

        spAlunos = findViewById(R.id.spAlunos);
        lvNotas = findViewById(R.id.lvNotas);

        String[] vetAlunos = new String[1];
        vetAlunos[0] = "Selecione um aluno para filtrar";

        if(Globais.listaAlunos != null){
            vetAlunos = new String[Globais.listaAlunos.size() + 1];
            vetAlunos[0] = "Selecione um aluno para filtrar";
            int index = 0;
            for(Aluno aluno : Globais.listaAlunos){
                vetAlunos[++index] = aluno.getRa() + " - " + aluno.getNome();
            }
        }

        spAlunos.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetAlunos));

        spAlunos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0 && Globais.listaAlunos != null ){
                    lvNotas.setAdapter(new NotasAdapter(RelacaoNotasActivity.this, Globais.listaAlunos.get(i-1).getDisciplinas()));
                } else if (i != 0){
                    lvNotas.setAdapter(new NotasAdapter(RelacaoNotasActivity.this, new ArrayList<>()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}