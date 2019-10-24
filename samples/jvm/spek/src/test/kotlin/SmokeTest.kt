package org.spekframework.spek2

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import org.spekframework.spek2.style.specification.describe
import java.lang.IllegalArgumentException

object CalculatorTestBySpek : Spek({
    describe("Calculator") {
        val calculator by memoized { Calculator() }

        it("1 + 2 = 3") {
            val sum = 3
            expect(calculator.add(1, 2)).toBe(sum)
        }

        it("division sanity") {
            expect {
                // the below statement throws exception
                calculator.divide(12, 0)
            }.toThrow<IllegalArgumentException> { messageContains("division by zero") }
        }

        it("defaults to fancy"){
            expect(calculator.isFancy).toBe(true)
        }
    }
})

