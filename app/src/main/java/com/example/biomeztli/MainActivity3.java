package com.example.biomeztli;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.example.biomeztli.DescriptionFragment;
import com.example.biomeztli.PropertiesFragment;
import com.example.biomeztli.R;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Inicializa la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtén referencias a las vistas
        ImageView imageView = findViewById(R.id.img1);
        TextView textView = findViewById(R.id.nombrePlanta);

        // Obtén la información de la imagen y el nombre a través de un extra de Intent
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        String nombre = getIntent().getStringExtra("NOMBRE");

        // Carga la imagen en el ImageView usando Glide
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        // Asigna el nombre al TextView
        textView.setText(nombre);

        // Configura los listeners para cambiar entre fragments
        configureButtonListeners();
    }

    // Método para configurar listeners de los botones
    private void configureButtonListeners() {
        ImageButton buttonDescription = findViewById(R.id.descriptionB);
        ImageButton buttonProperties = findViewById(R.id.propertiesB);

        buttonDescription.setOnClickListener(v -> switchFragment(new DescriptionFragment()));
        buttonProperties.setOnClickListener(v -> switchFragment(new PropertiesFragment()));

        // Muestra el fragmento de descripción por defecto al iniciar la actividad
        switchFragment(new DescriptionFragment());
    }

    // Método para cambiar entre fragmentos
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplaza el contenedor con el nuevo fragmento
        fragmentTransaction.replace(R.id.container, fragment);

        // Añade la transacción al back stack
        fragmentTransaction.addToBackStack(null);

        // Commit de la transacción
        fragmentTransaction.commit();
    }
}
