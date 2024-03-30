package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.ProductSizeMapper;
import kg.demo.dodo.microservices.FileService;
import kg.demo.dodo.model.dto.ProductDTO;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.entity.ProductSize;
import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.response.ProductSizeListResponse;
import kg.demo.dodo.repository.ProductSizeRep;
import kg.demo.dodo.service.CategoryService;
import kg.demo.dodo.service.ProductService;
import kg.demo.dodo.service.ProductSizeService;
import kg.demo.dodo.service.SizeService;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeServiceImpl extends BaseServiceImpl<ProductSize, ProductSizeRep, ProductSizeDTO, ProductSizeMapper> implements ProductSizeService {

    private final FileService fileService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ProductService productService;

    public ProductSizeServiceImpl(ProductSizeRep rep, ProductSizeMapper mapper, FileService fileService, CategoryService categoryService, SizeService sizeService, ProductService productService) {
        super(rep, mapper);
        this.fileService = fileService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.productService = productService;
    }

    @Override
    public List<ProductSizeListResponse> getProductSizeList() {
        return rep.findProductSizeList();
    }

    @Override
    public String create(ProductCreateRequest request, int lang) {

        ProductDTO product = new ProductDTO();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setLogo(fileService.upload(request.getLogo()).getDownloadUri());
        product.setCategory(categoryService.findById(request.getCategoryId()));

        ProductSizeDTO productSize = new ProductSizeDTO();

        productSize.setProduct(productService.save(product));
        productSize.setSize(sizeService.findById(request.getSizeId()));
        productSize.setPrice(request.getPrice());

        save(productSize);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");

    }
}
