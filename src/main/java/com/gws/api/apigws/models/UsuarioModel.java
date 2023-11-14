package com.gws.api.apigws.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.serializer.Serializer;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_usuario")
 public class UsuarioModel implements Serializable {

   @Serial
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario", nullable = false)
    private UUID id_usuario;
    private String nome;
    private String tipo_usuario;
    private String sobrenome;
    private String telefone;
    private String email;
    @JsonIgnore
    private String senha;
    private LocalDateTime dataCadastro;
    private LocalDate data_ferias;
    private String designacao;
    private String cidade;
    private String estado;
    private LocalTime horas_semanais;
    private String descricao;
    private String url_img;


    @ManyToMany
    @JoinTable(
        name = "tb_softskills_usuarios",
        joinColumns = @JoinColumn(name = "id_usuario"),
       inverseJoinColumns = @JoinColumn(name = "id_softskill")
    )
    private Set<SoftSkillsModel> foreign_softskill = new HashSet<>();

    @ManyToMany
    @JoinTable(
         name = "tb_hardskills_usuarios",
         joinColumns = @JoinColumn(name = "id_usuario"),
         inverseJoinColumns = @JoinColumn(name = "id_hardskill")
    )
    private Set<HardSkillsModel> foreign_hardskill;

    @ManyToMany(mappedBy = "foreign_demanda")
    private Set<DemandasModel> foreing_demanda;

}
