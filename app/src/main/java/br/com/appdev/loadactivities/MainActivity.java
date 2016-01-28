package br.com.appdev.loadactivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNome, txtRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Escolha de Cidades");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtRetorno = (EditText) findViewById(R.id.txtRetorno);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_next:
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("Nome", txtNome.getText().toString());
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeCustomAnimation(
                                this,
                                R.anim.fade_in,
                                R.anim.fade_out
                        );
                ActivityCompat.startActivityForResult(this, intent, 1, optionsCompat.toBundle());
                break;
            case R.id.action_about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Nós somos bons, cara!!!");
                alert.setIcon(android.R.drawable.ic_dialog_alert);
                alert.setTitle("Sobre");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                break;

            case android.R.id.home:
                Toast.makeText(this, "Aqui deveria realmente fazer algo",
                        Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    public void btnGoOnClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Nome", txtNome.getText().toString());
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeCustomAnimation(
                        this,
                        R.anim.fade_in,
                        R.anim.fade_out
                );
        ActivityCompat.startActivityForResult(this, intent, 1, optionsCompat.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            txtRetorno.setText(data.getStringExtra("Retorno"));
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Amigo, tem certeza que deseja sair?");
        alert.setIcon(android.R.drawable.ic_lock_power_off);
        alert.setTitle("Preciso de sua resposta");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.tchau();
            }
        });
        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    public void tchau() {
        finish();
    }
}
