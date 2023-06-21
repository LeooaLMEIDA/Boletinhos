package com.example.boletinhos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.boletinhos.adapters.AlunoAdapter;
import com.example.boletinhos.utils.Globais;

public class RelacaoNotasActivity extends AppCompatActivity {

    private Spinner spAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacao_notas);

        spAlunos = findViewById(R.id.spAlunos);

        spAlunos.setAdapter(new AlunoAdapter(RelacaoNotasActivity.this, Globais.listaAlunos));

    }
}