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
import uz.pdp.vazifa2.entity.Product;
import uz.pdp.vazifa2.payload.ProductDto;
import uz.pdp.vazifa2.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> all = productService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Integer id) {
        Product product = productService.getOne(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.add(productDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse edit = productService.edit(id, productDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
}
