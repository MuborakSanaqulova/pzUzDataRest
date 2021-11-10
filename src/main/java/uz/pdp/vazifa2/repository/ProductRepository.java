package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByNameAndCategoryIdAndBrandId(String name, Integer category_id, Integer brand_id);

    boolean existsByNameAndCategoryIdAndBrandIdAndIdNot(String name, Integer category_id, Integer brand_id, Integer id);

}
