package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoMatricularTest {

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si no hay plazas libres suficientes.")
    public void matricular_noFreePlaces_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 7;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        int personasMatricular = 5;

        assertThrows(ClubException.class, () -> club.matricular("Tenis", personasMatricular));

    }

    @Test
    @DisplayName("El método matricular debe matricular correctamente en una actividad.")
    public void matricular_FreePlaces_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        int n = 3;
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        int plazasExpected = 2;

        club.matricular(actividad, n);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);

    }

    @Test
    @DisplayName("El método matricular correctamente en una actividad con dos grupos.")
    public void matricular_notEnoughPlacesInOneGroup_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        int n = 7;
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        String codigo2 = "D3";
        Grupo grupo2 = new Grupo(codigo2, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = 3;

        club.matricular(actividad, n);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método matricular debe no debe matricular si no hay personas para matricular.")
    public void matricular_noPerson_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        int personasMatricular = 0;

        assertThrows(ClubException.class, () -> club.matricular(actividad, personasMatricular));
    }

    @Test
    @DisplayName("El método matricular debe no debe matricular si no hay personas para matricular.")
    public void matricular_TwoGroupsOnlyUseOne_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        int n = 3;
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        String codigo2 = "D3";
        Grupo grupo2 = new Grupo(codigo2, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = 7;

        club.matricular(actividad, n);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si la actividad es nula.")
    public void matricular_ActivityNull_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        String actividadNull = null;
        int personasMatricular = 5;

        assertThrows(ClubException.class, () -> club.matricular(actividadNull, personasMatricular));
    }

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si el número de personas es negativo.")
    public void matricular_NegativePeople_ThrowsClubException() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        int personasMatricular = -5;

        assertThrows(ClubException.class, () -> club.matricular(actividad, personasMatricular));
    }

    @Test
    @DisplayName("El método matricular debe matricular en la actividad correcta.")
    public void matricular_goodDataWithTwoGroups_ReturnTrue() throws ClubException {
        String nombre = "Club de Tenis";
        ClubDeportivo club = new ClubDeportivo(nombre);
        int n = 1;
        String codigo = "D4";
        String actividad = "Tenis";
        int precio = 10;
        int plazas = 5;
        int maxPlazas = 10;
        Grupo grupo = new Grupo(codigo, actividad, maxPlazas, plazas, precio);
        String codigo2 = "D3";
        String actividad2 = "Futbol";
        Grupo grupo2 = new Grupo(codigo2, actividad2, maxPlazas, plazas, precio);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = 4;

        club.matricular(actividad2, n);
        int plazasLibres = club.plazasLibres(actividad2);

        assertEquals(plazasExpected, plazasLibres);
    }
}
