package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAnyadirActividadTest {
     @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_correctData_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = {"D4","Tenis", "10", "5", "10"};
        club.anyadirActividad(datos);
        String exectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:10 - M:5) ]";
        assertEquals(club.toString(), exectedString);
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si los datos son incorrectos.")
    public void anyadirActividad_inCorrectData_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String[] datos = {"D4","Tenis", "10", "cinco", "10"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si el grupo no existe.")
    public void anyadirActividad_IncorrectGroup_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = null;
        assertThrows(ClubException.class, () -> club.anyadirActividad(grupo));
    }

    @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_newGroup_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);
        String exectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:10 - M:5) ]";
        assertEquals(club.toString(), exectedString);
    }

    @Test
    @DisplayName("El método anyadirActividad debe crear y añadir correctamente el grupo al club deportivo.")
    public void anyadirActividad_existingGroup_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        Grupo grupo2 = new Grupo("D4","Tenis", 15, 5, 10);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        String exectedString = nombre + " --> [ (D4 - Tenis - 10.0 euros - P:15 - M:5) ]";
        assertEquals(club.toString(), exectedString);
    }
}
