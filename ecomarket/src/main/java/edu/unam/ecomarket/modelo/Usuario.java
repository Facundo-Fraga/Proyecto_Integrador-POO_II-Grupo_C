package edu.unam.ecomarket.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase abstracta que representa un usuario en el sistema.
 * 
 * <p>
 * Esta clase es la base para los diferentes tipos de usuarios (por ejemplo,
 * administrador y cliente) y define atributos comunes como nombre, contraseña
 * y correo electrónico.
 * </p>
 * 
 * <p>
 * Está diseñada para ser utilizada con herencia en JPA, almacenando todos los
 * tipos de usuarios en una única tabla diferenciada por la columna {@code tipo_usuario}.
 * </p>
 * 
 * <p>
 * Se asegura que todos los campos esenciales estén validados para no ser nulos
 * ni vacíos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
public abstract class Usuario {

    /**
     * Identificador único del usuario.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_sequence", allocationSize = 1)
    private Long idUsuario;

    /**
     * Nombre del usuario.
     * 
     * <p>
     * Este campo es obligatorio y no puede estar en blanco.
     * </p>
     */
    @Column(nullable = false)
    @NotBlank
    private String nombre;

    /**
     * Contraseña del usuario.
     * 
     * <p>
     * Este campo es obligatorio y no puede estar en blanco.
     * </p>
     */
    @Column(nullable = false)
    @NotBlank
    private String contrasenia;

    /**
     * Dirección de correo electrónico del usuario.
     * 
     * <p>
     * Este campo es obligatorio y no puede estar en blanco.
     * </p>
     */
    @Column(nullable = false)
    @NotBlank
    private String email;
}
