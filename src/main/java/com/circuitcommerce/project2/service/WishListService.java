// package com.circuitcommerce.project2.service;

// import com.circuitcommerce.project2.model.User;
// import com.circuitcommerce.project2.model.WishList;
// import com.circuitcommerce.project2.repository.ProductRepository;

// import java.util.List;

// import com.circuitcommerce.project2.exception.ProductNotFoundException;
// import com.circuitcommerce.project2.model.Product;
// import com.circuitcommerce.project2.repository.UserRepository;
// import com.circuitcommerce.project2.repository.WishListRepository;

// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class WishListService {
//   private final WishListRepository wishListRepository;
//   private final UserRepository userRepository;
//   private final ProductRepository productRepository;

//   public List<Product> getWishListProducts(String username) {
//     User user = userRepository.findByUsername(username)
//         .orElseThrow(() -> new UsernameNotFoundException("Could not find username: " + username));
//     return user.getWishList().getDesiredProducts();
//   }

//   public void addProduct(String username, Long productId) {
//     User user = userRepository.findByUsername(username)
//         .orElseThrow(() -> new UsernameNotFoundException(
//               "User not found adding Product to WishList"));

//     Product product = productRepository.findById(productId)
//         .orElseThrow(() -> new ProductNotFoundException(
//       "Product with id: " + productId + "not found, in WishListService"
//     ));

//     List<WishList> wishLists = product.getWishLists();
//     WishList wishList = user.getWishList();
//     wishLists.add(wishList);
//     productRepository.save(product);
//   }

//   public void removeProduct(String username, Long productId) {
//     User user = userRepository.findByUsername(username)
//         .orElseThrow(() -> new UsernameNotFoundException(
//               "User not found adding Product to WishList"));

//     Product product = productRepository.findById(productId)
//         .orElseThrow(() -> new ProductNotFoundException(
//       "Product with id: " + productId + "not found, in WishListService"
//     ));

//     List<WishList> wishLists = product.getWishLists();
//     WishList wishList = user.getWishList();
//     wishLists.remove(wishList);
//     productRepository.save(product);
//   }

//   public void clearWishList(String username) {
//     User user = userRepository.findByUsername(username)
//         .orElseThrow(() -> new UsernameNotFoundException(
//               "User not found adding Product to WishList"));
//     List<Product> pList = getWishListProducts(username);
//     for(Product i: pList){
//       List<WishList> wishLists = i.getWishLists();
//       WishList wishlist = user.getWishList();
//       wishLists.remove(wishlist);
//       productRepository.save(i);
//     }
//   }

// }
