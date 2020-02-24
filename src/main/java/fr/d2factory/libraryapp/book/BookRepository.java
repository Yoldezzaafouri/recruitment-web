package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
	public Map<ISBN, Book> getAvailableBooks() {
		return availableBooks;
	}

	public void setAvailableBooks(Map<ISBN, Book> availableBooks) {
		this.availableBooks = availableBooks;
	}

	public Map<Book, LocalDate> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(Map<Book, LocalDate> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	private static final LocalDate localDateBook = null;
	private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();
	private Book book;

    
 
    public void addBooks(List<Book> books){
    	
        books.forEach(book -> {
        	availableBooks.put(book.getIsbn(), book);
        });

        
    	
    }

    public Book findBook(long isbnCode) {
    	
    	Book book = null;
    	for (Map.Entry<ISBN, Book> entry : availableBooks.entrySet()) {
    		if (entry.getValue().getIsbn().getIsbnCode() == isbnCode) {
    			book=entry.getValue();
    		}

    	}
    	
		return book;
        
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        LocalDate date = LocalDate.now();  

		availableBooks.remove(book.getIsbn());
    	borrowedBooks.put(book, date);
    	
    

    }

    public LocalDate findBorrowedBookDate(Book book) {
    	
    	
    	borrowedBooks.forEach((key, value) -> {
    		if(key.getIsbn().getIsbnCode() == book.getIsbn().getIsbnCode()){
    			System.out.println(value.toString());
    			LocalDate localDateBook = value;
    		}
    	});
    	
    	
		return localDateBook;
    }
    
    
}
