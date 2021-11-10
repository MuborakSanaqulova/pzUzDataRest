package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Comment;
import uz.pdp.vazifa2.payload.CommentDto;
import uz.pdp.vazifa2.repository.CommentRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Page<Comment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment getOne(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent())
            return comment.get();
        return null;
    }

    public ApiResponse addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setText(commentDto.getText());

        commentRepository.save(comment);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setName(commentDto.getName());
        comment.setText(commentDto.getText());

        commentRepository.save(comment);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("not found", false);

        commentRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
