package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.CategoryDTO;
import kg.demo.dodo.model.requests.CategoryCreateRequest;
import kg.demo.dodo.model.response.CategoryListResponse;

import java.util.List;

public interface CategoryService extends BaseService<CategoryDTO> {
    String create(CategoryCreateRequest request, int lang);

    List<CategoryListResponse> getCategoryList();
}
