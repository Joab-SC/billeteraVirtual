import co.edu.uniquindio.poo.Banco;
import co.edu.uniquindio.poo.BilleteraVirtual;
import co.edu.uniquindio.poo.Usuario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BancoTest {

    Banco banco = new Banco("Davivienda");
    Usuario usuario1 = new Usuario("Carlos", "Av. Siempre Viva", "987654321", "carlos@mail.com", "password123");
    Usuario usuario2 = new Usuario("Maicol", "Caicedonia", "124234234", "maicol@mail.com", "michkael123");
    Usuario usuario3 = new Usuario("Sergio", "Armenia", "124234234", "maicol@mail.com", "michkael123");

    @Test
    public void crearBilleteraTest() throws Exception {
        BilleteraVirtual billetera1 = Banco.crearBilletera(2000, null );
        BilleteraVirtual billetera = new BilleteraVirtual(2000, billetera1.getCodigo(), null);

        assertEquals(billetera1.getSaldo(), billetera.getSaldo());
        assertEquals(billetera1.getTransacciones(), billetera.getTransacciones());
        assertEquals(billetera1.getCodigo(), billetera.getCodigo());
        assertEquals(billetera1.getUsuario(), billetera.getUsuario());
        assertEquals(billetera1.getSaldo(), billetera.getSaldo());
    }


    @Test
    public void crearNumeroAletatorioTest() throws Exception {
        String numeroAleatorio = Banco.generarNumeroAleatorio(10);
        assertEquals(10, numeroAleatorio.length());
    }

    @Test
    public void crearUsuarioTest() {
        Usuario usuario2 = Banco.crearUsuario("Carlos", "Av. Siempre Viva", "987654321", "carlos@mail.com", "password123");
        assertEquals(usuario1.getNombre(), usuario2.getNombre());
        assertEquals(usuario1.getNumeroIdentificacion(), usuario2.getNumeroIdentificacion());
        assertEquals(usuario1.getContrasena(), usuario2.getContrasena());
        assertEquals(usuario1.getCorreo(), usuario2.getCorreo());
        assertEquals(usuario1.getDireccion(), usuario2.getDireccion());
    }


    @Test
    public void agregarUsuarioTest() throws Exception {
        // Se crea una lista de usuarios
        banco.agregarUsuario(usuario1);
        banco.agregarUsuario(usuario2);
        assertTrue(banco.getUsuarios().contains(usuario1) && banco.getUsuarios().contains(usuario2));
        assertThrows(Throwable.class, () -> banco.agregarUsuario(usuario3));



    }

    @Test
    public void crearNumeroAletorioNegativoTest() throws Exception {
        assertThrows(Throwable.class, () -> Banco.generarNumeroAleatorio(-2));
    }
}