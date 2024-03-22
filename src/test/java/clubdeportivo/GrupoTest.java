/**
 * @author Eulogio Quemada Torres
 * @author Alejandro Román Sánchez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        double tarifa = 5.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Inicializar un grupo con valores validos, lo crea correctamente")
    public void Grupo_ValidValues_Successfull() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 15;
        int matriculados = 10;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        String expectedString = "(" + codigo + " - " + actividad + " - " + tarifa + " euros " + "- P:" + nplazas
                + " - M:" + matriculados + ")";

        String toString = grupo.toString();

        assertEquals(expectedString, toString);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 5",
            "10, 10, 0",
            "25, 4, 21"
    })
    @DisplayName("Al matricular a N personas en un grupo de M plazas, las plazas libres serán M - N")
    public void plazasLibres_OnInit_ReturnsRemainingSpots(int nplazas, int matriculados, int expectedValue)
            throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        double tarifa = 5.0;

        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        int plazasLibres = grupo.plazasLibres();

        assertEquals(expectedValue, plazasLibres);
    }

    @Test
    @DisplayName("Actualizar el número de plazas a un número menor o igual a 0 lanza un error")
    public void actualizarPlazas_LessThanOrEqualZero_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(0);
        });
    }

    @Test
    @DisplayName("Actualizar el número de plazas a un número menor que el número de matriculados lanza un error")
    public void actualizarPlazas_LessThanMatriculados_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(nplazas - matriculados - 1);
        });
    }

    @Test
    @DisplayName("Actualizar el número de plazas a un número válido cambia el número de plazas correctamente")
    public void actualizarPlazas_ValidNumber_ChangesPlazas() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        grupo.actualizarPlazas(nplazas * 2);
        int getPlazas = grupo.getPlazas();

        assertEquals(nplazas * 2, getPlazas);
    }

    @Test
    @DisplayName("Matricular a un número de alumnos nuevos mayor que el de las plazas libres restantes lanza un error")
    public void matricular_MoreThanPlazasLibres_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertThrows(ClubException.class, () -> {
            grupo.matricular(nplazas - matriculados + 1);
        });
    }

    @Test
    @DisplayName("Matricular a un número de alumnos nuevos menor o igual a 0 lanza un error")
    public void matricular_LessThanOrEqualZero_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertThrows(ClubException.class, () -> {
            grupo.matricular(0);
        });
    }

    @Test
    @DisplayName("Matricular a un número de alumnos positivo y menor o igual que las plazas libres, incrementa los matriculados correctamente")
    public void matricular_ValidValue_AddMatriculados() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        grupo.matricular(nplazas - matriculados);
        int getMatriculados = grupo.getMatriculados();

        assertEquals(nplazas, getMatriculados);
    }

    @Test
    @DisplayName("El método toString devuelve el formato esperado")
    public void toString_AnyCase_ReturnsExpectedFormat() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        String expectedFormat = "(" + codigo + " - " + actividad + " - " + tarifa + " euros " + "- P:" + nplazas
                + " - M:" + matriculados + ")";

        String toString = grupo.toString();

        assertEquals(toString, expectedFormat);
    }

    @Test
    @DisplayName("Si un grupo se compara con cualquier otro tipo de objeto, equals devuelve true")
    public void equals_DifferentInstance_ReturnsFalse() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        String grupo2 = "Grupo2";

        boolean equals = grupo1.equals(grupo2);

        assertFalse(equals);
    }

    @Test
    @DisplayName("Si dos grupos tienen codigo diferente, no son el mismo grupo, por tanto equals devuelve false")
    public void equals_DifferentCodigo_ReturnsFalse() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo2", "Actividad1", 20, 1, 15.0);

        boolean equals = grupo1.equals(grupo2);

        assertFalse(equals);
    }

    @Test
    @DisplayName("Si dos grupos tienen actividad diferente, no son el mismo grupo, por tanto equals devuelve false")
    public void equals_DifferentActividad_ReturnsFalse() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo1", "Actividad2", 20, 1, 15.0);

        boolean equals = grupo1.equals(grupo2);

        assertFalse(equals);
    }

    @Test
    @DisplayName("Si dos grupos tienen el mismo codigo y actividad, son el mismo grupo, por tanto equals devuelve true")
    public void equals_SameGrupoAndActividad_ReturnsTrue() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo1", "Actividad1", 20, 1, 15.0);

        boolean equals = grupo1.equals(grupo2);

        assertTrue(equals);
    }

    @Test
    @DisplayName("Si dos grupos son iguales, entonces tienen el mismo hashCode")
    public void hashCode_SameGrupo_IsEqual() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo1", "Actividad1", 20, 1, 15.0);

        int hashCode1 = grupo1.hashCode();
        int hashCode2 = grupo2.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Si dos grupos no son iguales, entonces tienen distinto hashCode")
    public void hashCode_DifferentGrupo_IsNotEqual() throws ClubException {
        Grupo grupo1 = new Grupo("Grupo1", "Actividad1", 10, 5, 5.0);
        Grupo grupo2 = new Grupo("Grupo2", "Actividad1", 20, 1, 15.0);

        int hashCode1 = grupo1.hashCode();
        int hashCode2 = grupo2.hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Crear un grupo con codigo nulo lanza un error")
    public void Grupo_CodigoNull_ThrowsError() {
        String codigo = null;
        String actividad = "Actividad1";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Crear un grupo con actividad nula lanza un error")
    public void Grupo_ActividadNull_ThrowsError() {
        String codigo = "Grupo1";
        String actividad = null;
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }
}
