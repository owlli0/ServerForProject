package com.glucokid.server.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parent_child")
public class ParentChild {

    @Id
    @Column(name = "id_note_pc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_parent", nullable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "id_child", nullable = false)
    private Child child;


}
