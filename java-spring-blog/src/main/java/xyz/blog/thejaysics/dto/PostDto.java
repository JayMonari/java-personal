package xyz.blog.thejaysics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDto {
	private Long id;
	private String content;
	private String title;
	private String username;
}
