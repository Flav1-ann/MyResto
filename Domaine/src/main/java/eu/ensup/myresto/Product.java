package eu.ensup.myresto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Product.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    private String name;
    private float price;
    private String picture;
    private String description;

    @ManyToOne
    private Category category;

    public Product(int id, String name, float price, String picture, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.category = category;
    }


    public Product(String name, float price, String picture, String description, Category category) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.category = category;
    }

    public Product() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets picture.
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    public Category getIdCategory() {
        return category;
    }


    public void setIdCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "eu.ensup.myresto.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
