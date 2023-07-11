package com.ayushi.Task1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String empName;

    @PastOrPresent(message = "Joining date should be today or older")
    @NotNull(message = "Joining Date is required")
    private LocalDate empJoiningDate;

    @ManyToOne
    @JoinColumn(name = "comp_id")
    @JsonIgnore
    @NotNull(message = "Company is required")
    private Company company;

    @Lob
    @Column(columnDefinition = "mediumblob")
    @JsonProperty("image")
    private byte[] empImage;

}