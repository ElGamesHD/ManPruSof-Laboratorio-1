package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoMatricularTest {
    
    @Test
    @DisplayName("El método matricular debe lanzar una excepción si no hay plazas libres suficientes.")
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
    @DisplayName("El método matricular correctamente en una actividad con dos grupos.")
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

    @Test
    @DisplayName("El método matricular debe no debe matricular si no hay personas para matricular.")
    public void matricular_noPerson_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        club.anyadirActividad(grupo);

        int plazasExpected = club.plazasLibres("Tenis");
        club.matricular("Tenis", 0);
        assertEquals(plazasExpected, club.plazasLibres("Tenis"));
    }

    @Test
    @DisplayName("El método matricular debe matricular en la actividad correcta.")
    public void matricular_goodDataWithTwoGroups_ReturnTrue() throws ClubException{
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        Grupo grupo = new Grupo("D4","Tenis", 10, 5, 10);
        Grupo grupo2 = new Grupo("D3","Futbol", 10, 5, 10);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);

        int plazasExpected = 4;
        club.matricular("Futbol", 1);
        assertEquals(plazasExpected, club.plazasLibres("Futbol"));
    }
}
