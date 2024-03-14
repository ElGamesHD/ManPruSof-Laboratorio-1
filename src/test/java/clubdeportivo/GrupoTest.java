package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Crear un grupo con 0 o menos plazas lanza un error")
    public void Grupo_LessThanOrEqualZeroPlazas_ThrowsError() {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 0;
        int matriculados = 5;
        double tarifa = 5.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Crear un grupo con matriculados negativos lanza un error")
    public void Grupo_NegativeMatriculado_ThrowsError() {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 5;
        int matriculados = -1;
        double tarifa = 5.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Crear un grupo con 0 o menos de tarifa lanza un error")
    public void Grupo_LessThanOrEqualZeroTarifa_ThrowsError() {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 5;
        int matriculados = 5;
        double tarifa = 0.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Crear un grupo con más matriculados que plazas lanza un error")
    public void Grupo_MoreMatriculadosThanPlazas_ThrowsError() {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 5;
        int matriculados = 10;
        double tarifa = 0.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("El getter de codigo deuelve el codigo pasado por el constructor al grupo")
    public void getCodigo_OnInit_ReturnsCodigo() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 5;
        int matriculados = 10;
        double tarifa = 0.0;

        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertEquals(codigo, grupo.getCodigo());
    }
}
