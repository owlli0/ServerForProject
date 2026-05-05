package com.glucokid.server.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sugar_lvl")
public class SugarLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note_sl")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_child")
    private Child child;

    @Column(name = "value")
    private double value;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "extra")
    private String extra;
}