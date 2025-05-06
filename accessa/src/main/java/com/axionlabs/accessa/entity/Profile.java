package com.axionlabs.accessa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "_profiles")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Profile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID profileId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usedId",nullable = false, unique = true)
    private User user;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Lob
    @Column(name = "about",columnDefinition = "TEXT")
    private String about;

}
