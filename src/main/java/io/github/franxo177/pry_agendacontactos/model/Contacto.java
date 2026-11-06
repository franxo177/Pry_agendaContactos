package io.github.franxo177.pry_agendacontactos.model;

//atributos que tendra el contacto
public class Contacto {
    private String id,nombre, apellido, telefono;

    //constructor con sus setters validados
    private Contacto(String id, String nombre, String apellido, String telefono) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    private String getId() {
        return id;
    }

    private String getNombre() {
        return nombre;
    }

    private String getApellido() {
        return apellido;
    }

    private String getTelefono() {
        return telefono;
    }

    private void setId(String id) {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException("La ID no puede quedar vacia");
        }
        this.id = id;
    }

    private void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede quedar vacio");
        }
        this.nombre = nombre;
    }

    private void setApellido(String apellido) {
        if(apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede quedar vacio");
        }
        this.apellido = apellido;
    }

    private void setTelefono(String telefono) {
        if(telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El telefono no puede quedar vacio");
        }
        if (!telefono.matches("(\\+56)?\\d{9}")) {
            throw new IllegalArgumentException("El telefono debe contener 9 digitos, y si desea empezar por +56");
        }
        this.telefono = telefono;
    }
}
