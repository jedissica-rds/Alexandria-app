package com.example.bd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioRepositorio {

    private DBHelper helper;

    public FuncionarioRepositorio(Context context){
        helper = new DBHelper(context);
    }


    //Método de inserção no Banco de Dados
    public long inserir(Funcionario funcionario){

        // Cria instância do database
        SQLiteDatabase db = helper.getWritableDatabase();

        // Cria instância do ConbtentValue onde os valores vão ser armazenados
        ContentValues valores = new ContentValues();

        // Armazena o valor através do método Get e anuncia a coluna da tabela do banco
        valores.put(DBHelper.COLUNA_NOME_FUNCIONARIO, funcionario.getNome());
        valores.put(DBHelper.COLUNA_CARGO_FUNCIONARIO, funcionario.getCargo());
        valores.put(DBHelper.COLUNA_SALARIO_FUNCIONARIO, funcionario.getSalario());
        valores.put(DBHelper.COLUNA_DATADENASCIMENTO_FUNCIONARIO, funcionario.getDataDeNascimento().getTime());

        // Criação de um "stream" para armazenamento de um vetor de bytes que simboliza uma imagem
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Compactando uma imagem (mapa de bits) em um vetor de bytes para ser armazenado no "stream"
        funcionario.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);

        // Adiciona a foto no Banco
        valores.put(DBHelper.COLUNA_FOTO_FUNCIONARIO, stream.toByteArray());

        // ID ????????
        long id = db.insert(DBHelper.TABELA_FUNCIONARIO, null, valores);

        // Fechando o banco
        db.close();

        // Retornando o ID ??????
        return id;
    }


    // Método de acesso a todos os dados do banco
    public List<Funcionario> buscarTodosFuncionarios(){

        // Cria instância do database
        SQLiteDatabase db = helper.getReadableDatabase();

        // Comando SQL ao ser processado
        String sql ="SELECT * FROM " + DBHelper.TABELA_FUNCIONARIO + " ORDER BY " + DBHelper.COLUNA_NOME_FUNCIONARIO;

        // Coloca o comando sql para rodar no banco ?????????
        Cursor cursor = db.rawQuery(sql, null);

        // Criação de array para armazenar os dados do banco
        List<Funcionario> funcionarios = new ArrayList<>();

        // loop para acessar os dados com o cursor
        while(cursor.moveToNext()){

            //acessando as colunas a partir do int
            long id = cursor.getLong(0);
            String nome = cursor.getString(1);
            String cargo = cursor.getString(2);
            float salario = cursor.getFloat(3);
            Date dataDeNascimento = new Date(cursor.getLong(4));

            // o vetor de byte armazena o stream de bytes salvo no banco previamente
            byte[] stream = cursor.getBlob(5);

            // Transforma o vetor de bytes em uma foto (mapa de bits)
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);

            // Criação de um objeto Funcionario para coletar os dados do banco para ser exibidos na lista
            Funcionario funcionario = new Funcionario(id, nome, cargo, salario, dataDeNascimento, foto);

            //Adição do funcionario na Lista de Funcionario para ser exibido
            funcionarios.add(funcionario);
        }

        // Cursor e Banco fechados
        cursor.close();
        db.close();

        //Array de Funcionarios retornando
        return funcionarios;
    }


}