/**
 * @author Eulogio Quemada Torres
 * @author Alejandro Román Sánchez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos es negativo.")
    public void ClubDeportivo_NegativeNumberGroup_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        int numberGroup = -5;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(nombre, numberGroup);
        });
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos 0.")
    public void ClubDeportivo_NumberGroupZero_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        int numberGroup = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(nombre, numberGroup);
        });
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe crear correctamente el club deportivo.")
    public void ClubDeportivo_CorrectNumberGroup_Success() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String expectedString = nombre + " --> [  ]";

        String toString = club.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el nombre es nulo.")
    public void ClubDeportivo_NullName_ThrowsError() {
        String nombre = null;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(nombre);
        });
    }

    @Test
    @DisplayName("El método plazasLibres debe devolver correctamente las plazas libres de una actividad.")
    public void plazasLibres_ExistsActivity_ReturnsExpectedResult() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        int maxPlazas = 10;
        int plazas = 5;
        Grupo grupo = new Grupo("D4", actividad, maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        int plazasExpected = maxPlazas - plazas;

        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método plazasLibres debe devolver 0 si no existe la actividad.")
    public void plazasLibres_NotExistsActivity_ReturnsZero() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10.0);
        club.anyadirActividad(grupo);
        String actividadNoExiste = "Futbol";
        int plazasExpected = 0;

        int plazasLibres = club.plazasLibres(actividadNoExiste);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    public void plazasLibres_ActivityNull_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10.0);
        club.anyadirActividad(grupo);
        String actividadNull = null;

        assertThrows(ClubException.class, () -> {
            club.plazasLibres(actividadNull);
        });
    }

    @Test
    @DisplayName("El método ingresos debe devolver 0 si no hay grupos.")
    public void ingresos_NoGroups_ReturnsZero() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        double expected = 0;

        double ingresos = club.ingresos();

        assertEquals(expected, ingresos);
    }

    @Test
    @DisplayName("El método ingresos debe devolver la suma de los ingresos de los grupos.")
    public void ingresos_HasGroups_ReturnsExpectedResult() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo("D4", "Tenis", maxPlazas, plazas, precio);
        int precio2 = 15;
        int plazas2 = 4;
        int maxPlazas2 = 10;
        Grupo grupo2 = new Grupo("D5", "Tenis", maxPlazas2, plazas2, precio2);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        double expected = 110;

        double ingresos = club.ingresos();

        assertEquals(expected, ingresos);
    }

}
