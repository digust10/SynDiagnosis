package br.edu.ufcg.embedded.syndiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciando o Parse
        ParseObject.registerSubclass(TestObject.class);
        Parse.initialize(this, "N4FsbD6GDmLtxg9WKbTWIOxcKCFmzvN60FCHJQl4", "p2VfHwjS5T5lFk3hbT15IoQnSs4lyafNdkziOmIH");

        ((Button) findViewById(R.id.button)).setOnClickListener(onClick);
        ((Button) findViewById(R.id.button2)).setOnClickListener(onClick);
        ((Button) findViewById(R.id.button3)).setOnClickListener(onClick);
        ((Button) findViewById(R.id.button4)).setOnClickListener(onClick);
        ((Button) findViewById(R.id.button5)).setOnClickListener(onClick);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = null;
            switch (v.getId()) {
                case R.id.button:
                    it = new Intent(getApplicationContext(), login.class);
                    break;
                case R.id.button2:
                    //it = new Intent(getApplicationContext(), QuestionarioMedicoActivity.class);
                    break;
                case R.id.button3:
                    it = new Intent(getApplicationContext(), FacialRecognizingActivity.class);
                    break;
                case R.id.button4:
                    it = new Intent(getApplicationContext(), MedicosCadastradosActivity.class);
                    break;
                case R.id.button5:
                    it = new Intent(getApplicationContext(), ParseListActivity.class);
                    break;
            }
            if (it != null)
                startActivity(it);
        }
    };
}
