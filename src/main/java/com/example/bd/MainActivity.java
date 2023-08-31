package com.example.bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listViewListaFuncionarios;
    private ListaFuncionariosAdapter listaFuncionariosAdapter;
    private Button buttonIniciaTelaCadastro;
    private FuncionarioController funcionarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        funcionarioController = FuncionarioController.getInstancia(this);

        listViewListaFuncionarios = findViewById(R.id.activity_main_listview_funcionarios);
        listaFuncionariosAdapter = new ListaFuncionariosAdapter(this);
        listViewListaFuncionarios.setAdapter(listaFuncionariosAdapter);

        buttonIniciaTelaCadastro = findViewById(R.id.activity_main_iniciarCadastro);
        buttonIniciaTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroFuncionarioActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        listaFuncionariosAdapter.atualizarLista();
    }
}