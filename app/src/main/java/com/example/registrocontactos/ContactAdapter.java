package com.example.registrocontactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final List<Contacto> contactos;

    public ContactAdapter(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacto c = contactos.get(position);
        holder.tvItemNombre.setText(c.getNombre());
        holder.tvItemDetalles.setText(c.getTipo() + " | Tel: " + c.getTelefono() + " | " + c.getCorreo());
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemNombre, tvItemDetalles;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemNombre = itemView.findViewById(R.id.tvItemNombre);
            tvItemDetalles = itemView.findViewById(R.id.tvItemDetalles);
        }
    }
}
