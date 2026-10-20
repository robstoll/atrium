package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.toBeIn
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class RangeExpectationSamples {

    @Test
    fun toBeInCharRange(){
        expect(3).toBeIn(1..5)

        fails {
            expect(0).toBeIn(1..5)
        }
    }

    @Test
    fun toBeInIntRange(){
        expect(100L).toBeIn(50L..200L)

        fails {
            expect(0L).toBeIn(50L..200L)
        }
    }

    @Test
    fun toBeInLongRange(){
        expect('c').toBeIn('a'..'f')

        fails {
            expect('A').toBeIn('a'..'f')
        }
    }
}
