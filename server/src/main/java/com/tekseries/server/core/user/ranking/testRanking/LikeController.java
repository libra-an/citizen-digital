//package com.tekseries.server.core.user.ranking.testRanking;
//
//import com.tekseries.server.core.user.ranking.service.ContributionService;
//import com.tekseries.server.entity.Like;
//import com.tekseries.server.entity.Post;
//import com.tekseries.server.entity.User;
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
//@RequestMapping("/likes")
//public class LikeController {
//
//    @Autowired
//    private LikeRepository likeRepository;
//
//    @Autowired
//    private ContributionService contributionService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @PostMapping("/create")
//    public Like createLike(@RequestParam String userId, @RequestParam String postId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        Like like = new Like();
//        like.setId(UUID.randomUUID().toString());
//        like.setUser(user);
//        like.setPost(post);
//        likeRepository.save(like);
//
//
//        contributionService.addContribution(userId, "LIKE", postId);
//
//        return like;
//    }
//}
//
//
