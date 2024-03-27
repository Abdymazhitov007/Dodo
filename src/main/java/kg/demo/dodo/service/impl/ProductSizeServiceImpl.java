package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.ProductSizeMapper;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.entity.ProductSize;
import kg.demo.dodo.repository.ProductSizeRep;
import kg.demo.dodo.service.ProductSizeService;
import org.springframework.stereotype.Service;

@Service
public class ProductSizeServiceImpl extends BaseServiceImpl<ProductSize, ProductSizeRep, ProductSizeDTO, ProductSizeMapper> implements ProductSizeService {
    public ProductSizeServiceImpl(ProductSizeRep rep, ProductSizeMapper mapper) {
        super(rep, mapper);
    }
}
