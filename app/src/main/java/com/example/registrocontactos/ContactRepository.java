package com.example.registrocontactos;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private static ContactRepository instance;
    private final List<Contacto> listaContactos;
    private OnContactAddedListener listener;

    public interface OnContactAddedListener {
        void onContactAdded();
    }

    private ContactRepository() {
        listaContactos = new ArrayList<>();
    }

    public static synchronized ContactRepository getInstance() {
        if (instance == null) {
            instance = new ContactRepository();
        }
        return instance;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void agregarContacto(Contacto contacto) {
        listaContactos.add(contacto);
        if (listener != null) {
            listener.onContactAdded();
        }
    }

    public void setListener(OnContactAddedListener listener) {
        this.listener = listener;
    }
}
