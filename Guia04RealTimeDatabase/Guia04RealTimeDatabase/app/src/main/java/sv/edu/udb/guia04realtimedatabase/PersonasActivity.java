package sv.edu.udb.guia04realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.guia04realtimedatabase.datos.Persona;

public class PersonasActivity extends AppCompatActivity {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference reference = database.getReference("personas");

    Query orderQuery = reference.orderByChild("nombre");

    List<Persona> persons;
    ListView listPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        inicializar();
    }

    public void inicializar() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fabAdd);
        listPersons = findViewById(R.id.listPersons);

        listPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddPersonaActivity.class);

                intent.putExtra("accion","e"); // Editar
                intent.putExtra("key", persons.get(i).getKey());
                intent.putExtra("nombre",persons.get(i).getNombre());
                intent.putExtra("dui",persons.get(i).getDui());
                intent.putExtra("fecha",persons.get(i).getFecha());
                intent.putExtra("genero",persons.get(i).getGenero());
                intent.putExtra("peso",persons.get(i).getPeso());
                intent.putExtra("altura",persons.get(i).getAltura());
                startActivity(intent);
            }
        });

        listPersons.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(PersonasActivity.this);
                ad.setMessage("¿Está seguro de eliminar este registro?")
                    .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PersonasActivity.reference
                                .child(persons.get(position).getKey()).removeValue();

                        Toast.makeText(PersonasActivity.this,
                                "Registro eliminado",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(PersonasActivity.this,
                                "Operación cancelada",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), AddPersonaActivity.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombre","");
                i.putExtra("dui","");
                startActivity(i);
            }
        });

        persons = new ArrayList<>();

        orderQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                persons.removeAll(persons);
                for (DataSnapshot dato : snapshot.getChildren()) {
                    Persona persona = dato.getValue(Persona.class);
                    persona.setKey(dato.getKey());
                    persons.add(persona);
                }

                AdaptadorPersona adapter = new AdaptadorPersona(PersonasActivity.this, persons);
                listPersons.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}