package io.github.franxo177.pry_agendacontactos.model;

//atributos que tendra el contacto
public class Contacto {
    private String nombre, apellido, telefono;

    //constructor con sus setters validados
    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
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
