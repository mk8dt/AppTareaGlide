package retrofitUtils;
import dataJson.Pokemon;
import dataJson.PokemonTotal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mario on 05/02/2018.
 */

public interface APIRest {

    //Las clase en interfaces por defecto son public static final no hace falta poner.
    String BASE_URL="https://pokeapi.co/api/v2/";


        @GET("pokemon/")
        Call<Pokemon> obtenerPokemon (@Query("limit") String limite); // Query para valores fijos

        @GET("pokemon-form/{id_pokemon}/")
        Call<PokemonTotal> obtenerFoto (@Path("id_pokemon") String foto); // Path para valores variables
}
