package io.github.franxo177.pry_agendacontactos.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonElementoDao<T> implements ElementoDao<T> {
    private final Path archivo;
    private final Type tipoLista;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonElementoDao(String ruta, Class<T> tipo) {
        archivo = Path.of(ruta);
        tipoLista = TypeToken.getParameterized(List.class, tipo).getType();
    }

    @Override
    public List<T> cargar() {
        if (!Files.exists(archivo)) return new ArrayList<>();
        try (Reader lector = Files.newBufferedReader(archivo)) {
            List<T> datos = gson.fromJson(lector, tipoLista);
            return datos == null ? new ArrayList<>() : datos;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo " + archivo, e);
        }
    }

    @Override
    public void guardar(List<T> elementos) {
        try {
            Files.createDirectories(archivo.getParent());
            try (Writer escritor = Files.newBufferedWriter(archivo)) {
                gson.toJson(elementos, tipoLista, escritor);
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar el archivo " + archivo, e);
        }
    }
}

