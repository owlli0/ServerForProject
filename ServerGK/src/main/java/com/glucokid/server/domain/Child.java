package com.glucokid.server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Child")
public class Child {
    @Id
    @Column(name = "ID_child")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //Убратьрекурсию↑↑↑↑↑↑↑
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<ParentChild> parents;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<SugarLevel> sugarLevels;
}
