package com.example.registrocontactos;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {
    // Declaramos las variables para mostrar los datos recibidos
    TextView tvNombre, tvCorreo, tvTelefono, tvTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        // NOTA: Asegúrate de que en tu activity_confirmacion.xml tengas estos IDs.
        // Si tus TextViews se llaman diferente, pon sus nombres reales aquí adentro.
        tvNombre = findViewById(R.id.main);
        tvCorreo = findViewById(R.id.main);
        tvTelefono = findViewById(R.id.main);
        tvTipo = findViewById(R.id.main);


        // 1. Recibimos el "paquete" con los datos de la pantalla anterior
        Bundle datosRecibidos = getIntent().getExtras();

        if (datosRecibidos != null) {
            // 2. Extraemos los textos usando las mismas llaves (KEYS) que creamos antes
            String nombre = datosRecibidos.getString("KEY_NOMBRE");
            String correo = datosRecibidos.getString("KEY_CORREO");
            String telefono = datosRecibidos.getString("KEY_TELEFONO");
            String tipo = datosRecibidos.getString("KEY_TIPO");

            // 3. Pintamos la información en las etiquetas de la pantalla
            if (tvNombre != null) tvNombre.setText("Nombre: " + nombre);
            if (tvCorreo != null) tvCorreo.setText("Correo: " + correo);
            if (tvTelefono != null) tvTelefono.setText("Teléfono: " + telefono);
            if (tvTipo != null) tvTipo.setText("Tipo: " + tipo);
        }
    }
}
