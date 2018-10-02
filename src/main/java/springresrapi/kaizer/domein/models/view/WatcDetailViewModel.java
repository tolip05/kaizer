package springresrapi.kaizer.domein.models.view;

import java.math.BigDecimal;

public class WatcDetailViewModel {

    private String id;

    private String name;

    private String image;

    private BigDecimal price;

    private String description;

    private Long views;

    public WatcDetailViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getViews() {
        return this.views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
