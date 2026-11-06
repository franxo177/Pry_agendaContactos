package io.github.franxo177.pry_agendacontactos.dao;
import java.util.List;

public interface ElementoDao<T> {
    List<T> cargar();
    void guardar(List<T> elementos);
}
