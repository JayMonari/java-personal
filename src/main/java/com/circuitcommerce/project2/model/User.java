package com.circuitcommerce.project2.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @NotBlank(message = "Username is required")
  @Column(nullable = false, unique = true, updatable = false)
  private String username;
  @NotBlank(message = "Password is required")
  @Column(nullable = false)
  private String password;
  @Email
  @NotEmpty(message = "Email is required")
  @Column(unique = true, nullable = false)
  private String email;

  private String firstName;

  private String lastName;

  private Instant createdAt;
  private boolean isEnabled;
  private String address;
  @OneToOne
  @JsonIgnore
  private Cart cart;

  // @OneToOne
  // @JsonIgnore
  // private WishList wishList;

  @OneToMany(mappedBy = "reviewingUser", fetch = FetchType.LAZY)
  private List<Review> reviewList;

  @OneToMany(mappedBy = "orderOwner", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<UserOrder> orderList;

  @Override
  public String toString() {
    return "User [address=" + address + ", createdAt=" + createdAt + ", email=" + email + ", firstName=" + firstName
        + ", isEnabled=" + isEnabled + ", lastName=" + lastName + ", password=" + password + ", userId=" + userId
        + ", username=" + username + "]";
  }
}
