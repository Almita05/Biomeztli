package com.example.biomeztli;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull MainModel model) {
        holder.nombre.setText(model.getNombre());

        // Glide para cargar la imagen desde la base de datos en lugar de la URL
        Glide.with(holder.img.getContext())
                .load(Uri.parse(model.getImagen()))  // URL de la imagen desde la base de datos
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        // Onclick en la imagen para ir a la actividad 3
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí se va a  iniciar la nueva actividad
                Intent intent = new Intent(view.getContext(), MainActivity3.class);
                intent.putExtra("IMAGE_URL", model.getImagen());  // Pasar la URL de la imagen como extra, estoo es importante
                intent.putExtra("NOMBRE", model.getNombre());  // Pasar la URL de la imagen como extra, estoo es importante
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView nombre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            nombre = itemView.findViewById(R.id.nameText);
        }
    }
}