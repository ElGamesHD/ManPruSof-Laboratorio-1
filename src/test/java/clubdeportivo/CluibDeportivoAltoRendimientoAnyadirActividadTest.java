package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CluibDeportivoAltoRendimientoAnyadirActividadTest {
    @Test
    @DisplayName("Al anyadirActividad con un grupo con datos validos, el grupo es anyadido correctamente al club")
    public void anyadirActividad_ValidValues_AddGrupoToClub() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        String plazas = "10";
        String matriculados = "5";
        String tarifa = "5.0";
        String[] data = { codigo, actividad, plazas, matriculados, tarifa };
        String nombre = "Club1";
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, 15, 2.5);
        String expectedString = nombre + " --> [ (" + codigo + " - " + actividad + " - " + tarifa + " euros - P:"
                + plazas + " - M:" + matriculados + ") ]";

        clubAltoRendimiento.anyadirActividad(data);
        String toString = clubAltoRendimiento.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("Al anyadirActividad con un numero de datos menor a 5 (el necesario) se lanza un error")
    public void anyadirActividad_LessThanFiveArgumentsArray_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        String plazas = "10";
        String matriculados = "5";
        String[] data = { codigo, actividad, plazas, matriculados };
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento("Club1", 15, 2.5);

        assertThrows(ClubException.class, () -> {
            clubAltoRendimiento.anyadirActividad(data);
        });
    }

    @Test
    @DisplayName("Anyadir al club un grupo con campos numericos con un formato incorrecto lanza un error")
    public void anyadirActividad_IncorrectNumberFormat_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        String plazas = "aaa";
        String matriculados = "5";
        String tarifa = "5.0";
        String[] data = { codigo, actividad, plazas, matriculados, tarifa };
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento("Club1", 15, 2.5);

        assertThrows(ClubException.class, () -> {
            clubAltoRendimiento.anyadirActividad(data);
        });
    }

    @Test
    @DisplayName("El método anyadirActividad debe lanzar una excepción si alguno de los campos es nulo.")
    public void anyadirActividad_NullValues_ThrowsError() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        String plazas = null;
        String matriculados = "5";
        String tarifa = "5.0";
        String[] data = { codigo, actividad, plazas, matriculados, tarifa };
        String nombre = "Club1";
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, 15, 2.5);

        assertThrows(ClubException.class, () -> {
            clubAltoRendimiento.anyadirActividad(data);
        });
    }

    /*
     * De error arreglado
     */
    @Test
    @DisplayName("Al anyadirActividad con el vector datos nulo, se lanza un error")
    public void anyadirActividad_NullDatos_ThrowsError() throws ClubException {
        String[] data = null;
        String nombre = "Club1";
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, 15, 2.5);

        assertThrows(ClubException.class, () -> {
            clubAltoRendimiento.anyadirActividad(data);
        });
    }

    @Test
    @DisplayName("Al anyadir al club un grupo con mas plazas que el maximo, se establece su valor al maximo permitido por grupo para el club")
    public void anyadirActividad_MorePlazasThanMax_SetPlazasToMax() throws ClubException {
        String codigo = "Grupo1";
        String actividad = "Actividad1";
        String plazas = "20";
        String matriculados = "5";
        String tarifa = "5.0";
        String[] data = { codigo, actividad, plazas, matriculados, tarifa };
        String nombre = "Club1";
        int maximo = 15;
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, maximo, 2.5);
        String expectedString = nombre + " --> [ (" + codigo + " - " + actividad + " - " + tarifa + " euros - P:"
                + maximo + " - M:" + matriculados + ") ]";

        clubAltoRendimiento.anyadirActividad(data);
        String toString = clubAltoRendimiento.toString();

        assertEquals(expectedString, toString);
    }
}
