package com.example.boletinhos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.boletinhos.R;
import com.example.boletinhos.models.Aluno;
import com.example.boletinhos.models.Disciplina;

import java.util.ArrayList;

public class MediasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Aluno> lista;
    private Disciplina disciplinaSelec;

    public MediasAdapter(Context context, ArrayList<Aluno> lista, Disciplina disciplinaSelec) {
        this.context = context;
        this.lista = lista;
        this.disciplinaSelec = disciplinaSelec;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int posicao) {
        return lista.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        if (view ==null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_notas,
                    viewGroup, false);
        }

        Aluno aluno = lista.get(posicao);
        TextView tvRaAlunoDisciplina =  view.findViewById(R.id.tvRaAlunoDisciplina);
        TextView tvMediaDisciplina = view.findViewById(R.id.tvMediaDisciplina);
        TextView tvNomeAlunoDisciplina = view.findViewById(R.id.tvNomeAlunoDisciplina);
        TextView tvSituacaoAluno = view.findViewById(R.id.tvSituacaoAluno);

        tvRaAlunoDisciplina.setText(tvRaAlunoDisciplina.getText().toString() + String.valueOf(aluno.getRa()));
        tvMediaDisciplina.setText(tvMediaDisciplina.getText().toString() + String.valueOf(disciplinaSelec.calculaMedia()));
        tvNomeAlunoDisciplina.setText(tvNomeAlunoDisciplina.getText().toString() + aluno.getNome());
        if (disciplinaSelec.calculaMedia() >= 60){
            tvSituacaoAluno.setText(tvSituacaoAluno.getText().toString() + "Aprovado");
        } else {
            tvSituacaoAluno.setText(tvSituacaoAluno.getText().toString() + "Reprovado");
        }

        return view;
    }


}
