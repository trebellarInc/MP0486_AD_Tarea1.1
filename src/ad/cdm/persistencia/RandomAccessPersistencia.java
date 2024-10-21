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
            //System.out.println(personas.toString());
            
            try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {

             
                longitudBytes = raf.length();
                raf.seek(longitudBytes);

                for (Persona persona : personas) {

                    // Id -> Long: 8 bytes
                    raf.writeLong(persona.getId());

                    // DNI -> String: 2 bytes * 9 caracteres 18 bytes
                    StringBuilder sb = new StringBuilder(persona.getDni());
                    sb.setLength(Persona.MAX_LENGTH_DNI);
                    raf.writeChars(sb.toString());

                    // Nombre -> String 2 bytes * 100 caracteres 200 bytes
                    sb = new StringBuilder(persona.getNombre());
                    sb.setLength(Persona.MAX_LENGTH_NOMBRE);
                    raf.writeChars(sb.toString());

                    // Edad -> Integer: 4 bytes
                    raf.writeInt(persona.getEdad());

                    // Salario -> Float: 4 bytes
                    raf.writeFloat(persona.getSalario());

                    // Borrado -> Boolean: 1 byte
                    raf.writeBoolean(persona.isBorrado());

                    System.out.println(persona.toString());

                    System.out.println("Escribiendo una persona -> " + persona.toString());

                }

            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Se ha producido una excepción: ", ex);
            }
        }

    }

    /**
     *
     * 2- Implementa o método public ArrayList<Persona> leerTodo(String ruta) da
     * clase RandomAccessPersistencia para que lea con RandomAccessFile cada un
     * dos datos escritos no arquivo sinalado en ruta e cree un obxecto Persona
     * a partir deles. Os datos deben lerse na mesma orde na que se escribiron
     * co método public void escribirPersonas(ArrayList<Persona> personas,
     * String ruta).
     */
    @Override
    public ArrayList<Persona> leerTodo(String ruta) throws IOException {

        ArrayList<Persona> personas = new ArrayList<>();

        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {

            Persona p = new Persona();

            p.setId(raf.readLong());

            String dni = "";
            for (int i = 0; i < Persona.MAX_LENGTH_DNI; i++) {
                dni += raf.readChar();
            }
            p.setDni(dni);

            String nombre = "";
            for (int i = 0; i < Persona.MAX_LENGTH_NOMBRE; i++) {
                nombre += raf.readChar();
            }
            p.setNombre(nombre);

            p.setEdad(raf.readInt());

            p.setSalario(raf.readFloat());

            p.setBorrado(raf.readBoolean());

            personas.add(p);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
        /**
         * 4- Implementa o método public Persona leerPersona(int posicion,
         * String ruta) throws NotFoundPersonaException da clase
         * RandomAccessPersistencia para que dada a ruta do arquivo a ler (terá
         * que ter sido creado con RandomAccessFile na mesma orde que o método
         * escribirPersonas), obteña o obxecto Persona da posición que entra
         * como parámetro. Se posicion é un cero, devolverá a primeira persoa,
         * se é un un, devolverá a segunda persoa, etc. Teredes que ter en conta
         * cantos bytes ocupa cada obxecto Persona. (0,7 puntos) Se xurde
         * calquera excepción deberá capturarse e lanzar unha nova excepción
         * NotFoundPersonaException que xa se proporciona no códio do
         * repositorio indicando a posicion que se intentou ler no construtor.
         * (0,1 puntos)
         */

        Persona p = new Persona();
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            personas = leerTodo(ruta);

        } catch (IOException ex) {
            Logger.getLogger(RandomAccessPersistencia.class.getName()).log(Level.SEVERE, "Error al leer persona");
        }
        return p;
    }

}
