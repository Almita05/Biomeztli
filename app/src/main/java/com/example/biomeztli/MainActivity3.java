package com.example.biomeztli;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageView = findViewById(R.id.img1); // Reemplaza con el ID real de tu ImageView
        TextView textView = findViewById(R.id.nombrePlanta); // Reemplaza con el ID real de tu TextView

        // Pasar la imagen y el nombre a través de un extra de un intent
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        String nombre = getIntent().getStringExtra("NOMBRE");

        // Cargamos la imagen en un ImageView
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        // Asignamos el nombre a un TextView
        textView.setText(nombre);
    }
}

