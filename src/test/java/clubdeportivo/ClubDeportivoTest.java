package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos es negativo.")
    public void ClubDeportivo_NegativeNumberGroup_ThrowsClubException() throws ClubException{
        String nombre = "Club de Tenis";
        assertThrows(ClubException.class, () -> new ClubDeportivo(nombre, -5));
    }

    @Test
    @DisplayName("El constructor de ClubDeportivo debe lanzar una excepción si el número de grupos es 0 o negativo.")
    public void ClubDeportivo_CorrectNumberGroup_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String expetedString = nombre + " --> [  ]";
        assertEquals(club.toString(), expetedString);
    }

    @Test
    @DisplayName("El método plazasLibres debe devolver correctamente las plazas libres de una actividad.")
    public void plazasLibres_existsActivity_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);

        int plazasExpected = 5;
        assertEquals(plazasExpected, club.plazasLibres("Tenis"));
    }
   
    @Test
    @DisplayName("El método plazasLibres debe devolver 0 si no existe la actividad.")
    public void plazasLibres_notExistsActivity_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);

        int plazasExpected = 0;
        assertEquals(plazasExpected, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName("El método ingresos debe devolver 0 si no hay grupos.")
    public void ingresos_noGroups_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        double expected = 0;
        assertEquals(expected, club.ingresos());
    }

    @Test
    public void ingresos_hasGroups_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        Grupo grupo2 = new Grupo("D5","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);   
        club.anyadirActividad(grupo2);

        double expected = 100;
        assertEquals(expected, club.ingresos());
    }



}
