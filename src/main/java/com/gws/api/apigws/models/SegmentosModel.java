package com.gws.api.apigws.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_segmentos")
public class SegmentosModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_segmento", nullable = false)
    private UUID id_segmentos;

    private String segmento;

    @ManyToMany(mappedBy = "foreign_segmento")
    private Set<DemandasModel> foreing_demanda = new HashSet<>();

}
