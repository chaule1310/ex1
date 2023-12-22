package demo;
import config.SpringConfig;
import entity.BookEntity;
import repositorys.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    private static void createNewbook(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberPage(234);
        bookEntity.setPrice("20.5");
        bookEntity.setPublishDate(LocalDate.parse("2016 - 25 - 08"));

        BookEntity result = bookRepository.save(bookEntity);
        if (result != null){
            System.out.println("A new book saved successfully, book ID = " +bookEntity.getId());
        }
    }
    private static void readBook(){
        List<BookEntity> bookList=(List<BookEntity>) bookRepository.findAll();
        System.out.println("Found "+ bookList.size() + " book(s) in the table book");
        System.out.println("They are: ");
        for (BookEntity book: bookList){
            System.out.println(book.toString());
        }
    }
    public  static  void main(String[]args){
        List<BookEntity> bookEntityList = bookRepository.findByAuthor("Roger");
        if (bookEntityList.size() !=0){
            System.out.println("Found " + bookEntityList.size()+ "book(s) of Roger");
            System.out.println("They are: ");
            for (BookEntity book: bookEntityList){
                System.out.println(book.toString());
            }
        }
    }
    private static void readBook(int bookID){
        BookEntity bookList= bookRepository.findOne(bookID);
        if (bookList != null){
            System.out.println("Found a book with id:" + bookID);
            System.out.println(bookList.toString());
        }
        else {
            System.out.println("Not found any book with book Id = " +bookID);
        }
    }
    private static void updateBook {
        BookEntity bookEntity = bookRepository.findOne(1);
        System.out.println("Book date before updating");
        System.out.println(bookEntity.toString());
        bookEntity.setAuthor("Jame");
        bookEntity.setNumberPage(1999);
        bookEntity.setPrice(String.valueOf(201));
        bookRepository.save(bookEntity);
        System.out.println("Book date after updating");
        System.out.println(bookEntity.toString());
    }
    private
    static void deleteBook(){

        bookRepository.delete(1);
        BookEntity bookEntity = bookRepository.findOne(2);
        if(bookEntity != null){
            bookRepository.delete(bookEntity);
        }
        bookRepository.deleteAll();
    }

}
