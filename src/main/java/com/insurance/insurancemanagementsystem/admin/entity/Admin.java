package com.insurance.insurancemanagementsystem.admin.entity;



import com.insurance.insurancemanagementsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @OneToOne
    @JoinColumn( name = "user_id", nullable = false)
    private User user;

    private String firstName;

    private String Gender;

    private String email;

    private String phone;

    private String address;

    private LocalDate doj;
}
