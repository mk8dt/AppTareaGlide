package recyclerViewUtils;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mario.apptareaglide.FotoActivity;
import com.example.mario.apptareaglide.ListPokemonActivity;
import com.example.mario.apptareaglide.R;

import java.util.ArrayList;

/**
 * Created by mario on 05/02/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.VhPokemon>
        implements View.OnClickListener {

    private ArrayList<ItemPokemon>listaPokemon;
    private View.OnClickListener listener;

    public Adapter(ArrayList<ItemPokemon> listaPokemon) {
        this.listaPokemon = listaPokemon;
    }

    public static class VhPokemon extends RecyclerView.ViewHolder{

        TextView tvCodigo;
        TextView tvNombre;

        public VhPokemon(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }

        public TextView getTvCodigo() {
            return tvCodigo;
        }

        public TextView getTvNombre() {
            return tvNombre;
        }
    }

    @Override
    public VhPokemon onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista,parent,false);
        v.setOnClickListener(this);
        VhPokemon vh = new VhPokemon(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(VhPokemon holder, int position) {

        holder.tvCodigo.setText(listaPokemon.get(position).getCodigo());
        holder.tvNombre.setText(listaPokemon.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPokemon.size();
    }

    //Preparamos el listener para recibir el metodo desde el ListPokemonActivity
    public void setOnclickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
}
