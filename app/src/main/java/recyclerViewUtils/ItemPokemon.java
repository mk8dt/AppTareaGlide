package recyclerViewUtils;

import android.os.Parcelable;

/**
 * Created by mario on 05/02/2018.
 */

public class ItemPokemon {

    private String codigo;
    private String nombre;

    public ItemPokemon(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
