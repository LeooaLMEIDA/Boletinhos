package com.example.boletinhos.models;

public class Disciplina {
    private String disciplina;
    private Double[] notas = new Double[4];

    public Disciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina() {
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double[] getNotas() {
        return notas;
    }

    public void setNotas(Double[] notas) {
        this.notas = notas;
    }

    public Double calculaMedia(){
        return (notas[0] + notas[1] + notas[2] + notas[3]) / 4;
    }
}
