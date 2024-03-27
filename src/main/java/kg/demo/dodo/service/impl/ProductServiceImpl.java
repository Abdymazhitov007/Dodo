package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.ProductMapper;
import kg.demo.dodo.model.dto.ProductDTO;
import kg.demo.dodo.model.entity.Product;
import kg.demo.dodo.repository.ProductRep;
import kg.demo.dodo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRep, ProductDTO, ProductMapper> implements ProductService {
    public ProductServiceImpl(ProductRep rep, ProductMapper mapper) {
        super(rep, mapper);
    }
}
