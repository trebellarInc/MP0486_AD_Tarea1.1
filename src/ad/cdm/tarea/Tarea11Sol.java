
package ad.cdm.tarea;

import ad.cdm.model.Persona;
import ad.cdm.model.exceptions.NotFoundPersonaException;
import ad.cdm.persistencia.RandomAccessPersistencia;
import ad.cdm.util.LogUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;

/**
 *
 * @author mfernandez
 */
public class Tarea11Sol {

    public static final Path PERSONAS_ORIGEN_PATH_RAN = Paths.get("src", "docs", "origen_ran.dat");

    static final Logger logger = Logger.getLogger(Tarea11Sol.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            LogUtil.setLogger();

            int posicion = leerPosicion();

            RandomAccessPersistencia rap = new RandomAccessPersistencia();
            Persona found = rap.leerPersona(posicion, PERSONAS_ORIGEN_PATH_RAN.toString());
            if (found != null) {
                System.out.println("Atopouse a persoa da posición " + posicion + " cos seguintes datos: " + found);
            }

        } catch (NotFoundPersonaException | IOException ex) {
            System.err.println("Ocorreu un erro e non se puido executar correctamente o programa. Revise o arquivo de log");
            logger.log(Level.SEVERE, "Ocorreu unha excepción", ex);
        }

    }

    private static int leerPosicion() throws IOException {
        String numeroString = "";
        boolean valida = false;
        int posicion = -1;
        do {
            System.out.println("Introduza a posición da persoa que desexe ler (un enterio do 0 ao 5)");

            try {

             //TODO Enter data using BufferReader
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Introduce un número entero:");

                // Leer la entrada del usuario como una cadena
                String entrada = br.readLine();

                // Convertir la cadena a un número entero
                posicion = Integer.parseInt(entrada);
                
                
                
                if (posicion >= 0 && posicion < 6) {
                    valida = true;
                } else {
                    System.out.println("O número introducido non é correcto");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Inténteo de novo");
                logger.log(Level.SEVERE, "Ocorreu unha excepción convertendo: " + numeroString, ex);
            }
        } while (!valida);

        return posicion;
    }

}
