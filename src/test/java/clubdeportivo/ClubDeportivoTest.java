package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos es negativo.")
    public void ClubDeportivo_NegativeNumberGroup_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        int numberGroup = -5;

        assertThrows(ClubException.class, () -> new ClubDeportivo(nombre, numberGroup));
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos 0.")
    public void ClubDeportivo_NumberGroupZero_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        int numberGroup = 0;

        assertThrows(ClubException.class, () -> new ClubDeportivo(nombre, numberGroup));
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe crear correctamente el club deportivo.")
    public void ClubDeportivo_CorrectNumberGroup_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String expectedString = nombre + " --> [  ]";

        String toString = club.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el nombre es nulo.")
    public void ClubDeportivo_nullName_ThrowsClubException() {
        String nombre = null;

        assertThrows(ClubException.class, () -> new ClubDeportivo(nombre));
    }

    @Test
    @DisplayName("El método plazasLibres debe devolver correctamente las plazas libres de una actividad.")
    public void plazasLibres_existsActivity_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, precio, plazas, maxPlazas);
        club.anyadirActividad(grupo);
        int plazasExpected = 5;

        int plazasLibres = club.plazasLibres("Tenis");

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método plazasLibres debe devolver 0 si no existe la actividad.")
    public void plazasLibres_notExistsActivity_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, precio, plazas, maxPlazas);
        club.anyadirActividad(grupo);
        int plazasExpected = 0;

        int plazasLibres = club.plazasLibres("Futbol");

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    public void plazasLibres_activityNull_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);

        assertThrows(ClubException.class, () -> club.plazasLibres(null));
    }

    @Test
    @DisplayName("El método ingresos debe devolver 0 si no hay grupos.")
    public void ingresos_noGroups_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        double expected = 0;

        double ingresos = club.ingresos();

        assertEquals(expected, ingresos);
    }

    @Test
    @DisplayName("El método ingresos debe devolver la suma de los ingresos de los grupos.")
    public void ingresos_hasGroups_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, precio, plazas, maxPlazas);
        String codigo2 = "D5";
        String actividad2 = "Tenis";
        int precio2 = 10;
        int plazas2 = 5;
        int maxPlazas2 = 10;
        Grupo grupo2 = new Grupo(codigo2, actividad2, precio2, plazas2, maxPlazas2);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        double expected = 100;

        double ingresos = club.ingresos();

        assertEquals(expected, ingresos);
    }

}
