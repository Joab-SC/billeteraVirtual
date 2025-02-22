import co.edu.uniquindio.poo.Banco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    public void crearNumeroAletatorioTest() {
        String numeroAleatorio = Banco.generarNumeroAleatorio(10);
        assertEquals(10, numeroAleatorio.length());
    }
}
