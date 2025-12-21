package com.group.libraryapp.calculator


fun main() {
    val calculatorTest = CalculatorTest()

    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
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

    fun divideTest() {
        val calculator = Calculator(5)
        calculator.divide(2)
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun divideExceptionTest() {
        val calculator = Calculator(5)
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다.") {
                throw IllegalStateException("예외 메세지가 다릅니다.")
            }
            // 테스트 성공
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않음.")
    }


}