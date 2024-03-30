package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.CategoryMapper;
import kg.demo.dodo.model.dto.CategoryDTO;
import kg.demo.dodo.model.entity.Category;
import kg.demo.dodo.model.requests.CategoryCreateRequest;
import kg.demo.dodo.model.response.CategoryListResponse;
import kg.demo.dodo.repository.CategoryRep;
import kg.demo.dodo.service.CategoryService;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRep, CategoryDTO, CategoryMapper> implements CategoryService {
    public CategoryServiceImpl(CategoryRep rep, CategoryMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(CategoryCreateRequest request, int lang) {

        CategoryDTO dto = new CategoryDTO();
        dto.setName(request.getName());
        dto.setDefinition(request.getDefinition());

        save(dto);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");
    }

    @Override
    public List<CategoryListResponse> getCategoryList() {
        return rep.findCategoryList();
    }
}
