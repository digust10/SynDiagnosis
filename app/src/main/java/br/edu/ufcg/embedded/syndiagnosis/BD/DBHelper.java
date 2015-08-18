package br.edu.ufcg.embedded.syndiagnosis.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nicolas on 18/08/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Nome do banco de dados
    private static final String NOME_DO_BANCO = "medicos";
    // Versão atual do banco de dados
    private static final int VERSAO_DO_BANCO = 1;

    public DBHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    /**
     * Cria a tabela no banco de dados, caso ela não exista.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE medicos (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
                ",name TEXT NOT NULL" +
                ",email TEXT NOT NULL" +
                ",crm TEXT NOT NULL" +
                ",cpf TEXT NOT NULL" +
                ",password TEXT NOT NULL" +
                ");";
        db.execSQL(sql);

    }

    /**
     * Atualiza a estrutura da tabela no banco de dados, caso sua versão tenha mudado.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS medicos";
        db.execSQL(sql);
        onCreate(db);
    }
}
