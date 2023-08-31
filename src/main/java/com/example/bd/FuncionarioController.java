package com.example.bd;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private final List<Funcionario> funcionarios;
    private Context contexto;
    private static FuncionarioController instancia = null;

    // Criar uma instância do Controller
    private FuncionarioController(Context contexto)
    {
        this.contexto = contexto;
        funcionarios = new ArrayList<>();
    }

    // Criar uma instância do Controller
    public static FuncionarioController getInstancia(Context contexto){
        if (instancia == null)
            instancia = new FuncionarioController(contexto);
        return instancia;
    }

    //Reutiliza os métodos do Repositório para cadastro
    public boolean cadastrar(Funcionario funcionario){
        FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio(contexto);
        long resultado = funcionarioRepositorio.inserir(funcionario);
        if (resultado != -1)
            return true;
        else
            return false;
    }

    //Reutiliza os métodos do Repositório para consulta
    public List<Funcionario> buscarTodos(){
        FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio(contexto);
        List<Funcionario> funcionarios = funcionarioRepositorio.buscarTodosFuncionarios();
        return funcionarios;
    }

    public Funcionario buscarPorPosicao(int posicao)
    {
        return funcionarios.get(posicao);
    }

    //Apaga tudo e adiciona de novo
    public void atualizarLista(){
        funcionarios.clear();
        funcionarios.addAll(buscarTodos());
    }



}