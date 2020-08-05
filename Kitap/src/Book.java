/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *veri tabanındaki kitap bilgilerini hem tablo içerisine getirerek hem de güncelleme ekleme ve silme işlemleri için get ve setlerini tanımlıyoruz
 * @author Cobain
 */
public class Book {
    private int id;
    private String book_name;
    private String book_writer;
    private String book_type;
    private String book_publisher;
    private int book_pcs;
    private int book_price;

    public int getBook_pcs() {
        return book_pcs;
    }

    public void setBook_pcs(int book_pcs) {
        this.book_pcs = book_pcs;
    }

    public int getBook_price() {
        return book_price;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    //döngülerin içerisinde sürekli bilgileri bir diziye aktaracağımız için bir constructor tanımlıyoruz
    
    public Book(int id, String book_name, String book_writer, String book_type, String book_publisher, int book_pcs, int book_price) {
        this.id = id;
        this.book_name = book_name;
        this.book_writer = book_writer;
        this.book_type = book_type;
        this.book_publisher = book_publisher;
        this.book_pcs = book_pcs;
        this.book_price = book_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_writer() {
        return book_writer;
    }

    public void setBook_writer(String book_writer) {
        this.book_writer = book_writer;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }
}
