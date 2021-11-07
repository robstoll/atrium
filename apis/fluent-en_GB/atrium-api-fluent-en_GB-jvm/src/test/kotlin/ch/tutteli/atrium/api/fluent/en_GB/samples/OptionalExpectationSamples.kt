package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import org.junit.jupiter.api.Test
import java.util.*

class OptionalExpectationSamples {

    @Test
    fun toBeEmpty() {

        val opt: Optional<String> = Optional.empty()

        expect(opt).toBeEmpty()

        fails {
            expect(opt).toBePresent() // fails
        }
    }

    @Test
    fun toBePresentFeature() {
        val notEmpty: String? = "toBePresentFeature"
        val opt = Optional.ofNullable(notEmpty)


        expect(opt).toBePresent()

        fails {
            expect(opt).toBeEmpty()
        }
    }


    @Test
    fun toBePresent() {
        val x = 10
        val y = 5

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
