package com.example.bd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroFuncionarioActivity extends AppCompatActivity {

    private EditText editTextCodigo;
    private EditText editTextNome;
    private EditText editTextCargo;
    private EditText editTextSalario;
    private EditText editTextDataNascimento;
    private ImageView imageViewFoto;
    private Button btnCadastrar;

    private Bitmap fotoTirada = null;

    private final int COD_REQ_FOTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);
        fotoTirada = null;
        initComponentes();

    }

    private void initComponentes() {
        editTextCodigo = findViewById(R.id.activity_cadastro_editText_codigo);
        editTextNome = findViewById(R.id.activity_cadastro_editText_nome);
        editTextCargo = findViewById(R.id.activity_cadastro_editText_cargo);
        editTextSalario = findViewById(R.id.activity_cadastro_editText_salario);
        editTextDataNascimento = findViewById(R.id.activity_cadastro_editText_data);
        imageViewFoto = findViewById(R.id.activity_cadastro_imageView_foto);
        btnCadastrar = findViewById(R.id.activity_cadastro_button_cadastrar);

        imageViewFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCapturaDeFoto();
            }
        });

        editTextDataNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mostrarDialogParaData(editTextDataNascimento);
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarFuncionario();
                finish();
            }
        });
    }

    private void mostrarDialogParaData(EditText editTextDate) {
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+7;
        int mes = Calendar.getInstance().get(Calendar.MONTH);
        int ano = Calendar.getInstance().get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, ano, mes, dia);
        datePickerDialog.setTitle("Escolha uma data");
        datePickerDialog.show();
    }

    private void criarFuncionario() {
        try {
            long id = -1;//Integer.parseInt(editTextCodigo.getText().toString());
            String nome  = editTextNome.getText().toString();
            String cargo  = editTextCargo.getText().toString();
            float salario = Float.parseFloat(editTextSalario.getText().toString());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataDeNascimento = formato.parse(editTextDataNascimento.getText().toString());
            Funcionario funcionario = new Funcionario(id, nome, cargo, salario, dataDeNascimento, fotoTirada);
            FuncionarioController funcionarioController = FuncionarioController.getInstancia(CadastroFuncionarioActivity.this);
            if (funcionarioController.cadastrar(funcionario)){
                Log.d("Gravacao", "Ok");
            }else{
                Log.d("Gravacao", "Sem Sucesso");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void iniciarCapturaDeFoto() {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, COD_REQ_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_REQ_FOTO){
            if (resultCode == Activity.RESULT_OK){
                if (data != null) {
                    /*Uma intent mantém um Bundle que é o estado de uma Activity,
                    no caso os dados extras mantidos pela captura*/
                    Bundle extras = data.getExtras();
                    if(data.hasExtra("data")) {
                        Bitmap bitmap = (Bitmap) extras.get("data");
                        fotoTirada = bitmap;
                        imageViewFoto.setImageBitmap(fotoTirada);
                    }
                }
            }else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(CadastroFuncionarioActivity.this, "Você não capturou uma imagem", Toast.LENGTH_LONG).show();
                imageViewFoto.setImageResource(R.drawable.baseline_person_24); //Define uma imagem padrão
            }
        }
    }
}