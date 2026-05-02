package com.glucokid.server.domain;

import jakarta.persistence.*;

public class ParentChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_parent", nullable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "id_child", nullable = false)
    private Child child;


}
