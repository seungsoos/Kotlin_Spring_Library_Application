package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
){

    @AfterEach
    fun cleanup(){
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun saveBookTest() {
        //given
        val bookRequest = BookRequest("자바")
        //when
        bookService.saveBook(bookRequest)
        //then
        val results = bookRepository.findAll()
        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].name).isEqualTo("자바")
    }

    @Test
    fun loanBookTest() {
        //given
        bookRepository.save(Book("자바"))
        val savedUser = userRepository.save(User("A", null))
        val request = BookLoanRequest("A", "자바")

        //when
        bookService.loanBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].bookName).isEqualTo(request.bookName)
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isFalse()
    }

    @Test
    fun loanBookExceptionTest() {
        //given
        bookRepository.save(Book("자바"))
        val savedUser = userRepository.save(User("A", null))
        val request = BookLoanRequest("A", "자바")

        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "자바", false))
        //when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    fun returnBookTest() {
        //given
        bookRepository.save(Book("자바"))
        val savedUser = userRepository.save(User("A", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "자바", false))
        val request = BookReturnRequest("A", "자바")

        //when
        bookService.returnBook(request)
        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].bookName).isEqualTo("자바")
        assertThat(results[0].isReturn).isTrue()
    }
}