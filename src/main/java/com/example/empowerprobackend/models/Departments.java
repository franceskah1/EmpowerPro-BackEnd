package com.example.empowerprobackend.models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departments extends BaseEntity {
    private String departmentName;

    @OneToMany(mappedBy = "departments",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();


}
