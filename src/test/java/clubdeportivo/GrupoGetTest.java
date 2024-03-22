/**
 * @author Eulogio Quemada Torres
 * @author Alejandro Román Sánchez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoGetTest {

    @Test
    @DisplayName("El getter de codigo deuelve el codigo pasado por el constructor al grupo")
    public void getCodigo_OnInit_ReturnsCodigo() throws ClubException {
        String codigo = "Grupo1";

        Grupo grupo = new Grupo(codigo, "Actividad1", 10, 5, 5.0);
        String getCodigo = grupo.getCodigo();

        assertEquals(codigo, getCodigo);
    }

    @Test
    @DisplayName("El getter de actividad deuelve la actividad pasada por el constructor al grupo")
    public void getActividad_OnInit_ReturnsActividad() throws ClubException {
        String actividad = "Actividad1";

        Grupo grupo = new Grupo("Grupo1", actividad, 10, 5, 5.0);
        String getActividad = grupo.getActividad();

        assertEquals(actividad, getActividad);
    }

    @Test
    @DisplayName("El getter de número de plazas deuelve el número de plazas pasado por el constructor al grupo")
    public void getPlazas_OnInit_ReturnsPlazas() throws ClubException {
        int nplazas = 10;

        Grupo grupo = new Grupo("Grupo1", "Actividad1", nplazas, 5, 5.0);
        int getPlazas = grupo.getPlazas();

        assertEquals(nplazas, getPlazas);
    }

    @Test
    @DisplayName("El getter de número de matriculados deuelve el número de matriculados pasado por el constructor al grupo")
    public void getMatriculados_OnInit_ReturnsMatriculados() throws ClubException {
        int matriculados = 5;

        Grupo grupo = new Grupo("Grupo1", "Actividad1", 10, matriculados, 5.0);
        int getMatriculados = grupo.getMatriculados();

        assertEquals(matriculados, getMatriculados);
    }

    @Test
    @DisplayName("El getter de la tarifa deuelve la tarifa pasada por el constructor al grupo")
    public void getTarifa_OnInit_ReturnsTarifa() throws ClubException {
        double tarifa = 5.0;

        Grupo grupo = new Grupo("Grupo1", "Actividad1", 10, 5, tarifa);
        double getTarifa = grupo.getTarifa();

        assertEquals(tarifa, getTarifa);
    }
}
