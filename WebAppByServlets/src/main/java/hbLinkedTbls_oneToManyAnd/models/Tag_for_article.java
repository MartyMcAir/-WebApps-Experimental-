package hbLinkedTbls_oneToManyAnd.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag_for_article {
    @Id
    @GeneratedValue
    private int id;

    private String tag_name;

    public Tag_for_article() {
    }

    public Tag_for_article(String tag_name) {
        this.tag_name = tag_name;
    }

    //    @ManyToOne(targetEntity = Article.class, fetch = FetchType.EAGER, optional = true)
    @OneToMany
    private List<Article> articleList; // у одного тега много статей

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}