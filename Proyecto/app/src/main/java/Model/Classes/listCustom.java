package Model.Classes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto1.moviles.proyecto.R;

import java.util.ArrayList;

/**
 * Created by felipe on 3/25/2016.
 */
public class listCustom extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> nombres; //
    private final Integer[] imagenes;

    public listCustom(Activity context, ArrayList<String> nombres, Integer[] imagenes) {
        super(context, R.layout.listrow, nombres);
        this.context = context;
        this.nombres = nombres;
        this.imagenes = imagenes;
    }
    @Override
    public View getView(int posicion, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listrow, null, true);
        TextView nomUser = (TextView) rowView.findViewById(R.id.nombre);

        ImageView imgUser = (ImageView) rowView.findViewById(R.id.img);
        nomUser.setText(nombres.get(posicion));

        imgUser.setImageResource(imagenes[0]);
        Button arch = (Button)rowView.findViewById(R.id.archivos);
        Button msgs = (Button)rowView.findViewById(R.id.mensajes);
        return rowView;
    }

}
