package com.example.mario.apptareaglide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.etNumPokemon);
        btn = findViewById(R.id.btn);
    }

    public void acceder(View v){
        Intent i = new Intent(this,ListPokemonActivity.class);
        String pokemon = et.getText().toString();
        i.putExtra(getResources().getString(R.string.clave_num_pokemon),pokemon);
        startActivity(i);
        finish();
    }
}
