package alex.nklv.blog_backend.config;

import alex.nklv.blog_backend.entity.Comment;
import alex.nklv.blog_backend.entity.Post;
import alex.nklv.blog_backend.entity.User;
import alex.nklv.blog_backend.repository.CommentRepository;
import alex.nklv.blog_backend.repository.PostRepository;
import alex.nklv.blog_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(UserRepository userRepository,
                               PostRepository postRepository,
                               CommentRepository commentRepository) {
        return args -> {

            if (userRepository.count() > 0 || postRepository.count() > 0 || commentRepository.count() > 0) {
                return;
            }

            // USERS
            User user1 = new User();
            user1.setName("Ivan Petrov");
            user1.setEmail("ivan@example.com");
            user1.setPassword("1234");

            User user2 = new User();
            user2.setName("Maria Georgieva");
            user2.setEmail("maria@example.com");
            user2.setPassword("2345");

            User user3 = new User();
            user3.setName("Nikolay Dimitrov");
            user3.setEmail("nikolay@example.com");
            user3.setPassword("3456");

            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);
            user3 = userRepository.save(user3);

            // POSTS
            Post post1 = new Post();
            post1.setTitle("Getting Started with Spring Boot");
            post1.setContent("Spring Boot makes it easy to create stand-alone, production-grade Spring applications with minimal configuration.");
            post1.setCreatedAt(LocalDateTime.now().minusDays(10));
            post1.setUser(user1);

            Post post2 = new Post();
            post2.setTitle("Why Angular is Great for Frontend Projects");
            post2.setContent("Angular provides a powerful structure for building modern single-page applications with routing, services, and reusable components.");
            post2.setCreatedAt(LocalDateTime.now().minusDays(8));
            post2.setUser(user2);

            Post post3 = new Post();
            post3.setTitle("Top 5 Java Tips for Beginners");
            post3.setContent("Learn the basics of Java syntax, object-oriented programming, collections, and debugging to improve your development skills.");
            post3.setCreatedAt(LocalDateTime.now().minusDays(6));
            post3.setUser(user1);

            Post post4 = new Post();
            post4.setTitle("REST APIs Explained Simply");
            post4.setContent("REST APIs allow communication between frontend and backend through standard HTTP methods such as GET, POST, PUT, and DELETE.");
            post4.setCreatedAt(LocalDateTime.now().minusDays(4));
            post4.setUser(user3);

            Post post5 = new Post();
            post5.setTitle("Frontend Project Ideas for Students");
            post5.setContent("Blog systems, task managers, and small e-commerce apps are among the easiest and most practical frontend course project ideas.");
            post5.setCreatedAt(LocalDateTime.now().minusDays(2));
            post5.setUser(user2);

            post1 = postRepository.save(post1);
            post2 = postRepository.save(post2);
            post3 = postRepository.save(post3);
            post4 = postRepository.save(post4);
            post5 = postRepository.save(post5);

            // COMMENTS
            Comment comment1 = new Comment();
            comment1.setContent("Very helpful introduction!");
            comment1.setCreatedAt(LocalDateTime.now().minusDays(9));
            comment1.setUser(user2);
            comment1.setPost(post1);

            Comment comment2 = new Comment();
            comment2.setContent("Spring Boot really saves a lot of setup time.");
            comment2.setCreatedAt(LocalDateTime.now().minusDays(9).plusHours(2));
            comment2.setUser(user3);
            comment2.setPost(post1);

            Comment comment3 = new Comment();
            comment3.setContent("Angular routing was the hardest part for me at first.");
            comment3.setCreatedAt(LocalDateTime.now().minusDays(7));
            comment3.setUser(user1);
            comment3.setPost(post2);

            Comment comment4 = new Comment();
            comment4.setContent("This gives me ideas for my semester project.");
            comment4.setCreatedAt(LocalDateTime.now().minusDays(7).plusHours(3));
            comment4.setUser(user3);
            comment4.setPost(post2);

            Comment comment5 = new Comment();
            comment5.setContent("Collections in Java were confusing until I practiced more.");
            comment5.setCreatedAt(LocalDateTime.now().minusDays(5));
            comment5.setUser(user2);
            comment5.setPost(post3);

            Comment comment6 = new Comment();
            comment6.setContent("Good explanation of REST principles.");
            comment6.setCreatedAt(LocalDateTime.now().minusDays(3));
            comment6.setUser(user1);
            comment6.setPost(post4);

            Comment comment7 = new Comment();
            comment7.setContent("I think blog systems are definitely easier than e-shops.");
            comment7.setCreatedAt(LocalDateTime.now().minusDays(1));
            comment7.setUser(user3);
            comment7.setPost(post5);

            Comment comment8 = new Comment();
            comment8.setContent("Task manager is another solid student project idea.");
            comment8.setCreatedAt(LocalDateTime.now().minusHours(20));
            comment8.setUser(user1);
            comment8.setPost(post5);

            Comment comment9 = new Comment();
            comment9.setContent("Nice article, short and practical.");
            comment9.setCreatedAt(LocalDateTime.now().minusHours(12));
            comment9.setUser(user2);
            comment9.setPost(post4);

            Comment comment10 = new Comment();
            comment10.setContent("Would love to see a part two on Spring Data JPA.");
            comment10.setCreatedAt(LocalDateTime.now().minusHours(6));
            comment10.setUser(user3);
            comment10.setPost(post1);

            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            commentRepository.save(comment4);
            commentRepository.save(comment5);
            commentRepository.save(comment6);
            commentRepository.save(comment7);
            commentRepository.save(comment8);
            commentRepository.save(comment9);
            commentRepository.save(comment10);

            System.out.println("Demo blog data seeded successfully.");
        };
    }
}