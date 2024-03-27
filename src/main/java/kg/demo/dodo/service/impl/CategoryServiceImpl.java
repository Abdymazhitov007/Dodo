package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.CategoryMapper;
import kg.demo.dodo.model.dto.CategoryDTO;
import kg.demo.dodo.model.entity.Category;
import kg.demo.dodo.repository.CategoryRep;
import kg.demo.dodo.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRep, CategoryDTO, CategoryMapper> implements CategoryService {
    public CategoryServiceImpl(CategoryRep rep, CategoryMapper mapper) {
        super(rep, mapper);
    }
}
