package com.example.bd;
import android.graphics.Bitmap;

import java.util.Date;

public class Funcionario {

    private long id;
    private String nome;
    private String cargo;
    private float salario;
    private Date dataDeNascimento;
    private Bitmap foto;

    public Funcionario(){   }

    public Funcionario(long id, String nome, String cargo, float salario, Date dataDeNascimento, Bitmap foto){
        this.id = id;
        setNome(nome);
        setCargo(cargo);
        setSalario(salario);
        setDataDeNascimento(dataDeNascimento);
        setFoto(foto);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome.trim().toUpperCase();
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}