package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle_Confirmacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        Log.d(TAG, "onCreate");

        // 1. Vinculamos de forma correcta los componentes con tu XML
        TextView tvResumen = findViewById(R.id.tvResumen);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        // 2. Recuperamos los datos enviados por la primera pantalla
        final String nombre = getIntent().getStringExtra("NOMBRE");
        final String correo = getIntent().getStringExtra("CORREO");
        final String telefono = getIntent().getStringExtra("TELEFONO");
        final String tipo = getIntent().getStringExtra("TIPO");

        // 3. Mostramos el resumen en el TextView principal
        if (tvResumen != null) {
            String resumen = "Nombre: " + nombre + "\n\n" +
                    "Correo: " + correo + "\n\n" +
                    "Teléfono: " + telefono + "\n\n" +
                    "Tipo: " + tipo;
            tvResumen.setText(resumen);
        }

        // 4. Acción del botón Volver (Regresa usando un Intent seguro)
        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> {
                Intent regresarVolver = new Intent(ConfirmacionActivity.this, MainActivity.class);
                startActivity(regresarVolver);
            });
        }

        // 5. Acción del botón Confirmar (Guarda en memoria y fuerza el regreso con Intent)
        if (btnConfirmar != null) {
            btnConfirmar.setOnClickListener(v -> {
                Contacto nuevoContacto = new Contacto(nombre, correo, telefono, tipo);
                ContactRepository.getInstance().agregarContacto(nuevoContacto);

                // SOLUCIÓN DEFINITIVA: Forzamos la navegación de regreso sin que el Samsung mate la app
                Intent regresarConfirmar = new Intent(ConfirmacionActivity.this, MainActivity.class);
                startActivity(regresarConfirmar);
            });
        }
    }

    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
    @Override protected void onRestart() { super.onRestart(); Log.d(TAG, "onRestart"); }
}
