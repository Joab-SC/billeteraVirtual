import co.edu.uniquindio.poo.Banco;
import co.edu.uniquindio.poo.Usuario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BancoTest {

    @Test
    public void crearNumeroAletatorioTest() throws Exception {
        String numeroAleatorio = Banco.generarNumeroAleatorio(10);
        assertEquals(10, numeroAleatorio.length());
    }

    @Test
    public void crearUsuarioTest() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario("Carlos", "Av. Siempre Viva", "987654321", "carlos@mail.com", "password123");

        assertDoesNotThrow(() -> {
            usuarios.add(usuario1);
        });
        assertTrue(usuarios.contains(usuario1));
    }

    @Test
    public void actualizarUsuarioTest() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Se crea y agrega un usuario inicial
        Usuario usuario = new Usuario("maicol", "calle6", "123456789", "dhsgvff@", "vfvfvf");
        usuarios.add(usuario);

        // Se crea un nuevo usuario con datos actualizados
        Usuario usuarioActualizado = new Usuario("maicol", "calle10", "987654321", "nuevoCorreo@mail.com", "nuevaClave");

        // Se espera que no se lance ninguna excepción al actualizar
        assertDoesNotThrow(() -> {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getNombre().equals(usuarioActualizado.getNombre())) {
                    usuarios.set(i, usuarioActualizado);
                    break;
                }
            }
        });

        // Se obtiene el usuario actualizado
        Usuario usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getNombre().equals("maicol"))
                .findFirst()
                .orElse(null);

        // Se espera que el usuario no sea nulo y que tenga los datos actualizados
        assertNotNull(usuarioEncontrado);
        assertEquals("calle10", usuarioEncontrado.getDireccion());
        assertEquals("987654321", usuarioEncontrado.getContrasena());
        assertEquals("nuevoCorreo@mail.com", usuarioEncontrado.getCorreo());
    }

    @Test
    public void agregarUsuarioTest() {
        // Se crea una lista de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Se crea un nuevo usuario con los atributos específicos
        Usuario nuevoUsuario = new Usuario("Maicol", "Calle 6", "123456789", "maicol@mail.com", "password123");

        // Se espera que no se lance ninguna excepción al agregar el usuario
        assertDoesNotThrow(() -> {
            usuarios.add(nuevoUsuario);
        });

        // Se busca el usuario en la lista
        Usuario usuarioAgregado = usuarios.stream()
                .filter(u -> u.getNumeroIdentificacion().equals("123456789"))
                .findFirst()
                .orElse(null);

        // Se espera que el usuario haya sido agregado correctamente
        assertNotNull(usuarioAgregado);
        assertEquals("Maicol", usuarioAgregado.getNombre());
        assertEquals("Calle 6", usuarioAgregado.getDireccion());
        assertEquals("123456789", usuarioAgregado.getNumeroIdentificacion());
        assertEquals("maicol@mail.com", usuarioAgregado.getCorreo());
        assertEquals("password123", usuarioAgregado.getContrasena());
    }

    @Test
    public void eliminarUsuarioTest() {
        // Se crea ua lista de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Se agrega un usuario a la lista
        Usuario usuario = new Usuario("Maicol", "Calle 6", "123456789", "maicol@mail.com", "password123");
        usuarios.add(usuario);

        // Se espera que no se lance ninguna excepción al eliminar el usuario con identificación 123456789
        assertDoesNotThrow(() -> {
            usuarios.removeIf(u -> u.getNumeroIdentificacion().equals("123456789"));
        });

        // Se busca el usuario en la lista después de eliminarlo
        Usuario usuarioEliminado = usuarios.stream()
                .filter(u -> u.getNumeroIdentificacion().equals("123456789"))
                .findFirst()
                .orElse(null);

        // Se espera que el usuario ya no exista en la lista
        assertNull(usuarioEliminado);
    }

    @Test
    public void crearNumeroAletorioNegativo() throws Exception {
        assertThrows(Throwable.class, () -> Banco.generarNumeroAleatorio(-2));
    }
}