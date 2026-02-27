package com.nicode.challenge_api_foro_hub.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "topicos")
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    @Column(name = "status")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    public TopicoEntity(String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean estado, UsuarioEntity usuario, CursoEntity curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.usuario = usuario;
        this.curso = curso;
    }

}
