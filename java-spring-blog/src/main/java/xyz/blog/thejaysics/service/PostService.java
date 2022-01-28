package xyz.blog.thejaysics.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import xyz.blog.thejaysics.dto.PostDto;
import xyz.blog.thejaysics.exception.PostNotFoundException;
import xyz.blog.thejaysics.model.Post;
import xyz.blog.thejaysics.repository.PostRepository;

@Service
@AllArgsConstructor
public class PostService {
	private AuthService authService;
	private PostRepository postRepository;

	@Transactional
	public List<PostDto> showAllPosts() {
		return postRepository.findAll().stream()
			.map(this::mapFromPostToDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public void createPost(PostDto postDto) {
		postRepository.save(mapFromDtoToPost(postDto));
	}

	@Transactional
	public PostDto readSinglePost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new PostNotFoundException("For id " + id));
		return mapFromPostToDto(post);
	}

	private PostDto mapFromPostToDto(Post post) {
		return PostDto.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.username(post.getUsername())
			.build();
	}

	private Post mapFromDtoToPost(PostDto dto) {
		Post post = Post.builder()
			.title(dto.getTitle())
			.content(dto.getContent())
			.createdOn(Instant.now())
			.updatedOn(Instant.now())
			.build();
		org.springframework.security.core.userdetails.User loggedIn =
				authService.getCurrentUser().orElseThrow(() ->
					new IllegalArgumentException("User Not Found"));
		post.setUsername(loggedIn.getUsername());
		return post;
	}
}
