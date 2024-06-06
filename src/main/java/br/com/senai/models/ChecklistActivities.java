package br.com.senai.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="Checklist_Activities")
@EqualsAndHashCode(of="id")
public class ChecklistActivities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name_activities;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date_hour ;
}
