package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle_MainActivity";

    // Declaración global de variables para que se puedan limpiar de forma segura
    private EditText etNombre, etCorreo, etTelefono;
    private Spinner spinnerTipo;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        // 1. Conectamos los componentes de tu diseño XML
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        // 2. Opciones del Spinner
        String[] opciones = {"Personal", "Trabajo", "Otros"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinnerTipo != null) {
            spinnerTipo.setAdapter(adapterSpinner);
        }

        // 3. Configuración del RecyclerView para la lista
        RecyclerView rvContactos = findViewById(R.id.rvContactos);
        if (rvContactos != null) {
            rvContactos.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ContactAdapter(ContactRepository.getInstance().getListaContactos());
            rvContactos.setAdapter(adapter);
        }

        // 4. Acción del botón Enviar Datos
        if (btnEnviar != null) {
            btnEnviar.setOnClickListener(v -> {
                if (validarFormulario()) {
                    String nombre = etNombre.getText().toString().trim();
                    String correo = etCorreo.getText().toString().trim();
                    String telefono = etTelefono.getText().toString().trim();
                    String tipo = spinnerTipo.getSelectedItem().toString();

                    Intent intent = new Intent(MainActivity.this, ConfirmacionActivity.class);
                    intent.putExtra("NOMBRE", nombre);
                    intent.putExtra("CORREO", correo);
                    intent.putExtra("TELEFONO", telefono);
                    intent.putExtra("TIPO", tipo);
                    startActivity(intent);
                }
            });
        }
    }

    // ESTO EVITA EL CRASH: Refresca la lista y limpia los cuadros de arriba al regresar
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        // Vacía las cajas de texto automáticamente para poder usar el botón de nuevo sin cierres
        if (etNombre != null) etNombre.setText("");
        if (etCorreo != null) etCorreo.setText("");
        if (etTelefono != null) etTelefono.setText("");
    }

    private boolean validarFormulario() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("El nombre es requerido");
            return false;
        }
        if (correo.isEmpty()) {
            etCorreo.setError("El correo es requerido");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Formato de correo inválido");
            return false;
        }
        if (telefono.isEmpty()) {
            etTelefono.setError("El teléfono es requerido");
            return false;
        } else if (telefono.length() != 8) {
            etTelefono.setError("El teléfono debe tener exactamente 8 dígitos");
            return false;
        }
        return true;
    }

    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
    @Override protected void onRestart() { super.onRestart(); Log.d(TAG, "onRestart"); }
}
