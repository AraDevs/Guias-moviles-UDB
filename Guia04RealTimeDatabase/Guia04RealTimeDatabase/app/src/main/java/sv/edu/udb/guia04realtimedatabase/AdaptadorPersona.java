package sv.edu.udb.guia04realtimedatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sv.edu.udb.guia04realtimedatabase.datos.Persona;

public class AdaptadorPersona extends ArrayAdapter<Persona> {

    List<Persona> personas;
    private Activity context;

    public AdaptadorPersona(@NonNull Activity context, @NonNull List<Persona> personas) {
        super(context, R.layout.persona_layout, personas);
        this.context = context;
        this.personas = personas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.persona_layout,null);
        else rowview = view;

        TextView tvNombre = rowview.findViewById(R.id.name);
        TextView tvDUI = rowview.findViewById(R.id.dui);

        tvNombre.setText("Nombre : "+personas.get(position).getNombre());
        tvDUI.setText("DUI : " + personas.get(position).getDui());

        return rowview;
    }
}
