/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.cdm.persistencia;

import ad.cdm.model.Persona;
import ad.cdm.model.exceptions.NotFoundPersonaException;
import ad.cdm.util.LogUtil;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 */
public class RandomAccessPersistencia implements IPersistencia {

   

    static final Logger logger = Logger.getLogger(RandomAccessPersistencia.class.getName());

    public RandomAccessPersistencia() {
        LogUtil.setLogger();
    }

    @Override
    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        long longitudBytes = 0;

        if (personas != null) {
            try (
                    RandomAccessFile raf = new RandomAccessFile(ruta, "rw");) {

                longitudBytes = raf.length();
                raf.seek(longitudBytes);
                for (Persona persona : personas) {

                    raf.writeLong(persona.getId());
                    StringBuilder sb = new StringBuilder(persona.getDni());
                    sb.setLength(Persona.MAX_LENGTH_DNI);
                    raf.writeChars(sb.toString());

                    sb = new StringBuilder(persona.getNombre());
                    sb.setLength(Persona.MAX_LENGTH_NOMBRE);
                    raf.writeChars(sb.toString());

                    raf.writeInt(persona.getEdad());
                    raf.writeFloat(persona.getSalario());

                    raf.writeBoolean(persona.isBorrado());

                }

            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Se ha producido una excepción: ", ex);
            }
        }

    }

    @Override
    public ArrayList<Persona> leerTodo(String ruta) throws IOException {
       
        ArrayList<Persona> personas = new ArrayList<>();
        return personas;

    }

    /**
     * Obtén un obxecto Persona do arquivo sinalado en ruta
     *
     * @param posicion indica a posición que ocupa cada persoa comezando no 0: o
     * cero devolverá a primera persoa, o 1 a segunda persoa, etc.
     * @param ruta a ruta ao arquivo
     * @return o obxecto Persoa atopado nesa posición
     * @throws NotFoundPersonaException en caso de erro
     */
    public Persona leerPersona(int posicion, String ruta) throws NotFoundPersonaException {
       
        return null;
    }

  

}
