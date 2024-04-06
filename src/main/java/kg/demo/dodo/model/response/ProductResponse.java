package kg.demo.dodo.model.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    private Long id;
    private String name;
    private String category;
    private String size;
    private Integer quantity;
    private Double price;

    public ProductResponse() {
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getSize() {
        return this.size;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String toString() {
        return "ProductResponse(id=" + this.getId() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", size=" + this.getSize() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ")";
    }
}
