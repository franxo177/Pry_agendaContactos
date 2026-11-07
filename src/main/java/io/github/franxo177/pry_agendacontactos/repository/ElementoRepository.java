package io.github.franxo177.pry_agendacontactos.repository;

import io.github.franxo177.pry_agendacontactos.dao.ElementoDao;
import io.github.franxo177.pry_agendacontactos.dao.JsonElementoDao;
import io.github.franxo177.pry_agendacontactos.model.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ElementoRepository {
    private final ElementoDao<Contacto> daoA = new JsonElementoDao<>("data/contactos.json", Contacto.class);
    private final List<Contacto> elementosA = new ArrayList<>();

    private ElementoRepository() {
        elementosA.addAll(daoA.cargar());
    }

    public List<Contacto> listar() {
        List<Contacto> todos = new ArrayList<>();
        todos.addAll(elementosA);
        return todos;
    }

    public void agregar(Contacto nuevo) {
        for (Contacto e : listar())
            if (e.getId().equalsIgnoreCase(nuevo.getId()))
                throw new IllegalArgumentException("ID duplicado");
        elementosA.add(nuevo);
        daoA.guardar(elementosA);
    }

    public void eliminar(String id) {
        if(elementosA.removeIf(x -> x.getId().equalsIgnoreCase(id))) {
            daoA.guardar(elementosA);
            return;
        } throw new IllegalArgumentException("Registro no encontrado");
    }

}
