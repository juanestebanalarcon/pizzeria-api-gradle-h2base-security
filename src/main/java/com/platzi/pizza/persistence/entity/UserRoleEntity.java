package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user_role")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(nullable=false,length = 20)
    private String username;

    @Id
    @Column(nullable=false,length = 20)
    private String role;

    @Column(nullable=false,name="granted_date",columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(nullable=false,insertable = false,name="username",referencedColumnName = "username")
    private UserEntity user;


}
