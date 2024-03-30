package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.Category;
import kg.demo.dodo.model.response.CategoryListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRep extends BaseRep<Category> {
    @Query(value = "select id, name, definition from tb_category where status = 'ACTIVE'", nativeQuery = true)
    List<CategoryListResponse> findCategoryList();

}
