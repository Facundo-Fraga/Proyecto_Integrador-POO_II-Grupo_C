package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los usuarios.
 * 
 * <p>
 * Este servicio incluye operaciones CRUD para usuarios, gestión de clientes en
 * sesión y edición de datos de usuarios existentes.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * <p>
 * Se apoya en el repositorio {@link UsuarioRepository} para interactuar con la base de datos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class UsuarioService {

    @Autowired
    protected UsuarioRepository repository;

    /**
     * Constructor para inicializar el servicio con el repositorio necesario.
     * 
     * @param repository Repositorio para gestionar los datos de usuarios.
     */
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     * 
     * @param nuevoUsuario El usuario a guardar.
     */
    public void cargarUsuario(Usuario nuevoUsuario) {
        repository.save(nuevoUsuario);
    }

    /**
     * Obtiene una lista de todos los usuarios registrados en la base de datos.
     * 
     * @return Lista de usuarios.
     */
    public List<Usuario> buscarUsuarios() {
        return repository.findAll();
    }

    /**
     * Busca un usuario por su nombre.
     * 
     * @param nombre Nombre del usuario a buscar.
     * @return El usuario encontrado, o {@code null} si no existe.
     */
    public Usuario buscarUsuarioPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    /**
     * Obtiene el cliente autenticado en la sesión actual.
     * 
     * @param session Objeto {@link HttpSession} que contiene los datos de la sesión.
     * @return El cliente autenticado.
     * @throws IllegalStateException Si no hay un cliente autenticado en la sesión.
     */
    public Cliente obtenerClienteEnSesion(HttpSession session) {
        Cliente clienteActual = (Cliente) session.getAttribute("clienteActual");
        if (clienteActual == null) {
            throw new IllegalStateException("No hay un cliente autenticado en la sesión.");
        }
        return clienteActual;
    }

    /**
     * Edita y persiste un usuario existente en la base de datos.
     * 
     * <p>
     * Busca el usuario por su ID y actualiza los campos con los nuevos valores proporcionados.
     * </p>
     * 
     * @param usuarioEditado Objeto {@link Usuario} con los nuevos datos.
     * @return El usuario actualizado.
     * @throws IllegalArgumentException Si no se encuentra un usuario con el ID proporcionado.
     */
    public Usuario editarUsuario(Usuario usuarioEditado) {
        Usuario usuarioExistente = repository.findById(usuarioEditado.getIdUsuario()).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioEditado.getNombre());
            usuarioExistente.setContrasenia(usuarioEditado.getContrasenia());
            usuarioExistente.setEmail(usuarioEditado.getEmail());
            return repository.save(usuarioExistente);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
        }
    }
}
