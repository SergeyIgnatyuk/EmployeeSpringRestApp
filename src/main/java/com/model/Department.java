package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Department.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "departments")
@ApiModel(value="Department", description="Department of company")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated department ID")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The department name")
    private String name;

    @Column(name = "description")
    @ApiModelProperty(notes = "The department description")
    private String description;

    @Column(name = "phone_number")
    @ApiModelProperty(notes = "The department phone number")
    private String phoneNumber;

    @Column(name = "date_of_formation")
    @ApiModelProperty(notes = "The department date of formation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfFormation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ApiModelProperty(notes = "Employees which belong to this department")
    private Set<Employee> employees = new HashSet<Employee>();

    @PreRemove
    private void deleteDepartmentFromEmployee() {
        for (Employee employee : employees) {
            employee.setDepartmentId(null);
        }
    }
}
