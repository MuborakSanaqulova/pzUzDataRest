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
import uz.pdp.vazifa2.entity.Brand;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.payload.BrandDto;
import uz.pdp.vazifa2.payload.CategoryDto;
import uz.pdp.vazifa2.service.BrandService;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<Brand>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Brand> all = brandService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getOne(@PathVariable Integer id) {
        Brand brand = brandService.getOne(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = brandService.add(brandDto);

        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody BrandDto brandDto) {
        ApiResponse edit = brandService.edit(id, brandDto);
        if (edit.isSuccess())
            return ResponseEntity.status(200).body(edit);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = brandService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
