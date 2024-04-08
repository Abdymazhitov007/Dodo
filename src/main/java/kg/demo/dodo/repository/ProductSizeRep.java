package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.ProductSize;
import kg.demo.dodo.model.response.ProductListResponse;
import kg.demo.dodo.model.response.ProductSizeListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRep extends BaseRep<ProductSize> {

    @Query(value = "select id, name, description from tb_product where category_id = :categoryId and status = 'ACTIVE' order by created_date desc;", nativeQuery = true)
    List<ProductListResponse> findProductListByCategoryId(Long categoryId, Pageable pageable);

    List<ProductSize> findAllByProductId(Long productId);

    @Query(value = """
SELECT ps.id, p.name, p.logo, p.description, c.name as category, s.name as size, ps.price
FROM tb_product_size ps join public.tb_product p on ps.product_id = p.id join public.tb_category c on c.id = p.category_id join public.tb_size s on s.id = ps.size_id
WHERE (:sizeId IS NULL OR ps.size_id = :sizeId)
  AND (:fromPrice IS NULL OR ps.price >= :fromPrice)
  AND (:toPrice IS NULL OR ps.price <= :toPrice)
  AND (:name IS NULL OR p.name ILIKE '%' || :name || '%')
  AND (:categoryId IS NULL OR p.category_id = :categoryId);

""", nativeQuery = true)
    List<ProductSizeListResponse> filter(Long sizeId, Double fromPrice, Double toPrice, String name, Long categoryId);
}
