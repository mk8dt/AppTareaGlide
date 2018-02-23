package com.example.mario.apptareaglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dataJson.Pokemon;
import dataJson.PokemonFoto;
import dataJson.PokemonResult;
import dataJson.PokemonTotal;
import recyclerViewUtils.ItemPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitUtils.APIRest;
import retrofitUtils.RetrofitClient;

public class FotoActivity extends AppCompatActivity {

    String idPokemon;
    ImageView imagenPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        idPokemon = getIntent().getStringExtra("Item");
        imagenPokemon = findViewById(R.id.idFotoPokemon);
        invocarWS();

    }
    private void invocarWS() {
            Retrofit r = RetrofitClient.getClient(APIRest.BASE_URL);
            APIRest ars = r.create(APIRest.class);

            Call<PokemonTotal> call = ars.obtenerFoto(idPokemon);

            call.enqueue(new Callback<PokemonTotal>() {

                @Override
                public void onResponse(Call<PokemonTotal> call, Response<PokemonTotal> response) {
                    //pb.setVisibility(View.GONE);
                    if (!response.isSuccessful()) {
                        Log.e("Error", response.code() + " ");
                    } else {
                        PokemonTotal pokeFoto = response.body();
                        String pf = pokeFoto.getSprites().getFrontDefault();
                        Glide.with(imagenPokemon.getContext()).load(pf).into(imagenPokemon);

                    }
                }

                @Override
                public void onFailure(Call<PokemonTotal> call, Throwable t) {
                   // pb.setVisibility(View.GONE);
                    //Traza para controlar el error
                    Log.e("Error", t.toString());
                }
            });
        }

    }
