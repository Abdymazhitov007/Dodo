package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.requests.ProductSizeCreateRequest;
import kg.demo.dodo.model.response.ProductFullInfoResponse;
import kg.demo.dodo.model.response.ProductListResponse;
import kg.demo.dodo.model.response.ProductSizeListResponse;

import java.util.List;

public interface ProductSizeService  extends BaseService<ProductSizeDTO> {
    String create(ProductCreateRequest request, int lang);

    List<ProductListResponse> getProductByCategory(Long categoryId, int pageNum, int pageSize);

    ProductFullInfoResponse getFullInfoByProductId(Long productId, int lang);

    List<ProductSizeDTO> getAllByProductId(Long productId);

    List<ProductSizeListResponse> filter(Long sizeId, Double fromPrice, Double toPrice, String name, Long categoryId);

    String addSize(ProductSizeCreateRequest request, int lang);
}
