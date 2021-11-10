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
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.entity.Comment;
import uz.pdp.vazifa2.payload.CategoryDto;
import uz.pdp.vazifa2.payload.CommentDto;
import uz.pdp.vazifa2.service.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Category> all = categoryService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Integer id) {
        Category category = categoryService.getOne(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        ApiResponse edit = categoryService.edit(id, categoryDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
