package com.example.registrocontactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactoViewHolder> {

    private final List<Contacto> listaContactos;

    public ContactAdapter(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);
        holder.tvNombre.setText(contacto.getNombre());

        String detalles = contacto.getTipo() + " | " + contacto.getTelefono() + " | " + contacto.getCorreo();
        holder.tvDetalles.setText(detalles);
    }

    @Override
    public int getItemCount() {
        return listaContactos != null ? listaContactos.size() : 0;
    }

    static class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalles;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvItemNombre);
            tvDetalles = itemView.findViewById(R.id.tvItemDetalles);
        }
    }
}
