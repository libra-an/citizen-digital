//package com.tekseries.server.core.user.ranking.testRanking;
//
//import com.tekseries.server.core.user.ranking.service.ContributionService;
//import com.tekseries.server.entity.Comment;
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
//@RequestMapping("/comments")
//public class CommentController {
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ContributionService contributionService;
//
//    @PostMapping("/create")
//    public CommentDTO createComment(@RequestParam String userId,
//                                    @RequestParam String postId,
//                                    @RequestParam String content) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        Comment comment = new Comment();
//        comment.setId(UUID.randomUUID().toString());
//        comment.setUser(user);
//        comment.setPost(post);
//        comment.setContent(content);
//        commentRepository.save(comment);
//
//        contributionService.addContribution(userId, "COMMENT", postId);
//
//        return new CommentDTO(comment);
//    }
//}
