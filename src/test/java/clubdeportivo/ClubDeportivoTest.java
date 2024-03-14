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
    public void matricular_noFreePlaces_ThrowsClubException() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 10, 10);
        club.anyadirActividad(grupo);

        assertThrows(ClubException.class, () -> club.matricular("Tenis", 5));
        
    }

    @Test
    @DisplayName("El método matricular debe matricular correctamente en una actividad.")
    public void matricular_FreePlaces_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);

        int plazasExpected = 2;
        club.matricular("Tenis", 3);
        assertEquals(plazasExpected, club.plazasLibres("Tenis"));
        
    }

    @Test
    @DisplayName("El método matricular debe devolver 0 si no existe esa actividad.")
    public void matricular_notEnoughPlacesInOneGroup_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        Grupo grupo2 = new Grupo("D5","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);   
        club.anyadirActividad(grupo2);

        int plazasExpected = 3;
        club.matricular("Tenis", 7);
        assertEquals(plazasExpected, club.plazasLibres("Tenis"));
    }




}
