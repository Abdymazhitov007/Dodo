package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.response.ProductListResponse;
import kg.demo.dodo.model.response.ProductSizeListResponse;

import java.util.List;

public interface ProductSizeService  extends BaseService<ProductSizeDTO> {
    String create(ProductCreateRequest request, Long productId, int lang);

    List<ProductSizeListResponse> getProductSizeList();


    List<ProductListResponse> getProductList();
}
