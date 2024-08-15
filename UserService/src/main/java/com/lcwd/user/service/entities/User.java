package com.lcwd.user.service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "Name" , length = 20)
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "MobileNo")
    private long mobileNo;
    @Column(name = "About")
    private String about;
    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
