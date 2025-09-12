package Model;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cate_id;
    @Column(name = "cate_name")
    private String cate_name;
    @Column(name = "icons")
    private String icons;
    @ManyToOne
    @JoinColumn(name = "user_id")  // cột foreign key trong bảng Categories
    private User user;
    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }
}
