package com.tekseries.server.core.admin.dashboardUser.user.controller;

import com.tekseries.server.core.admin.dashboardUser.user.service.AdminPostService;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import com.tekseries.server.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MappingConstants.API_VERSION_PREFIX + MappingConstants.ADMIN + "/posts")
public class AdminPostController {

    @Autowired
    private AdminPostService adminPostService;

    // delete hẳn
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ResponseObject<?>> deletePost(@PathVariable String postId) {
        boolean deleted = adminPostService.deletePost(postId);
        if (!deleted) {
            return ResponseEntity.status(404)
                    .body(new ResponseObject<>(null, "Không tìm thấy bài viết"));
        }
        return ResponseEntity.ok(new ResponseObject<>(null, "Xóa bài viết thành công"));
    }

    // Soft delete
    @PutMapping("/soft-delete/{postId}")
    public ResponseEntity<ResponseObject<?>> softDeletePost(@PathVariable String postId) {
        boolean deleted = adminPostService.softDeletePost(postId);
        if (!deleted) {
            return ResponseEntity.status(404)
                    .body(new ResponseObject<>(null, "Không tìm thấy bài viết"));
        }
        return ResponseEntity.ok(new ResponseObject<>(null, "Bài viết đã được đánh dấu xóa"));
    }
}
