import co.edu.uniquindio.poo.Banco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    public void crearNumeroAletatorioTest() throws Exception {
        String numeroAleatorio = Banco.generarNumeroAleatorio(10);
        assertEquals(10, numeroAleatorio.length());
    }

    @Test
    public void crearNumeroAletorioNegativo() throws Exception {
        assertThrows(Throwable.class, () -> Banco.generarNumeroAleatorio(-2));

    }
}
