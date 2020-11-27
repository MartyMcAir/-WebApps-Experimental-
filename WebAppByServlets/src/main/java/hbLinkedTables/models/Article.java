package hbLinkedTables.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private int id;

//        @OneToMany(mappedBy = "Article", cascade = CascadeType.DETACH, orphanRemoval = true)
    @OneToMany
    List<Tag_for_article> forArticleList; // у одной статьи много тегов

    private String article_content;
    private String article_name;

    public List<Tag_for_article> getForArticleList() {
        return forArticleList;
    }

    public void setForArticleList(List<Tag_for_article> forArticleList) {
        this.forArticleList = forArticleList;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
