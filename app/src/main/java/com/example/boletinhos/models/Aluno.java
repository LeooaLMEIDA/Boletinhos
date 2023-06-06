package com.example.boletinhos.models;

import java.util.ArrayList;

public class Aluno {

    private int                     ra;
    private String                  nome;
    private ArrayList<Disciplina>   disciplinas = new ArrayList<>();

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
