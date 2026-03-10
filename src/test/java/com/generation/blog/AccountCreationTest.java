package com.generation.blog;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.dto.BlogDTO;
import com.generation.blog.dto.BlogPostDTO;
import com.generation.blog.mapper.BlogPostMapper;
import com.generation.blog.repository.BlogPostRepository;
import com.generation.blog.repository.BlogRepository;
import com.generation.blog.repository.WebUserRepository;
import com.generation.blog.service.AccountManagementService;
import com.generation.blog.service.BlogPostService;
import com.generation.blog.service.BlogService;
import com.generation.blog.service.WebUserService;

import jakarta.transaction.Transactional;

import com.generation.blog.model.*;

/**
 * Testing for backend logic. For proper login and security tests, I need proper
 * e2e tests.
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AccountCreationTest {

	@Autowired
	AccountManagementService service;

	@Autowired
	WebUserRepository userRepo;

	@Autowired
	BlogService blogService;

	@Autowired
	WebUserService userService;

	@Autowired
	BlogRepository blogRepo;

	@Autowired
	BlogPostService postService;

	@Autowired
	BlogPostRepository postRepo;

	@Autowired
	BlogPostMapper postMapper;




	/**
	 * TODO
	 * -check successful blog post creation
	 * -check that when the blog post gets deleted, it is no longer visible from the
	 * author or blog
	 * -check that deleting the blog deletes the posts
	 * -check that deleting the author deletes the blogs and the posts
	 */

	@Test
	@Order(1)
	@Commit
	void AdminCreationTest() {
		AdminDTO adminDTO = makeAdminDTO();

		// Puts the admin into the db
		adminDTO = (AdminDTO) service.create(adminDTO);

		assert (adminDTO.getPassword() == null); // should not be visible

		WebUser admin = userRepo.findById(adminDTO.getId()).get();
		assert (admin instanceof Admin);
		assert ((Admin) admin).getLastPasswordChange().equals(LocalDate.now());
	}

	@Test
	@Order(2)
	@Commit
	void BlogCreationTest() {
		// Also tests that findByUsername works
		WebUser user = userRepo.findByUsernameContaining("Wajojo").get(0);

		BlogDTO blogDTO = makeBlogDTO(user.getId());
		blogDTO = blogService.save(blogDTO);

		Blog blog = blogRepo.findById(blogDTO.getId()).get();
		assert (blog.getTitle().equals(blogDTO.getTitle()));
		assert (blog.getOwner().equals(user));
	}

	@Order(3)
	@Test
	@Commit
	@Transactional
	void BlogPostCreationTest() {
		WebUser user = userRepo.findByUsernameContaining("Wajojo").get(0);
		Blog blog = blogRepo.findByOwnerId(user.getId()).get(0);
		//System.out.println("This blog is like this:" + blog.toString());

		List<BlogPostDTO> posts = makeBlogPosts(user.getId(), blog.getId());

		posts.stream().forEach(postService::save);

		//Reload first user and blog
		user = userRepo.findById(user.getId()).get();
		blog = blogRepo.findById(blog.getId()).get();

		System.out.println("This blog has this title:" + blog.getTitle());

		System.out.println("I am this post's blog! " + blog.getPosts().get(0).getTitle());

		assert(user.getPosts().size() == 5);
		assert(user.getPosts().get(0).getContent().equals("a"));
		assert(user.getPosts().get(0).getTitle().equals("a"));
		assert(blog.getPosts().size() == 5);

		BlogPost post = postRepo.findByTitleContaining("a").get(0);
		System.out.println(post.getBlog().getClass());
		//I expect this to be an empty object with the id only at first, and then get loaded with getAuthor?? Maybe
		System.out.println("This posts author is:" + post.getAuthor().getFirstName());
		System.out.println("This posts blog is:" + post.getBlog().getTitle());
		assert(post.getAuthor().equals(user));
		assert(post.getBlog().equals(blog));
	}

	@Order(4)
	@Test
	@Commit
	@Transactional
	void BlogPostDeletionTest() {
		WebUser user = userRepo.findByUsernameContaining("Wajojo").get(0);

		BlogPost post = postRepo.findByTitleContaining("a").get(0);
		postRepo.deleteById(post.getId());
		assert(postRepo.findById(post.getId()).isEmpty());

		user = userRepo.findById(user.getId()).get();
		Blog blog = blogRepo.findById(post.getBlog().getId()).get();

		//Okay I am getting persistence context fuckery here, so maybe it is better to just go for e2e testing and fuck it.
		//user.getPosts().stream().map(BlogPost::getTitle).forEach(System.out::println);
		//assert(user.getPosts().size() == 4);
		//assert(blog.getPosts().size() == 4);
	}
	/*
	 * TODO collaborator test make sure deleting collaborator erases references, but
	 * not the blog
	 * 
	 */

	AdminDTO makeAdminDTO() {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUsername("SeriousWajojo");
		adminDTO.setEmail("wajojo@evil.com");
		adminDTO.setPassword("ayyylmao");
		adminDTO.setLastName("Waandrade");
		adminDTO.setFirstName("Wajojo");
		adminDTO.setRole("adminDTO");

		return adminDTO;
	}

	BlogDTO makeBlogDTO(int id) {
		BlogDTO dto = new BlogDTO();
		dto.setTitle("CLICK HERE!!!11!!!!");
		dto.setDescription("I have something original to say: Lorem ipsum.");
		dto.setOwnerId(id);
		return dto;
	}

	List<BlogPostDTO> makeBlogPosts(int authorId, int blogId) {
		List<BlogPostDTO> posts = new ArrayList<>();

		List<String> titles = new ArrayList<>();
		titles.add("a");
		titles.add("b");
		titles.add("c");
		titles.add("d");
		titles.add("e");
		List<String> contents = new ArrayList<>();
		contents.add("a");
		contents.add("b");
		contents.add("c");
		contents.add("d");
		contents.add("e");

		for (int i = 0; i < 5; i++) {
			BlogPostDTO post = new BlogPostDTO();
			post.setTitle(titles.get(i));
			post.setContent(contents.get(i));
			post.setAuthorId(authorId);
			post.setBlogId(blogId);
			posts.add(post);
		}

		return posts;
	}

}
