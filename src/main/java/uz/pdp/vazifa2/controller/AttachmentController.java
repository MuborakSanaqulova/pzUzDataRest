package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Attachment;
import uz.pdp.vazifa2.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public ResponseEntity<Page<Attachment>> getInfo(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Attachment> all = attachmentService.getAttachments(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        ApiResponse one = attachmentService.findOne(id, response);
        return ResponseEntity.ok(one);
    }

    @PostMapping
    public HttpEntity<ApiResponse> uploadFile(MultipartHttpServletRequest request) throws IOException {
        ApiResponse post = attachmentService.post(request);
        if (post.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(post);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse delete = attachmentService.delete(id);
        if (delete.isSuccess())
            return ResponseEntity.status(200).body(delete);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(delete);
    }

}
