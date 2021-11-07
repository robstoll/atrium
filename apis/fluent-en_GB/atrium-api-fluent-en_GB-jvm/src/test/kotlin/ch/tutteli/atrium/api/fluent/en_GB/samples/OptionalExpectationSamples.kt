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

        val opt = Optional.of(1)

        expect(opt)
            .toBePresent() //subject is now of type Int

        fails {
            expect(opt).toBeEmpty() //fails
        }

    }

    @Test
    fun toBePresent() {
        val opt = Optional.of(10)

        expect(opt).toBePresent() {
            toBeGreaterThan(0)
            toBeLessThan(11)
        }

        fails {
            expect(opt).toBePresent() {
                toBeGreaterThan(15)
                toBeLessThan(9)
            }
        }
    }
}
