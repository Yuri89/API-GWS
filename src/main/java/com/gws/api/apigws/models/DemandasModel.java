package com.gws.api.apigws.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_demandas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_demanda")
public class DemandasModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_demanda", nullable = false)
    private UUID id_demanda;


    @Column(nullable = false,unique = false)
    private String titulo;
    @Column(nullable = false,unique = false)
    private String logo;
    @Column(nullable = false,unique = false)
    private String descricao;
    @Column(nullable = false,unique = false)
    private String prioridade;
    @Column(nullable = false,unique = false)
    private boolean privacidade;
    @Column(nullable = false,unique = false)
    private String status;
    @Column(nullable = false,unique = false)
    private LocalDateTime data_inicio;
    @Column(nullable = false,unique = false)
    private LocalDate data_final;
    @Column(nullable = false,unique = false)
    private int custo;
    @Column(nullable = false,unique = false)
    private String anexo;


    @ManyToMany
    @JoinTable(
            name = "tb_usuarios_demandas",
            joinColumns = @JoinColumn(name = "id_demanda",unique = false),
            inverseJoinColumns = @JoinColumn(name = "id_usuario",unique = false)
<<<<<<< HEAD

    )
    @org.hibernate.annotations.Index(name = "idx_demanda_usuario")
=======
    )
    @org.hibernate.annotations.Index(name = "idx_usuarios_demandas")
>>>>>>> dfe9dae7ab494b91bba808694f33f229b5ac2fc6
    private Set<UsuarioModel> id_usuarios = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tb_segmentos_demandas",
            joinColumns = @JoinColumn(name = "id_demanda",unique = false),
            inverseJoinColumns = @JoinColumn(name = "id_segmento",unique = false)
<<<<<<< HEAD

    )
    @org.hibernate.annotations.Index(name = "idx_demanda_segmento")
=======
    )
    @org.hibernate.annotations.Index(name = "idx_segmentos_demandas")
>>>>>>> dfe9dae7ab494b91bba808694f33f229b5ac2fc6
    private Set<SegmentosModel> id_segmentos = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClientesModel id_cliente;

}
