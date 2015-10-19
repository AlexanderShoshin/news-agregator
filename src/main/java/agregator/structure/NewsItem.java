package agregator.structure;

import java.util.ArrayList;
import java.util.List;

public class NewsItem {
    private String id;
    private String category;
    private String title;
    private String description;
    private List<String> images;
    private String publishedDate;
    private String author;
    private String source;
    
    public NewsItem() {
        images = new ArrayList<String>();
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getImagesCount() {
        return images.size();
    }
    public String getImage(int id) {
        return  images.get(id);
    }
    public void addImage(String imageSrc) {
        this.images.add(imageSrc);
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
}