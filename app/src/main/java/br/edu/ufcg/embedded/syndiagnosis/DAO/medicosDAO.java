package br.edu.ufcg.embedded.syndiagnosis.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.BD.DBHelper;
import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;

/**
 * Created by Nicolas on 18/08/2015.
 */
public class medicosDAO {

    private DBHelper dbHelper;
    private Context context;

    public medicosDAO(Context context) {

        dbHelper = new DBHelper(context);
        this.context = context;
    }

    /**
     * Adiciona objeto no banco de dados.
     */
    public void adiciona(Medico medico) {
        // Encapsula no objeto do tipo ContentValues os valores a serem persistidos no banco de dados
        ContentValues values = new ContentValues();
        values.put("name", medico.getName());
        values.put("email", medico.getEmail());
        values.put("cpf", medico.getCpf());
        values.put("crm", medico.getCrm());
        values.put("password", medico.getPassword());

        // Instancia uma conexão com o banco de dados, em modo de gravação
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Insere o registro no banco de dados
        long id = db.insert("medicos", null, values);
        medico.setId(id);
        // Encerra a conexão com o banco de dados
        db.close();
    }


    /**
     * Lista todos os registros da tabela “objeto_emprestado”
     */
    public List<Medico> listaTodos() {
        // Cria um List para guardar os objetos consultados no banco de dados
        List<Medico> medicos = new ArrayList<Medico>();
        // Instancia uma nova conexão com o banco de dados em modo leitura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Executa a consulta no banco de dados
        Cursor c = db.query("medicos", null, null, null, null,
                null, null);
        /**
         * Percorre o Cursor, injetando os dados consultados em um objeto
         * do tipo ObjetoEmprestado e adicionando-os na List
         */
        try {
            while (c.moveToNext()) {
                Medico medico = new Medico();
                medico.setId(c.getLong(c.getColumnIndex("_id")));
                medico.setName(c.getString(c.getColumnIndex("name")));
                medico.setEmail(c.getString(c.getColumnIndex("email")));
                medico.setCpf(c.getString(c.getColumnIndex("cpf")));
                medico.setCrm(c.getString(c.getColumnIndex("crm")));
                medico.setPassword(c.getString(c.getColumnIndex("password")));
                medicos.add(medico);
            }
        } finally {
        // Encerra o Cursor
            c.close();
        }
        // Encerra a conexão com o banco de dados
        db.close();
        // Retorna uma lista com os objetos consultados
        return medicos;
    }
}

