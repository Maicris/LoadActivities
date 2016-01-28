package br.com.appdev.loadactivities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String nome = getIntent().getStringExtra("Nome");
        Toast.makeText(
                this,
                "Escolha uma das opções, " + nome,
                Toast.LENGTH_LONG).show();

        String[] itens = new String[] {
                "Opção 01",
                "Opção 02",
                "Opção 03",
                "Opção 04"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                itens
        );
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent data = new Intent();
        switch (position) {
            case 0:
                data.putExtra("Retorno", "Curitiba");
                break;
            case 1:
                data.putExtra("Retorno", "São Paulo");
                break;
            case 2:
                data.putExtra("Retorno", "Cuibá");
                break;
            case 3:
                data.putExtra("Retorno", "Belo Horizonte");
                break;

            default:
                data.putExtra("Retorno", "Santa Clara, São Francisco!!!");
                break;
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
