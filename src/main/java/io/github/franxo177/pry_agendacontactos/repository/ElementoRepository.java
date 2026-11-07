package io.github.franxo177.pry_agendacontactos.repository;

import io.github.franxo177.pry_agendacontactos.dao.ElementoDao;
import io.github.franxo177.pry_agendacontactos.dao.JsonElementoDao;
import io.github.franxo177.pry_agendacontactos.model.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ElementoRepository {
    private final ElementoDao<Contacto> daoA = new JsonElementoDao<>("data/contactos.json", Contacto.class);
    private final List<Contacto> elementosA = new ArrayList<>();

    public ElementoRepository() {
        elementosA.addAll(daoA.cargar());
    }

    public List<Contacto> listar() {
        List<Contacto> todos = new ArrayList<>();
        todos.addAll(elementosA);
        return todos;
    }

    public void agregar(Contacto nuevo) {
        for (Contacto e : listar())
            if (e.getTelefono().equalsIgnoreCase(nuevo.getTelefono()))
                throw new IllegalArgumentException("Telefono ya en contactos");
        elementosA.add(nuevo);
        daoA.guardar(elementosA);
    }

    public void eliminar(String telefono) {
        if(elementosA.removeIf(x -> x.getTelefono().equalsIgnoreCase(telefono))) {
            daoA.guardar(elementosA);
            return;
        } throw new IllegalArgumentException("Registro no encontrado");
    }

}
