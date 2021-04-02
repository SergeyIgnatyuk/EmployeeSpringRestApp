package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_formation")
    private Date dateOfFormation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Set<Employee> employees = new HashSet<Employee>();

    @PreRemove
    private void deleteDepartmentFromEmployee() {
        for (Employee employee : employees) {
            employee.setDepartmentId(null);
        }
    }
}
