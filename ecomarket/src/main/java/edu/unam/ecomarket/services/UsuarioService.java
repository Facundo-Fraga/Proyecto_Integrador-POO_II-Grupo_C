package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

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
        Usuario encontrado = repository.findByNombre(nombre);
        return encontrado;
    }
}
