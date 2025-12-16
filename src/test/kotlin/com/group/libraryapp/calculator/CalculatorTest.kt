package com.group.libraryapp.calculator

import org.junit.jupiter.api.Assertions.*

fun main() {
    val calculatorTest = CalculatorTest()

    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        //then

        // data 클래스 사용시
//        val expectedCalculator = Calculator(8)
//        if (calculator != expectedCalculator) {
//            throw IllegalArgumentException()
//        }

        // public 필드 사용시
        // setter 사용이 가능해서 주의 필요
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }

        // _네이밍으로 변수명 변경 후 내부적으로 get메서드를 통해 추출 가능함.
//        class Calculator(
//            private var _number: Int
//        ) {
//            val number: Int
//                get() = this._number
    }

    fun minusTest() {
        val calculator = Calculator(5)
        calculator.minus(3)
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun multiplyTest() {
        val calculator = Calculator(5)
        calculator.multiply(3)
        if (calculator.number != 15) {
            throw IllegalArgumentException()
        }
    }
}