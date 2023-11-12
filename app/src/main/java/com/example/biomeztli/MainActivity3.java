package com.example.biomeztli;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageView = findViewById(R.id.img1); // Reemplaza con el ID real de tu ImageView

        // Recupera la URL de la imagen de la actividad anterior (puedes pasarla a través de Intent)
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");

        // Utiliza Glide para cargar la imagen en el ImageView
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }
}
