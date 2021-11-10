package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.payload.CategoryDto;
import uz.pdp.vazifa2.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category getOne(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent())
            return category.get();
        return null;
    }

    public ApiResponse add(CategoryDto categoryDto) {

        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new ApiResponse("already exist", false);

        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {

        boolean byNameAndIdNot = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (byNameAndIdNot)
            return new ApiResponse("already exist ", false);

        Category category = new Category();
        category.setId(id);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("not found", false);

        categoryRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
