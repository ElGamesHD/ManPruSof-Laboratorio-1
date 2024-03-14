package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

   

}
