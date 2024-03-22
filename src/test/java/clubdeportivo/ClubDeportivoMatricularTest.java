/**
 * @author Eulogio Quemada Torres
 * @author Alejandro Román Sánchez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoMatricularTest {

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si no hay plazas libres suficientes.")
    public void matricular_NotEnoughPlaces_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        int plazas = 7;
        int maxPlazas = 10;
        Grupo grupo = new Grupo("D4", "Tenis", maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        int personasMatricular = maxPlazas - plazas + 1;

        assertThrows(ClubException.class, () -> {
            club.matricular("Tenis", personasMatricular);
        });

    }

    @Test
    @DisplayName("El método matricular debe matricular correctamente en una actividad si los datos son correctos.")
    public void matricular_FreePlaces_Success() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        int maxPlazas = 10;
        int plazas = 5;
        int matricularPersonas = 3;
        Grupo grupo = new Grupo("D4", actividad, maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        int plazasExpected = maxPlazas - (plazas + matricularPersonas);

        club.matricular(actividad, matricularPersonas);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método matricular debe usar otro grupo si lo hay para matricular en caso de que en un primero no haya plazas suficientes.")
    public void matricular_NotEnoughPlacesInOneGroup_UseSecondGroup() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        int maxPlazas = 10;
        int plazas = 5;
        int matricularPersonas = 7;
        Grupo grupo = new Grupo("D4", actividad, maxPlazas, plazas, 10.0);
        Grupo grupo2 = new Grupo("D3", actividad, maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = 2 * (maxPlazas - plazas) - matricularPersonas;

        club.matricular(actividad, matricularPersonas);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método matricular debe lanzar error si se intenta matricular un número menor o igual a cero personas")
    public void matricular_LessThanOrEqualZeroPeople_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        Grupo grupo = new Grupo("D4", actividad, 10, 5, 10.0);
        club.anyadirActividad(grupo);
        int personasMatricular = 0;

        assertThrows(ClubException.class, () -> {
            club.matricular(actividad, personasMatricular);
        });
    }

    @Test
    @DisplayName("El método matricular no debe matricular a más personas de las indicadas.")
    public void matricular_TwoGroupsOnlyUseOne_NotMoreThanIndicated() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        int maxPlazas = 10;
        int plazas = 5;
        int personasMatricular = 3;
        Grupo grupo = new Grupo("D4", actividad, maxPlazas, plazas, 10.0);
        Grupo grupo2 = new Grupo("D3", actividad, maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = 2 * (maxPlazas - plazas) - personasMatricular;

        club.matricular(actividad, personasMatricular);
        int plazasLibres = club.plazasLibres(actividad);

        assertEquals(plazasExpected, plazasLibres);
    }

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si la actividad es nula.")
    public void matricular_ActivityNull_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        Grupo grupo = new Grupo("D4", "Tenis", 10, 5, 10.0);
        club.anyadirActividad(grupo);
        String actividadNull = null;
        int personasMatricular = 5;

        assertThrows(ClubException.class, () -> club.matricular(actividadNull, personasMatricular));
    }

    @Test
    @DisplayName("El método matricular debe lanzar una excepción si el número de personas es negativo.")
    public void matricular_NegativePeople_ThrowsError() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        Grupo grupo = new Grupo("D4", actividad, 10, 5, 10.0);
        club.anyadirActividad(grupo);
        int personasMatricular = -5;

        assertThrows(ClubException.class, () -> club.matricular(actividad, personasMatricular));
    }

    @Test
    @DisplayName("El método matricular debe matricular en la actividad correcta.")
    public void matricular_GoodDataWithTwoGroups_ReturnTrue() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club de Tenis");
        String actividad = "Tenis";
        int maxPlazas = 10;
        int plazas = 5;
        int personasMatricular = 1;
        Grupo grupo = new Grupo("D4", actividad, maxPlazas, plazas, 10.0);
        String actividad2 = "Futbol";
        Grupo grupo2 = new Grupo("D3", actividad2, maxPlazas, plazas, 10.0);
        club.anyadirActividad(grupo);
        club.anyadirActividad(grupo2);
        int plazasExpected = maxPlazas - (plazas + personasMatricular);

        club.matricular(actividad2, personasMatricular);
        int plazasLibres = club.plazasLibres(actividad2);

        assertEquals(plazasExpected, plazasLibres);
    }
}
