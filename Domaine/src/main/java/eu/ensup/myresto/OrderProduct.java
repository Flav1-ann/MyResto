package eu.ensup.myresto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * The type Order product.
 */
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;

    @ManyToOne
    private User idUser;

    @OneToMany(mappedBy = "orderProduct", fetch = FetchType.LAZY)
    private List<Product> products;


    private String status;
    private Date dateCreated;

    public OrderProduct() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public List<Product> getIdProduct() {
        return products;
    }

    public void setIdProduct(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * New status.
         */
        NEW,
        /**
         * Send status.
         */
        SEND,
        /**
         * Close status.
         */
        CLOSE
    }


    @Override
    public String toString() {
        return "eu.ensup.myresto.OrderProduct{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idProduct=" + products +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
