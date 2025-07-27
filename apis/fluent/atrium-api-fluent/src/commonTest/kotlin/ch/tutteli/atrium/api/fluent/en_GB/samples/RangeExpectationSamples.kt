package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.toBeInRange
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class RangeExpectationSamples {

    @Test
    fun toBeInRangeChar(){
        expect(3).toBeInRange(1..5)
    }

    @Test
    fun toBeInRangeInt(){
        expect(100L).toBeInRange(50L..200L)
    }

    @Test
    fun toBeInRangeLong(){
        expect('c').toBeInRange('a'..'f')
    }
}
