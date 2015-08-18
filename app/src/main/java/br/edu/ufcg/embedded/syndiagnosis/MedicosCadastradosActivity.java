package br.edu.ufcg.embedded.syndiagnosis;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.Adapters.MedicosCadastradosAdapter;
import br.edu.ufcg.embedded.syndiagnosis.DAO.medicosDAO;
import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;


public class MedicosCadastradosActivity extends ActionBarActivity {
       private ListView listaDeMedicosCadastrados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_cadastrados);

        listaDeMedicosCadastrados = (ListView) findViewById(R.id.lista_medicos_cadastrados);

        // Consulta os objetos cadastrados no banco de dados através do DAO
        medicosDAO dao = new medicosDAO(getApplicationContext());
        // Guarda os objetos consultados em uma List
        final List<Medico> medicosCadastrados = dao.listaTodos();
        // Instancia o Adapter que irá adaptar os dados na ListView
        ArrayAdapter<Medico> adapter = new MedicosCadastradosAdapter(this,
                android.R.layout.simple_list_item_1, medicosCadastrados);
        /**
         * Define o Adapter que irá adaptar os dados consultados no banco de dados
         * à nossa ListView do Resource Layout.
         *
         * @param adapter
         */
        listaDeMedicosCadastrados.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicos_cadastrados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
