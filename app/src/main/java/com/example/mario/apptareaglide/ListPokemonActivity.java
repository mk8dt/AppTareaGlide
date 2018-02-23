package com.example.mario.apptareaglide;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import dataJson.Pokemon;
import dataJson.PokemonResult;
import recyclerViewUtils.Adapter;
import recyclerViewUtils.ItemPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitUtils.APIRest;
import retrofitUtils.RetrofitClient;

public class ListPokemonActivity extends AppCompatActivity {

    private String pokemon;
    private ProgressBar pb;
    private ArrayList<ItemPokemon> lista;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);

        pokemon = getIntent().getStringExtra(getResources().getString(R.string.clave_num_pokemon));
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
        lista = new ArrayList<ItemPokemon>();
        invocarWS();


    }

    private void addOnclickFoto() {
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.indexOfChild(v);
                //Con este metodo recuperamos la posiion real de la lista
                // y con el indexOF solo recuperamos la posicion en la pantalla.
                ItemPokemon ip = lista.get(recyclerView.getChildAdapterPosition(v));

                Intent i = new Intent(getApplicationContext(),FotoActivity.class);
                i.putExtra("Item",ip.getCodigo());
                startActivity(i);
            }
        });
    }

    private void configurarRV() {
        recyclerView = findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        //añadimos el onclick despues de crear el adaptador
        adapter = new Adapter(lista);
        addOnclickFoto();
        recyclerView.setAdapter(adapter);



    }

    private void invocarWS() {
        if(isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRest.BASE_URL);
            APIRest ars = r.create(APIRest.class);

            Call<Pokemon> call = ars.obtenerPokemon(pokemon);

            call.enqueue(new Callback<Pokemon>() {

                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    pb.setVisibility(View.GONE);
                    if (!response.isSuccessful()) {
                        Log.e("Error", response.code() + " ");
                    } else {
                        Pokemon pokeRes = response.body();
                        ItemPokemon ip = null;

                        for (PokemonResult poke : pokeRes.getResults()) {
                            ip = new ItemPokemon(obtenerCodigo(poke.getUrl()), poke.getName());
                            lista.add(ip);
                        }
                        configurarRV();
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    pb.setVisibility(View.GONE);
                    //Traza para controlar el error
                    Log.e("Error", t.toString());
                }
            });
        }

    }

    private String obtenerCodigo(String url){
        String codigo="";
        String pokemonAux = "pokemon/";
        int indice =0; //indice de pokemon\/ + el tamaño de esta cadena.
        int j = 0; // ultimo indice de \/ .

        indice = url.lastIndexOf(pokemonAux) + pokemonAux.length();
        j = url.lastIndexOf("/");
        codigo = url.substring(indice,j);

        return codigo;
    }

    private boolean isNetworkAvailable(){
        boolean isAvailable = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo!=null && networkInfo.isConnected()) isAvailable=true;

        return isAvailable;
    }

}
