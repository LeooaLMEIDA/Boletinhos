package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.boletinhos.adapters.AlunoAdapter;
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

    private String[] vetorBimestres     = new String[]{"","1° Bimestre","2° Bimestre","3° Bimestre","4° Bimestre"};
    private String[] vetorDisciplinas   = new String[]{"",  "Empreendedorismo",
            "Relacoes interpessoais",
            "Projeto Integrador",
            "Desenvolvimento Frameworks",
            "Gerencia de projetos",
            "Qualidade de software",
            "Mobile",
            "Estagio",
            "Desenvolvimento Web"};
    private ArrayList<Aluno>    alunos = new ArrayList<>();
    private Disciplina          disciplinaSelec = new Disciplina();
    private Integer             posDisciplina = -1;
    private Integer             bimestreSelec = -1;
    private Aluno               aluno = new Aluno();
    private AlunoAdapter        alunoAdapter;
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

        if (alunos.size() == 0){
            alunoAdapter = new AlunoAdapter(MainActivity.this, Globais.listaAlunos);
        }

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edRa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strRa = edRa.getText().toString().trim();

                if (!strRa.equals("") && Globais.listaAlunos != null){
                    Integer intRa = Integer.parseInt(strRa);
                    if (!edNome.isEnabled()){
                        edNome.setText("");
                    }
                    edNome.setEnabled(true);

                    for (Aluno aluno : Globais.listaAlunos) {
                        System.out.println(aluno.getRa());
                        System.out.println(edRa.getText().toString());
                        if (String.valueOf(aluno.getRa()).equals(edRa.getText().toString())){
                            edNome.setText(aluno.getNome());
                            edNome.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edNota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edNota.getText().toString().equals("")) {
                    if (Integer.parseInt(edNota.getText().toString()) < 0 || Integer.parseInt(edNota.getText().toString()) > 10) {
                        edNota.setError("Informe uma Nota válida para adicionar!");
                        edNota.setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRa = edRa.getText().toString();
                String strNome = edNome.getText().toString();
                String strNota = edNota.getText().toString();

                if (
                        !strRa.equals("") &&
                        !strNome.equals("") &&
                        !strNota.equals("") &&
                        posDisciplina > 0 &&
                        bimestreSelec >= 0
                ){
                    if (Double.parseDouble(edNota.getText().toString()) >= 0 && Double.parseDouble(edNota.getText().toString()) <= 10) {
                        if (Globais.listaAlunos == null) {
                            Globais.listaAlunos = new ArrayList<>();
                        }

                        Aluno alunoSelecionado = new Aluno();
                        for(int i = 0; i < Globais.listaAlunos.size(); i++){
                            if(edRa.getText().toString().equals(Globais.listaAlunos.get(i).getRa())){
                                alunoSelecionado = Globais.listaAlunos.get(i);
                            }
                        }
                        Aluno aluno = new Aluno();
                        if(!String.valueOf(alunoSelecionado.getRa()).equals("")){
                            Globais.listaAlunos.remove(alunoSelecionado);
                            aluno = alunoSelecionado;
                        }else{
                            aluno.setRa(Integer.parseInt(edRa.getText().toString()));
                            aluno.setNome(edNome.getText().toString());
                        }

                        aluno.setRa(Integer.parseInt(strRa));
                        aluno.setNome(strNome);
                        boolean achou = false;

                        for (Disciplina auxDisciplina : aluno.getDisciplinas()) {
                            if (auxDisciplina.getDisciplina().equals(disciplinaSelec.getDisciplina())) {
                                achou = true;
                                break;
                            }
                        }

                        if (!achou) {
                            aluno.getDisciplinas().add(disciplinaSelec);
                        }

                        disciplinaSelec.getNotas()[bimestreSelec] = Double.parseDouble(edNota.getText().toString());

                        achou = false;

                        for (Aluno auxAluno:alunos) {
                            if (auxAluno.getRa() == aluno.getRa()){
                                achou = true;
                            }
                        }

                        if (!achou){
                            alunos.add(aluno);
                            Globais.listaAlunos.add(aluno);
                        }

                        Toast.makeText(MainActivity.this, "A nota da disciplina " +
                                disciplinaSelec.getDisciplina() + " do aluno " +
                                aluno.getNome() + " foi adicionada com sucesso!", Toast.LENGTH_SHORT).show();

                        aluno = new Aluno();
                        System.out.println(alunos.size());
                        System.out.println(Globais.listaAlunos.size());
                        edRa.setText("");
                        edNome.setText("");
                        spDisciplinas.setSelection(0);
                        edNota.setText("");
                        spBimestre.setSelection(0);

                        disciplinaSelec = new Disciplina();
                        posDisciplina = -1;
                        bimestreSelec = -1;

                    } else {
                        edNota.setError("Informe uma Nota válida para adicionar!");
                    }

                } else {
                    if (strRa.equals("")){
                        edRa.setError("Informe um RA válido para adicionar!");
                    }
                    if (strNome.equals("")){
                        edNome.setError("Informe um Nome válido para adicionar!");
                    }
                    if (strNota.equals("")){
                        edNota.setError("Informe uma Nota válida para adicionar!");
                    }
                    if (posDisciplina <= 0){
                        Toast.makeText(MainActivity.this,"Informe uma Disciplina válida para adicionar!",Toast.LENGTH_SHORT).show();
                    }
                    if (posDisciplina < 0){
                        Toast.makeText(MainActivity.this,"Informe um Bimestre válido para adicionar!",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btVerNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListaAlunos();
            }
        });

        btVerMedias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListaMedias();
            }
        });

    }

    private void abrirListaAlunos(){
        Intent intent = new Intent(this,
                RelacaoNotasActivity.class);

        startActivity(intent);

    }

    private void abrirListaMedias(){
        Intent intent = new Intent(this,
                RelacaoMediasActivity.class);

        startActivity(intent);

    }
}