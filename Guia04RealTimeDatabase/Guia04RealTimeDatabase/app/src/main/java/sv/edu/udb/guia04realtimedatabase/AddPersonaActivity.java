package sv.edu.udb.guia04realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import sv.edu.udb.guia04realtimedatabase.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {

    EditText etDui, etName, etDate, etGenre, etWeight, etHeight;
    String key, name, dui, date, genre, weight, height, action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        inicializar();
    }

    private void inicializar() {
        etDui = findViewById(R.id.dui);
        etName = findViewById(R.id.name);
        etDate = findViewById(R.id.date);
        etGenre = findViewById(R.id.genre);
        etWeight = findViewById(R.id.weight);
        etHeight = findViewById(R.id.height);

        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        name = datos.getString("nombre");
        date = datos.getString("fecha");
        genre = datos.getString("genero");
        weight = datos.getString("peso");
        height = datos.getString("altura");
        action = datos.getString("accion");
        etDui.setText(dui);
        etName.setText(name);
        etDate.setText(date);
        etGenre.setText(genre);
        etWeight.setText(weight);
        etHeight.setText(height);
    }

    public void save(View v) {
        String nombre = etName.getText().toString();
        String dui = etDui.getText().toString();
        String date = etDate.getText().toString();
        String genre = etGenre.getText().toString();
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();
        // Se forma objeto persona
        Persona persona = new Persona(dui,nombre, date, genre, weight, height);

        if (action.equals("a")) { //Agregar usando push()
            PersonasActivity.reference.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.reference.child(key).setValue(persona);
        }
        finish();
    }

    public void cancel(View v) { finish(); }
}