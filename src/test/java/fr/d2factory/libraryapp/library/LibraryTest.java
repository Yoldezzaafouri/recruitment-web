package fr.d2factory.libraryapp.library;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Do not forget to consult the README.md :)
 */
public class LibraryTest {
	private Library library;
	private BookRepository bookRepository;
	private static List<Book> books;

	@BeforeEach
	void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File booksJson = new File("src/test/resources/books.json");
		books = mapper.readValue(booksJson, new TypeReference<List<Book>>() {
		});
		bookRepository = new BookRepository();
		bookRepository.addBooks(books);

	}

	@Test
	void member_can_borrow_a_book_if_book_is_available() {

		Member member = new Student();

		Book b1 = bookRepository.findBook(968787565445L);

		if (b1 != null) {

			if (member instanceof Student)
				Assertions.assertTrue(!member.isLate());
			else
				Assertions.assertTrue(!member.isLate());

		} else {
			System.out.println("Book is not aivalable");
		}

	}

	@Test
	void borrowed_book_is_no_longer_available() {
		LocalDate borrowedAt = LocalDate.now();
		Book b1 = bookRepository.findBook(46578964513L);
		bookRepository.saveBookBorrow(b1, borrowedAt);

		// System.out.println(b1);


		bookRepository.getBorrowedBooks().forEach((key, value) -> {
			Assertions.assertTrue(key.equals(b1));

		});
	}

	@Test
	void residents_are_taxed_10cents_for_each_day_they_keep_a_book() {

		Member resident = new Resident();
		resident.setWallet(100);
		resident.payBook(61);
		Assertions.assertTrue(resident.isLate());
	}

	// Recheck

	@Test
	void students_pay_10_cents_the_first_30days() {

		Member student = new Student();
		student.setWallet(100);

		Assertions.assertEquals(student.payBook(30), 3.0f);

	}

	@Test
	void students_in_1st_year_are_not_taxed_for_the_first_15days() {

		Member student = new Student(1);
		student.setWallet(100);
		Assertions.assertEquals(student.payBook(46), 3.2f);

	}

	@Test
	void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days() {

		Member resident = new Resident();
		resident.setWallet(100);

		Assertions.assertEquals(resident.payBook(61), 6.2f);

	}

	@Test
	void members_cannot_borrow_book_if_they_have_late_books() {
		Member resident = new Resident();
		Member student = new Student();

		if (resident instanceof Member) {
			resident.payBook(151);
			Assertions.assertFalse(!resident.isLate());

		} else {

			student.payBook(100);
			Assertions.assertFalse(!student.isLate());

		}

	}

}
