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

public class NotasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Disciplina> lista;

    public NotasAdapter(Context context, ArrayList<Disciplina> lista) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_notas,
                    viewGroup, false);
        }

        Disciplina disciplina = lista.get(posicao);
        TextView tvNotaDisciplina =  view.findViewById(R.id.tvNotaDisciplina);
        TextView tvNotaMedia = view.findViewById(R.id.tvNotaMedia);
        TextView tvBim1 = view.findViewById(R.id.tvBim1);
        TextView tvBim2 = view.findViewById(R.id.tvBim2);
        TextView tvBim3 = view.findViewById(R.id.tvBim3);
        TextView tvBim4 = view.findViewById(R.id.tvBim4);

        tvNotaDisciplina.setText(disciplina.getDisciplina());
        tvNotaMedia.setText(tvNotaMedia.getText().toString() + String.valueOf(disciplina.calculaMedia()));
        tvBim1.setText(tvBim1.getText().toString() + String.valueOf(disciplina.getNotas()[0]));
        tvBim2.setText(tvBim2.getText().toString() + String.valueOf(disciplina.getNotas()[1]));
        tvBim3.setText(tvBim3.getText().toString() + String.valueOf(disciplina.getNotas()[2]));
        tvBim4.setText(tvBim4.getText().toString() + String.valueOf(disciplina.getNotas()[3]));

        return view;

    }
}
