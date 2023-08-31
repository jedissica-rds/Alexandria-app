package com.example.bd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NOME = "GestaoFuncionario";
    private static final int VERSAO = 1;

    public static final String TABELA_FUNCIONARIO = "Funcionario";
    public static final String COLUNA_ID_FUNCIONARIO = "_id";
    public static final String COLUNA_NOME_FUNCIONARIO = "nome";
    public static final String COLUNA_CARGO_FUNCIONARIO = "cargo";
    public static final String COLUNA_SALARIO_FUNCIONARIO = "salario";
    public static final String COLUNA_DATADENASCIMENTO_FUNCIONARIO = "dataDeNascimento";
    public static final String COLUNA_FOTO_FUNCIONARIO = "foto";

    public DBHelper(Context contexto){
        super(contexto,DB_NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_FUNCIONARIO+"(" +
                COLUNA_ID_FUNCIONARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME_FUNCIONARIO + " TEXT NOT NULL, " +
                COLUNA_CARGO_FUNCIONARIO + " TEXT NOT NULL, " +
                COLUNA_SALARIO_FUNCIONARIO + " FLOAT NOT NULL, " +
                COLUNA_DATADENASCIMENTO_FUNCIONARIO + " NUMERIC NOT NULL, " +
                COLUNA_FOTO_FUNCIONARIO + " BLOB " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_FUNCIONARIO + ";";
        db.execSQL(sql);
        onCreate(db);
    }
}