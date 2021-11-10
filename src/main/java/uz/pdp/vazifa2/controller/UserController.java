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
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.UserDto;
import uz.pdp.vazifa2.service.UserService;

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> all = userService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.add(userDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        ApiResponse edit = userService.edit(id, userDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
