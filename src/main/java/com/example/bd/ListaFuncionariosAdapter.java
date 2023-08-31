package com.example.bd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListaFuncionariosAdapter extends BaseAdapter {

    Context context;
    FuncionarioController funcionarioController;
    public List<Funcionario> funcionarios;

    public ListaFuncionariosAdapter(Context context){
        this.context = context;
        funcionarioController = FuncionarioController.getInstancia(context);
        funcionarios = funcionarioController.buscarTodos();
        atualizarLista();
    }

    @Override
    public int getCount() {
        return funcionarios.size();
    }

    @Override
    public Funcionario getItem(int position) {
        return funcionarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return funcionarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context)
                .inflate(
                        R.layout.item_lista_funcionarios,
                        parent,
                        false);
        TextView textViewNome = v.findViewById(R.id.item_lista_funcionario_textView_nome);
        TextView textViewDataNascimento = v.findViewById(R.id.item_lista_funcionario_textView_dataNascimento);
        TextView textViewCargo = v.findViewById(R.id.item_lista_funcionario_textView_cargo);
        ImageView imageViewFoto = v.findViewById(R.id.item_lista_funcionario_imageView_foto);

        Funcionario funcionario = funcionarios.get(position);
        textViewNome.setText(funcionario.getNome());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        textViewDataNascimento.setText(formato.format(funcionario.getDataDeNascimento()));
        textViewCargo.setText(funcionario.getCargo());
        if (funcionario.getFoto() != null) {
            imageViewFoto.setImageBitmap(funcionario.getFoto());
        }
        return v;
    }

    public void atualizarLista(){
        funcionarios.clear();
        funcionarios.addAll(funcionarioController.buscarTodos());
        notifyDataSetChanged();
    }

}
