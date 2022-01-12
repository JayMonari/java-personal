// package com.circuitcommerce.project2.controller;

// import java.util.List;

// import com.circuitcommerce.project2.model.Product;
// import com.circuitcommerce.project2.service.WishListService;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.AllArgsConstructor;

// @RestController
// @RequestMapping(value = "/api/user/wishlist")
// @AllArgsConstructor
// public class WishListController {
//   private final WishListService wishListService;

//   @GetMapping
//   public List<Product> getWishListByUsername(@RequestHeader("username") String username) {
//     return wishListService.getWishListProducts(username);
//   }  

//   @GetMapping("/add")
//   public ResponseEntity<String> addProductToWishList(@RequestParam("pid") Long productId,
//       @RequestHeader("username") String username) {
//     wishListService.addProduct(username, productId);
//     return new ResponseEntity<>("Product added to WishList", HttpStatus.OK);
//   }

//   @DeleteMapping
//   public ResponseEntity<Void> removeProductToWishList(@RequestParam("pid") Long productId,
//       @RequestHeader("username") String username) {
//     wishListService.removeProduct(username, productId);
//     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//   }

//   @DeleteMapping("/clear")
//   public ResponseEntity<Void> clearWishList(@RequestHeader("username") String username){
//     wishListService.clearWishList(username);
//     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//   }
// }
