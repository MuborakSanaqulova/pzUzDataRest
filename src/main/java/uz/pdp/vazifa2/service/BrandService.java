package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Brand;
import uz.pdp.vazifa2.payload.BrandDto;
import uz.pdp.vazifa2.repository.BrandRepository;

import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Page<Brand> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    public Brand getOne(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent())
            return brand.get();
        return null;
    }

    public ApiResponse add(BrandDto brandDto) {

        boolean existsByName = brandRepository.existsByName(brandDto.getName());
        if (existsByName)
            return new ApiResponse("already exist", false);

        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, BrandDto brandDto) {

        boolean byNameAndIdNot = brandRepository.existsByNameAndIdNot(brandDto.getName(), id);
        if (byNameAndIdNot)
            return new ApiResponse("already exist ", false);

        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(brandDto.getName());
        brandRepository.save(brand);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent())
            return new ApiResponse("not found", false);

        brandRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
