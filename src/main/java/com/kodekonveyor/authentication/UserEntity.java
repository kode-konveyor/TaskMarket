package com.kodekonveyor.authentication;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode
@ToString
@ExcludeFromCodeCoverage("no code")
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;// NOPMD
  @Column(name = "username")
  private String login;
  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {
          CascadeType.ALL
      }
  )
  @JoinTable(
      name = "users_roles",
      joinColumns = {
          @JoinColumn(name = "userid")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "role")
      }
  )
  @EqualsAndHashCode.Exclude
  private Set<RoleEntity> roles = new HashSet<>();

}
