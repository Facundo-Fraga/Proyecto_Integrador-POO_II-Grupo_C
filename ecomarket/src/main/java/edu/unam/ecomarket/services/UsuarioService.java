package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioService {

    @Autowired
    protected UsuarioRepository repository;



    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cargarUsuario(Usuario nuevoUsuario) {
        repository.save(nuevoUsuario);
    }

    public List<Usuario> buscarUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public Cliente obtenerClienteEnSesion(HttpSession session) {
        Cliente clienteActual = (Cliente) session.getAttribute("clienteActual");
        if (clienteActual == null) {
            throw new IllegalStateException("No hay un cliente autenticado en la sesión.");
        }
        return clienteActual;
    }
    // Método para editar y persistir un usuario
    public Usuario editarUsuario(Usuario usuarioEditado) {
        // Buscar el usuario por ID (o por otro identificador único)
        Usuario usuarioExistente = repository.findById(usuarioEditado.getIdUsuario()).orElse(null);

        if (usuarioExistente != null) {
            // Actualizar los campos del usuario existente con los nuevos valores
            usuarioExistente.setNombre(usuarioEditado.getNombre());
            usuarioExistente.setContrasenia(usuarioEditado.getContrasenia());
            usuarioExistente.setEmail(usuarioEditado.getEmail());
            // Aquí puedes agregar más campos según lo que quieras actualizar

            // Guardar el usuario actualizado en la base de datos
            return repository.save(usuarioExistente);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
        }
    }
}

