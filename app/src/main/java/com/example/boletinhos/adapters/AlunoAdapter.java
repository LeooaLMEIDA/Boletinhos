package com.example.boletinhos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.boletinhos.R;
import com.example.boletinhos.models.Aluno;

import java.util.ArrayList;

public class AlunoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Aluno> lista;

    public AlunoAdapter(Context context, ArrayList<Aluno> lista) {
        this.context = context;
        this.lista = lista;
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_item_list_aluno,
                    viewGroup, false);
        }

        Aluno aluno = lista.get(posicao);
        TextView tvRaAluno =  view.findViewById(R.id.tvRaAluno);
        TextView tvNomeAluno = view.findViewById(R.id.tvNomeAluno);

        tvRaAluno.setText(String.valueOf(aluno.getRa()));
        tvNomeAluno.setText(aluno.getNome());

        return view;

    }
}
