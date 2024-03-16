package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {

    @Test
    @DisplayName("Inicializar un club deportivo de alto rendimiento con un maximo menor o igual a cero, lanza un error")
    public void ClubDeportivoAltoRendimiento_MaximoLessThanOrEqualZero_ThrowsError() {
        String nombre = "ClubAltoRendimiento1";
        int maximo = 0;
        double incremento = 1.0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Inicializar un club deportivo de alto rendimiento con un incremento menor o igual a cero, lanza un error")
    public void ClubDeportivoAltoRendimiento_IncrementoLessThanOrEqualZero_ThrowsError() {
        String nombre = "ClubAltoRendimiento1";
        int maximo = 1;
        double incremento = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Inicializar un club deportivo con valores validos, lo crea correctamente")
    public void ClubDeportivoAltoRendimiento_ValidValues_Successfull() throws ClubException {
        String nombre = "ClubAltoRendimiento1";
        int maximo = 5;
        double incremento = 1.0;
        String expectedString = nombre + " --> [  ]";
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

        String toString = clubAltoRendimiento.toString();

        assertEquals(expectedString, toString);
    }

    @Test
    @DisplayName("Inicializar un club deportivo de alto rendimiento con un maximo menor o igual a cero, lanza un error")
    public void ClubDeportivoAltoRendimientoTam_MaximoLessThanOrEqualZero_ThrowsError() {
        String nombre = "ClubAltoRendimiento1";
        int tam = 10;
        int maximo = 0;
        double incremento = 1.0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Inicializar un club deportivo de alto rendimiento con un incremento menor o igual a cero, lanza un error")
    public void ClubDeportivoAltoRendimientoTam_IncrementoLessThanOrEqualZero_ThrowsError() {
        String nombre = "ClubAltoRendimiento1";
        int tam = 10;
        int maximo = 1;
        double incremento = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Inicializar un club deportivo con valores validos, lo crea correctamente")
    public void ClubDeportivoAltoRendimientoTam_ValidValues_Successfull() throws ClubException {
        String nombre = "ClubAltoRendimiento1";
        int tam = 10;
        int maximo = 5;
        double incremento = 1.0;
        String expectedString = nombre + " --> [  ]";
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, tam, maximo,
                incremento);

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
    @DisplayName("Los ingresos de un club deportivo de alto rendimiento sin grupos es cero")
    public void ingresos_EmptyClub_ReturnsZero() throws ClubException {
        String nombre = "ClubAltoRendimiento1";
        int maximo = 1;
        double incremento = 2.5;
        double expectedValue = 0.0;
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

        double ingresos = clubAltoRendimiento.ingresos();

        assertEquals(expectedValue, ingresos);
    }

    @Test
    @DisplayName("Los ingresos de un club deportivo de alto rendimiento son iguales que los de un club deportivo normal más el incremento por alto rendimiento")
    public void ingresos_MoreThanZeroClubs_ReturnsExpectedValue() throws ClubException {
        String[] data1 = { "Grupo1", "Actividad1", "20", "5", "5.0" };
        String[] data2 = { "Grupo2", "Actividad2", "20", "10", "4.0" };
        ClubDeportivoAltoRendimiento clubAltoRendimiento = new ClubDeportivoAltoRendimiento("Club1", 35, 2.5);
        double expectedValue = 66.625;

        clubAltoRendimiento.anyadirActividad(data1);
        clubAltoRendimiento.anyadirActividad(data2);
        double ingresos = clubAltoRendimiento.ingresos();

        assertEquals(expectedValue, ingresos);
    }
}
