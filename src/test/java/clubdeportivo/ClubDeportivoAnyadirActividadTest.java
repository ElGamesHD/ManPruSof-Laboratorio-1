/**
 * @author Eulogio Quemada Torres
 * @author Alejandro Román Sánchez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAnyadirActividadTest {

    @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_ValidValues_ReturnsExpectedResult() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", "10", "5", "10" };
        String expectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:10 - M:5) ]";

        club.anyadirActividad(datos);
        String toString = club.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si faltan datos.")
    public void anyadirActividad_LessThanFiveArgumentsArray_ThrowsError() throws ClubException {
        String nombre = "Club Alfarfa";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", "10", "5" };

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si los datos son incorrectos.")
    public void anyadirActividad_IncorrectNumberFormat_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", "10", "cinco", "10" };

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si alguno de los campos es nulo.")
    public void anyadirActividad_NullValues_ThrowsError() throws ClubException {
        String nombre = "Club Alfarfa";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", null, "5", "10" };

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si los datos son nulos.")
    public void anyadirActividad_NullDatos_ThrowsError() throws ClubException {
        String nombre = "Club Alfarfa";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si el club está lleno y se intenta anñadir otro grupo")
    public void anyadirActividad_ClubFull_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club 1", 1);
        Grupo grupo1 = new Grupo("Grupo 1", "Actividad 1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo 2", "Actividad 2", 10, 5, 5.0);
        club.anyadirActividad(grupo1);

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo2);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo no existe.")
    public void anyadirActividad_IncorrectGroup_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_NewGroup_ReturnsExpectedResult() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        double precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        String expectedString = nombre + " --> [ (" + codigo + " - " + actividad + " - " + precio + " euros - P:"
                + maxPlazas + " - M:" + plazas + ") ]";

        club.anyadirActividad(grupo);
        String toString = club.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("El método anyadirActividad debe modificar la plazas del grupo ya existente.")
    public void anyadirActividad_ExistingGroup_ReturnsExpectedResult() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        double precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        int nplazas = 15;
        Grupo grupo2 = new Grupo(codigo, actividad, nplazas, plazas, precio);
        String expectedString = nombre + " --> [ (" + codigo + " - " + actividad + " - " + precio + " euros - P:"
                + nplazas + " - M:" + plazas + ") ]";

        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        String toString = club.toString();

        assertEquals(expectedString, toString);
    }
}
