
public class BookData {
    private String author;
    private String title;
    private String type;
    private BookPrice price;
    private Integer pages;

    public BookData() {}

    public BookData(String author, String title, String type, BookPrice price, Integer pages) {
        this.author = author;
        this.title = title;
        this.type = type;
        this.price = price;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BookPrice getPrice() {
        return price;
    }

    public void setPrice(BookPrice price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
