package hbLinkedTbls_oneToManyAnd.makingStock;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

// https://mkyong.com/hibernate/hibernate-many-to-many-relationship-example-annotation/
@Entity
@Table(name = "category", catalog = "postgres")
public class Category implements java.io.Serializable {

    private Integer categoryId;
    private String name;
    private String desc;

    //////// my
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Stock> stocks = new HashSet<Stock>(0);

    public Category() {
    }

    public Category(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Category(String name, String desc, Set<Stock> stocks) {
        this.name = name;
        this.desc = desc;
        this.stocks = stocks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CATEGORY_ID", unique = true, nullable = false)
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "NAME", nullable = false, length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "[DESC]", nullable = false)
    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    public Set<Stock> getStocks() {
        return this.stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

}