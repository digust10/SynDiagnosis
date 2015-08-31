package br.edu.ufcg.embedded.syndiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

import br.edu.ufcg.embedded.syndiagnosis.DAO.medicosDAO;
import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;

import static br.edu.ufcg.embedded.syndiagnosis.Util.Validacao.checaEntradaEmBranco;
import static br.edu.ufcg.embedded.syndiagnosis.Util.Validacao.validaCPF;
import static br.edu.ufcg.embedded.syndiagnosis.Util.Validacao.validaEmail;
import static br.edu.ufcg.embedded.syndiagnosis.Util.Validacao.validaNome;
import static br.edu.ufcg.embedded.syndiagnosis.Util.Validacao.validaTamanhoSenha;

public class CadastroMedicoActivity extends AppCompatActivity {

    private Medico medico;
    private EditText edtName;
    private EditText edtCpf;
    private EditText edtCrm;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medico);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtCpf = (EditText) findViewById(R.id.edt_cpf);
        edtCrm = (EditText) findViewById(R.id.edt_crm);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        // Instancia um novo objeto do tipo Medico
        medico = new Medico();
        //Parse inicializado no main

    }


    public void Salvar(View view){
        if (validaCadastroMedico(edtName.getText().toString(),edtCpf.getText().toString(),edtCrm.getText().toString(),
                edtEmail.getText().toString(),edtPassword.getText().toString())) {
            // Injeta no objeto "objetoEmprestado" os dados informados pelo usuário
            medico.setName(edtName.getText().toString());
            medico.setCpf(edtCpf.getText().toString());
            medico.setCrm(edtCrm.getText().toString());
            medico.setEmail(edtEmail.getText().toString());
            medico.setPassword(edtPassword.getText().toString());

            // Instancia o DAO para persistir o objeto
            medicosDAO dao = new medicosDAO(getApplicationContext());
            // Salva o objeto no banco de dados
            dao.adiciona(medico);
            // Mostra para o usuário uma mensagem de sucesso na operação
            Toast.makeText(getApplicationContext(), "Objeto salvo com sucesso!", Toast.LENGTH_LONG)
                    .show();

            Intent it = null;
            //TODO: salvar dados no BD
            //testando o parse
            ParseObject testObject = ParseObject.create("TestObject");
            testObject.put("Nome", edtName.getText().toString());
            testObject.put("Email", edtEmail.getText().toString());
            testObject.put("Status", false); //o usuario nao esta autorizado por padrao
            testObject.saveInBackground();
            Toast.makeText(getApplicationContext(), "Requiscao enviada", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validaCadastroMedico(String name, String cpf,String crm, String email, String password){
        boolean ok = false;
        if (validaNome(name) && !checaEntradaEmBranco(cpf) && !checaEntradaEmBranco(crm) && !checaEntradaEmBranco(email)
                && !checaEntradaEmBranco(password)){

            if (validaTamanhoSenha(password)) {

                if (validaCPF(cpf)) {

                    if (validaEmail(email)) {
                        ok = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "CPF Invalido", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Tamanho de senha Invalido, a senha deve ter de 6 a 12 caracteres", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos, existe campos vazios!", Toast.LENGTH_SHORT).show();
        }


        return ok;
    }

    public void Cancel(View view){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_cadastro_medico, menu);
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
