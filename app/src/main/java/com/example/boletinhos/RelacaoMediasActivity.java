package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.boletinhos.adapters.MediasAdapter;
import com.example.boletinhos.models.Aluno;
import com.example.boletinhos.models.Disciplina;
import com.example.boletinhos.utils.Globais;

import java.util.ArrayList;

public class RelacaoMediasActivity extends AppCompatActivity {

    private Spinner spDisciplinas;
    private ListView lvMedias;
    private String[] vetorDisciplinas   = new String[]{"",  "Empreendedorismo",
            "Relacoes interpessoais",
            "Projeto Integrador",
            "Desenvolvimento Frameworks",
            "Gerencia de projetos",
            "Qualidade de software",
            "Mobile",
            "Estagio",
            "Desenvolvimento Web"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_medias);

        spDisciplinas = findViewById(R.id.spDisciplinas);
        lvMedias = findViewById(R.id.lvMedias);

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                vetorDisciplinas);

        spDisciplinas.setAdapter(adapterDisciplinas);

        spDisciplinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Aluno> alunos = new ArrayList<>();
                Disciplina disciplinaSelec = new Disciplina();

                if (position != 0 && Globais.listaAlunos != null){
                    for(Aluno aluno : Globais.listaAlunos){
                        for (Disciplina disciplina : aluno.getDisciplinas()){
                            if (disciplina.getDisciplina().equals((String) spDisciplinas.getItemAtPosition(position))){
                                Aluno alunoSelec = new Aluno();
                                alunoSelec.setRa(aluno.getRa());
                                alunoSelec.setNome(aluno.getNome());
                                ArrayList disciplinaNova = new ArrayList<>();
                                disciplinaNova.add(disciplina);
                                alunoSelec.setDisciplinas(disciplinaNova);
                                alunos.add(alunoSelec);
                                disciplinaSelec = disciplina;
                            }
                        }
                    }
                    lvMedias.setAdapter(new MediasAdapter(RelacaoMediasActivity.this, alunos, disciplinaSelec));
                } else {
                    lvMedias.setAdapter(new MediasAdapter(RelacaoMediasActivity.this, new ArrayList<>(), new Disciplina()));
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}