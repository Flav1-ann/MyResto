package eu.ensup.myresto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private int idCategory;

    /**
     * Instantiates a new Product.
     *
     * @param id          the id
     * @param name        the name
     * @param price       the price
     * @param picture     the picture
     * @param description the description
     * @param idCategory  the id category
     */
    public Product(int id, String name, float price, String picture, String description, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.idCategory = idCategory;
    }

    /**
     * Instantiates a new Product.
     *
     * @param name        the name
     * @param price       the price
     * @param picture     the picture
     * @param description the description
     * @param idCategory  the id category
     */
    public Product(String name, float price, String picture, String description, int idCategory) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.idCategory = idCategory;
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

    /**
     * Gets id category.
     *
     * @return the id category
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Sets id category.
     *
     * @param idCategory the id category
     */
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "eu.ensup.myresto.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", idCategory=" + idCategory +
                '}';
    }
}
