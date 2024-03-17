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

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si los datos son incorrectos.")
    public void anyadirActividad_IncorrectNumberFormat_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", "10", "cinco", "10" };

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si alguno de los campos es nulo.")
    public void anyadirActividad_NullValues_ThrowsError() throws ClubException {
        String nombre = "Club Alfarfa";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = { "D4", "Tenis", null, "5", "10" };

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si los datos son nulos.")
    public void anyadirActividad_NullDatos_ThrowsError() throws ClubException {
        String nombre = "Club Alfarfa";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = null;

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo no existe.")
    public void anyadirActividad_IncorrectGroup_ThrowsError() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);

        Grupo grupo = null;

        assertThrows(ClubException.class, () -> club.anyadirActividad(grupo));
    }

    @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_NewGroup_ReturnsExpectedResult() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10);
        String expectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:10 - M:5) ]";

        club.anyadirActividad(grupo);
        String toString = club.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("El método anyadirActividad debe modificar la plazas del grupo ya existente.")
    public void anyadirActividad_ExistingGroup_ReturnsExpectedResult() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10);
        Grupo grupo2 = new Grupo("D4", "Tenis", 15, 5, 10);
        String expectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:15 - M:5) ]";

        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        String toString = club.toString();

        assertEquals(expectedString, toString);
    }
}
