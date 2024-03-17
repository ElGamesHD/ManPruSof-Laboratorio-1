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
