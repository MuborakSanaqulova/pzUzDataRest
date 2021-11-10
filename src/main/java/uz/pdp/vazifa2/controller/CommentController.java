package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Comment;
import uz.pdp.vazifa2.payload.CommentDto;
import uz.pdp.vazifa2.service.CommentService;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Comment>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Comment> all = commentService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getOne(@PathVariable Integer id) {
        Comment comment = commentService.getOne(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        ApiResponse edit = commentService.edit(id, commentDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id) {
        ApiResponse apiResponse = commentService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
