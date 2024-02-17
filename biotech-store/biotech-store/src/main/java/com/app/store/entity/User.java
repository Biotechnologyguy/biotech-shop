package com.app.store.entity;

import com.app.store.utils.Gender;
import com.app.store.utils.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity(name = "users")
public class User extends BaseEntity {


    @Column(name = "user_name", unique = true, length = 45)
    @NotBlank(message = "User name is required")
    private String userName;

    @Column(name = "email", unique = true, length = 45)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "password", length = 400)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(name = "display_picture", length = 100)
    private String displayPicture;

    @Enumerated(STRING)
    private Gender gender;

    @Past(message = "Date of birth should not be in future")
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "mobile_number", unique = true, length = 10)
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @Enumerated(STRING)
    @Column(name = "user_role")
    private UserRole userRole;

}
