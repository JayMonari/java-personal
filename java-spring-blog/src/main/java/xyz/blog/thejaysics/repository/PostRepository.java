package xyz.blog.thejaysics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.blog.thejaysics.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
