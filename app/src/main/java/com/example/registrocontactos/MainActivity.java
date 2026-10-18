package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declaración de las variables para los componentes de la pantalla
    EditText etNombre, etCorreo, etTelefono;
    Spinner spinnerTipo;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectamos las variables de Java con los elementos del diseño XML
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Programamos la acción que ocurrirá al hacer clic en el botón Enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Recogemos el texto que escribió el usuario en el formulario
                String nombre = etNombre.getText().toString();
                String correo = etCorreo.getText().toString();
                String telefono = etTelefono.getText().toString();
                String tipoContacto = spinnerTipo.getSelectedItem().toString();

                // 2. Creamos el "Intent" para viajar a la pantalla de confirmación
                Intent intent = new Intent(MainActivity.this, ConfirmacionActivity.class);

                // 3. Empacamos los datos recopilados para mandarlos a la otra pantalla
                intent.putExtra("KEY_NOMBRE", nombre);
                intent.putExtra("KEY_CORREO", correo);
                intent.putExtra("KEY_TELEFONO", telefono);
                intent.putExtra("KEY_TIPO", tipoContacto);

                // 4. Arrancamos la segunda pantalla
                startActivity(intent);
            }
        });
    }
}
