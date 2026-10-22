package com.example.registrocontactos;

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

        // Conectamos los componentes correctos de la pantalla de confirmación
        TextView tvResumen = findViewById(R.id.tvResumen);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        // Recibimos los datos empaquetados desde la primera pantalla
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("NOMBRE");
            String correo = extras.getString("CORREO");
            String telefono = extras.getString("TELEFONO");
            String tipo = extras.getString("TIPO");

            // Juntamos todo en un solo texto bien formateado
            String resumen = "Nombre: " + nombre + "\n\n" +
                    "Correo: " + correo + "\n\n" +
                    "Teléfono: " + telefono + "\n\n" +
                    "Tipo: " + tipo;
            tvResumen.setText(resumen);

            // Al confirmar, guardamos en la lista en memoria y cerramos
            btnConfirmar.setOnClickListener(v -> {
                Contacto nuevoContacto = new Contacto(nombre, correo, telefono, tipo);
                ContactRepository.getInstance().agregarContacto(nuevoContacto);
                finish(); // Regresa a MainActivity
            });
        }

        // Al presionar Volver, simplemente cerramos esta pantalla sin guardar
        btnVolver.setOnClickListener(v -> finish());
    }

    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
}
