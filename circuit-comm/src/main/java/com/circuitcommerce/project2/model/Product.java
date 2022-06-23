package com.circuitcommerce.project2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long productId;

  @Column(unique = true, nullable = false)
  private String modelNumber;

  @Column(unique = true, nullable = false)
  private String title;

  @Column(unique = true, nullable = false, length = 30)
  private String abbreviatedTitle;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private Double price;

  @OneToMany(mappedBy = "reviewedProduct", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Review> reviewList;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "cartProducts")
  @JsonIgnore
  private List<Cart> cartList;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "productList")
  @JsonIgnore
  private List<UserOrder> orderedBy;

  // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @JoinColumn(name = "desiredProducts")
  // private List<WishList> wishLists;

  @Override
  public String toString() {
    return "Product [abbreviatedTitle=" + abbreviatedTitle + ", brand=" + brand + ", modelNumber=" + modelNumber
        + ", price=" + price + ", productId=" + productId + ", title=" + title + "]";
  }
}
