//package com.tekseries.server.core.user.ranking.testRanking;
//
//import com.tekseries.server.core.admin.dashboardUser.user.repository.AdminPostRepository;
//import com.tekseries.server.core.user.ranking.service.ContributionService;
//import com.tekseries.server.entity.Post;
//import com.tekseries.server.entity.User;
//import com.tekseries.server.infrastructure.constant.EntityStatus;
//import com.tekseries.server.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/posts")
//public class PostController {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private ContributionService contributionService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/create")
//    public Post createPost(@RequestParam String userId, @RequestParam String content) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Post post = new Post();
//        post.setId(UUID.randomUUID().toString());
//        post.setUser(user);
//        post.setContent(content);
//        post.setStatus(EntityStatus.ACTIVE);
//        postRepository.save(post);
//
//        // Publish event
//        contributionService.addContribution(userId, "CREATE_POST", post.getId());
//
//        return post;
//    }
//}
//
