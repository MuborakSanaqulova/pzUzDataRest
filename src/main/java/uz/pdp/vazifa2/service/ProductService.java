package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Brand;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.entity.Product;
import uz.pdp.vazifa2.payload.ProductDto;
import uz.pdp.vazifa2.repository.BrandRepository;
import uz.pdp.vazifa2.repository.CategoryRepository;
import uz.pdp.vazifa2.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getOne(Integer id) {
        Optional<Product> brand = productRepository.findById(id);
        if (brand.isPresent())
            return brand.get();
        return null;
    }

    public ApiResponse add(ProductDto productDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        if (!optionalBrand.isPresent())
            return new ApiResponse("brand not found", false);

        boolean existsByName = productRepository.existsByNameAndCategoryIdAndBrandId(productDto.getName(), productDto.getCategoryId(), productDto.getBrandId());
        if (existsByName)
            return new ApiResponse("already exist", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(optionalCategory.get());
        product.setBrand(optionalBrand.get());

        productRepository.save(product);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, ProductDto productDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        if (!optionalBrand.isPresent())
            return new ApiResponse("brand not found", false);

        boolean existsByName = productRepository.existsByNameAndCategoryIdAndBrandIdAndIdNot(productDto.getName(),
                productDto.getCategoryId(), productDto.getBrandId(), id);
        if (existsByName)
            return new ApiResponse("already exist", false);

        Product product = new Product();
        product.setId(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(optionalCategory.get());
        product.setBrand(optionalBrand.get());

        productRepository.save(product);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("not found", false);

        brandRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
