package com.example.biomeztli;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.bumptech.glide.Glide;
import com.example.biomeztli.databinding.ActivityMain3Binding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa la vista binding
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain3.toolbar);
        binding.appBarMain3.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top-level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main3);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ImageView imageView = findViewById(R.id.img1);
        TextView textView = findViewById(R.id.nombrePlanta);

        // Obtengo la información de la imagen y el nombre a través de un EXTRA de Intent
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        String nombre = getIntent().getStringExtra("NOMBRE");

        // Carga la imagen en el ImageView usando Glide
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        // Asigno el nombre al TextView
        textView.setText(nombre);

        // Configuro los listeners para cambiar entre fragments
        configureButtonListeners();

        // Muestra el fragmento de descripción por defecto al iniciar la actividad
        switchFragment(new DescriptionFragment());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main3);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }


    private void configureButtonListeners() {
        ImageButton buttonDescription = findViewById(R.id.descriptionB);
        ImageButton buttonProperties = findViewById(R.id.propertiesB);
        ImageButton buttonUse = findViewById(R.id.useB);
        ImageButton buttonCaution = findViewById(R.id.cautionB);

        buttonDescription.setOnClickListener(v -> switchFragment(new DescriptionFragment()));
        buttonProperties.setOnClickListener(v -> switchFragment(new PropertiesFragment()));
        buttonUse.setOnClickListener(v -> switchFragment(new UseFragment()));
        buttonCaution.setOnClickListener(v -> switchFragment(new CautionFragment()));
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplaza el contenedor con el nuevo fragmento
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main3, fragment);

        // Añade la transacción al back stack que nos permite eliminar lo anterior
        fragmentTransaction.addToBackStack(null);

        // Commit de la transacción
        fragmentTransaction.commit();
    }
}