// package com.circuitcommerce.project2.model;

// import java.util.List;

// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToOne;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class WishList {
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long wishListId;

//   @OneToOne
//   private User user;

//   @ManyToMany(mappedBy = "wishLists", fetch = FetchType.LAZY)
//   private List<Product> desiredProducts;
// }
