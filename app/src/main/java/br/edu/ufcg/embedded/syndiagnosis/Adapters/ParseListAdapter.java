package br.edu.ufcg.embedded.syndiagnosis.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.R;
import br.edu.ufcg.embedded.syndiagnosis.TestObject;

/**
 * Created by digust10 on 25/08/2015.
 */
public class ParseListAdapter extends ArrayAdapter {

    List<ParseUser> usuariosLista;
    Activity activity;

    public ParseListAdapter(Activity activity, int idLayout, List<ParseUser> list){
        super(activity, idLayout, list);
        this.activity = activity;
        usuariosLista = list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View usuarioView  = activity.getLayoutInflater().inflate(R.layout.item_parse_list, null);

        final ParseUser usuario = getItem(position);

        final CheckBox cb = (CheckBox) usuarioView.findViewById(R.id.checkboxItemParse);

        cb.setChecked(usuario.getBoolean("status"));
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView,
                                         final boolean isChecked) {
                cb.setChecked(isChecked);
                usuario.put("status",isChecked);
            }

        });

        TextView tvNome = (TextView) usuarioView.findViewById(R.id.nomeParseList);
        TextView tvEmail = (TextView) usuarioView.findViewById(R.id.emailParseList);

        tvNome.setText(usuario.getUsername());
        tvEmail.setText(usuario.getEmail());




        return usuarioView;
    }

    @Override
    public final int getCount() {
        return usuariosLista.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public final ParseUser getItem(final int position) {
        return usuariosLista.get(position);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public final long getItemId(final int position) {
        return position;
    }
}
