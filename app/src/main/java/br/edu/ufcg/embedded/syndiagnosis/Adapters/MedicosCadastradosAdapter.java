package br.edu.ufcg.embedded.syndiagnosis.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;
import br.edu.ufcg.embedded.syndiagnosis.R;

/**
 * Created by Nicolas on 18/08/2015.
 */
public class MedicosCadastradosAdapter extends ArrayAdapter<Medico> {

    private final List<Medico> medicosCadastrados;
    private final Activity activity;

    public MedicosCadastradosAdapter(Activity activity, int textViewResourceId,List<Medico> medicosCadastrados) {
        super(activity, textViewResourceId, medicosCadastrados);
        this.activity = activity;
        /**
        * List de objetos do tipo ObjetoEmprestado recebida como parâmetro
        * pelo Construtor.
        */
        this.medicosCadastrados = medicosCadastrados;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
       Medico medico = medicosCadastrados.get(position);

        View view = activity.getLayoutInflater().inflate(R.layout.item_medico, null);

        TextView txtName = (TextView) view.findViewById(R.id.txt_name);
        txtName.setText(medico.getName());

        TextView txtCpf = (TextView) view.findViewById(R.id.txt_cpf);
        txtCpf.setText(medico.getCpf());


        return view;
    }

    @Override
    public long getItemId(int position) {
        return medicosCadastrados.get(position).getId();
    }
    /**
     * Retorna o número de itens que serão mostrados na ListView.
     */
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Medico getItem(int position) {
        return medicosCadastrados.get(position);
    }
}
