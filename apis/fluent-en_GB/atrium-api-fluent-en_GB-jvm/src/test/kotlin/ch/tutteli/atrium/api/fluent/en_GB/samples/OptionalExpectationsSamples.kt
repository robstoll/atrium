package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import org.junit.jupiter.api.Test
import java.util.*

class OptionalExpectationsSamples {

    @Test
    fun toBeEmpty() {
        val empty: String? = null
        val opt = Optional.ofNullable(empty)

        expect(opt).toBeEmpty()
    }

    @Test
    fun toBePresent() {
        val notEmpty: String? = "toBePresent"
        val opt = Optional.ofNullable(notEmpty)

        expect(opt).toBePresent()
    }

    @Test
    fun toBePresentFeature() {
        val x  = 10
        val y  = 5

        val optX = Optional.ofNullable(x)
        val optY = Optional.ofNullable(y)

        expect(optX) {

            val sum = optX.get() + optY.get()

            feature("toBeLessThan") { sum }.toBeLessThan(20)
            feature("toBeGreaterThan") { sum }.toBeGreaterThan(5)
            feature("toBeGreaterThanOrEqualTo") { sum }.toBeGreaterThanOrEqualTo(15)

        }.toBePresent()


    }


}
