package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTest {

    companion object{
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("Before All")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("After All")
        }


    }

    @BeforeEach
    fun beforeEach() {
        println("Before Each")
    }

    @AfterEach
    fun afterEach() {
        println("After Each")
    }


    @Test
    fun test1() {
     println("test1")
    }

    @Test
    fun test2() {
      println("test2")
    }

}