package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.ProductSizeMapper;
import kg.demo.dodo.microservices.FileService;
import kg.demo.dodo.model.dto.ProductDTO;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.entity.ProductSize;
import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.requests.ProductSizeCreateRequest;
import kg.demo.dodo.model.response.ProductFullInfoResponse;
import kg.demo.dodo.model.response.ProductListResponse;
import kg.demo.dodo.model.response.ProductSizeInfoResponse;
import kg.demo.dodo.model.response.ProductSizeListResponse;
import kg.demo.dodo.repository.ProductSizeRep;
import kg.demo.dodo.service.CategoryService;
import kg.demo.dodo.service.ProductService;
import kg.demo.dodo.service.ProductSizeService;
import kg.demo.dodo.service.SizeService;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public ProductFullInfoResponse getFullInfoByProductId(Long productId, int lang) {

        ProductDTO productDTO = productService.findById(productId, lang);

        ProductFullInfoResponse response = new ProductFullInfoResponse();
        response.setName(productDTO.getName());
        response.setLogo(productDTO.getLogo());
        response.setDescription(productDTO.getDescription());
        response.setCategory(productDTO.getCategory().getName());

        List<ProductSizeInfoResponse> productSizeInfoList = new ArrayList<>();
        for (ProductSizeDTO item : getAllByProductId(productDTO.getId())) {
            ProductSizeInfoResponse productSizeInfo = new ProductSizeInfoResponse();
            productSizeInfo.setSize(item.getSize().getName());
            productSizeInfo.setPrice(item.getPrice());
            productSizeInfoList.add(productSizeInfo);
        }

        response.setProductSizeInfo(productSizeInfoList);
        return response;
    }


    public List<ProductSizeDTO> getAllByProductId(Long productId) {
        return mapper.toDtos(rep.findAllByProductId(productId), context);
    }

    @Override
    public List<ProductSizeListResponse> filter(Long sizeId, Double fromPrice, Double toPrice, String name, Long categoryId) {
        return rep.filter(sizeId, fromPrice, toPrice, name, categoryId);
    }

    @Override
    public String addSize(ProductSizeCreateRequest request, int lang) {
        ProductSizeDTO productSizeDTO = new ProductSizeDTO();
        productSizeDTO.setPrice(request.getPrice());
        productSizeDTO.setProduct(productService.findById(request.getProductId(), lang));
        productSizeDTO.setSize(sizeService.findById(request.getSizeId(), lang));
        save(productSizeDTO);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");
    }

    @Override
    public List<ProductListResponse> getProductByCategory(Long categoryId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return rep.findProductListByCategoryId(categoryId, pageable);
    }

    @Override
    @Transactional
    public String create(ProductCreateRequest request, int lang) {

        ProductDTO product = new ProductDTO();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setLogo(fileService.upload(request.getLogo()).getDownloadUri());
            product.setCategory(categoryService.findById(request.getCategoryId(), lang));
            productService.save(product);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");

    }
}
