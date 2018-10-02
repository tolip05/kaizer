package springresrapi.kaizer.domein.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "watches")
public class Watch {

    @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
                name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator"
        )
        @Column(name = "id",updatable = false,unique = true,nullable = false)
    private String id;

    @Column(name = "names",nullable = false)
    private String name;

    @Column(name = "image",nullable = false)
    private String image;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "views",nullable = false)
    private long views;

    public Watch() {
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

    public long getViews() {
        return this.views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
