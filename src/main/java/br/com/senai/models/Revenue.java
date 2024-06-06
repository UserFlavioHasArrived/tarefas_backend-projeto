package br.com.senai.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="revenue")
@EqualsAndHashCode(of="id")
public class Revenue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private String method_preparation;

    @Column(nullable = false)
    private String nutritional_information;
}
