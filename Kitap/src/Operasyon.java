import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cobain
 */

public class Operasyon {
    //import edinecekler
    Connection con = null;
    Statement sta = null;
    PreparedStatement psta = null;
    
    public int bookCount(){
        int label = 0;
    String sorgu = "SELECT COUNT(*) FROM books_database";//veritabanında bulunan kitapları sayacak sql kodu
    
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            rs.next();//veritananında bulunan son kitaba kadar sayacaktır
            label = rs.getInt(1);//gelen değerler label içerisine yazılacaktır
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return label;
    }
    
    public void bookDelete(int id){
    String sorgu ="DELETE FROM books_database WHERE id=?";//kitap silmek için çalışacak mysql kodumuz burada bulunmaktadır
        try {//sorgu çalışarak kullanıcın seçtiği kitap veritabanından silinecektir
            psta =con.prepareStatement(sorgu);
            psta.setInt(1, id);
            psta.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void bookUpdate(int id,String new_name,String new_writer,String new_type,String new_publisher,String new_pcs,String new_price){
    String sorgu = "UPDATE books_database SET book_name=?,book_writer=?,book_type=?,book_publisher=?,book_pcs=?,book_price=? WHERE id=?";
        try {//kullanıcıdan gelen verileri sorgu sayesinde güncelleyecek
            psta = con.prepareStatement(sorgu);
            psta.setString(1, new_name);
            psta.setString(2, new_writer);
            psta.setString(3, new_type);
            psta.setString(4, new_publisher);
            psta.setString(5, new_pcs);
            psta.setString(6, new_price);
            psta.setInt(7, id);
            psta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void bookAdd(String name,String writer, String type,String publisher,String pcs,String price){
    String sorgu = "INSERT INTO books_database(book_name, book_writer, book_type, book_publisher,book_pcs,book_price) VALUES (?,?,?,?,?,?)";
        try {
            psta = con.prepareStatement(sorgu);//sorgu atama işlemi
            psta.setString(1, name);
            psta.setString(2, writer);
            psta.setString(3, type);
            psta.setString(4, publisher);
            psta.setString(5, pcs);
            psta.setString(6, price);
            psta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public ArrayList<Book> bookCome(){
    ArrayList<Book> list = new ArrayList<Book>();
    String sorgu ="Select * from books_database";//veritabanında bulunan kitapları getirebilmek için sorgu
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);//gelecek değerleri ResultSet ile tutuyoruz
            
            while(rs.next()){//boş değer gelene kadar istenilen bilgileri getirecek
            int id = rs.getInt("id");
            String book_name = rs.getString("book_name");
            String book_writer = rs.getString("book_writer");
            String book_type = rs.getString("book_type");
            String book_publisher = rs.getString("book_publisher");
            int book_pcs = rs.getInt("book_pcs");
            int book_price = rs.getInt("book_price");
            list.add(new Book(id,book_name,book_writer,book_type,book_publisher,book_pcs,book_price));//ekleme işlemini yapıyoruz
            }
            return list;//eklenen parametreler ile birlikte lisyeyi döndür hata varsa boş döndürülecek
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
    public boolean Login(String id,String password){
        //veritabanı bağlantısından true false almak için boolean yapıyoruz 
        String sorgu = "Select * from admin where id= ? and password = ?";
        try {//sorgu içerisinde kullanılmak üzere kullanıcıdan gelen bilgileri değişkenlerde tutarak sorgulamamızı gerçekleştiriyoruz
            psta =  con.prepareStatement(sorgu);
            psta.setString(1,id);
            psta.setString(2,password);
            ResultSet rs= psta.executeQuery();//dönecek sonucu tutuyoruz
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    public Operasyon() {
        //url-> jdbc:mysql://host:port/db_name;id;password; veritabanı bağlantısı
        String url="jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_name+"?useUnicode=true&characterEncoding=UTF-8";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,Database.id,Database.password);
            System.out.println("Veritabanına Başarıyla Bağlandınız! :");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver Çalışmadı :/");
        } catch (SQLException ex) {
            Logger.getLogger(Operasyon.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Bağlantı Çalışmadı :/");
        }
        
    }
    public static void main(String[] args) {
        Operasyon op = new Operasyon();//classı çalıştırma anahtarı
    }
    
}
