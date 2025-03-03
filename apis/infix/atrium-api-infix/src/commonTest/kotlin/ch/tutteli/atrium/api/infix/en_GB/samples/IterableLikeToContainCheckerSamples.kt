package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.atLeast
import ch.tutteli.atrium.api.infix.en_GB.atMost
import ch.tutteli.atrium.api.infix.en_GB.butAtMost
import ch.tutteli.atrium.api.infix.en_GB.entry
import ch.tutteli.atrium.api.infix.en_GB.exactly
import ch.tutteli.atrium.api.infix.en_GB.inAny
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.notOrAtMost
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.order
import ch.tutteli.atrium.api.infix.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.toBeLessThanOrEqualTo
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect(listOf("A", "B", "C", "A", "B", "B")) toContain o inAny order atLeast 3 entry {
            it toEqual "B"
        }

        expect(listOf(1, 2, 3, 4, 5, 6, 4)) toContain o inAny order atLeast 2 entry {
            it toBeGreaterThan 4
        }

        fails { // because "A" is only 1 in the List
            expect(listOf("A", "B", "C")) toContain o inAny order atLeast 2 entry {
                it toEqual "A"
            }
        }
    }

    @Test
    fun butAtMost() {
        expect(listOf("A", "B", "C", "A", "B", "B")) toContain o inAny order atLeast 1 butAtMost 2 entry {
            it toEqual "A"
        }

        fails { // because "B" is three times in the List
            expect(listOf("A", "B", "C", "A", "B", "B")) toContain o inAny order atLeast 1 butAtMost 2 entry {
                it toEqual "B"
            }
        }

        fails { // because "C" is not in the List
            expect(listOf("A", "B", "C", "A", "B", "B")) toContain o inAny order atLeast 1 butAtMost 2 entry {
                it toEqual "D"
            }
        }
    }

    @Test
    fun exactly() {
        expect(listOf("A", "B", "A")) toContain o inAny order exactly 2 entry {
            it toEqual "A"
        }

        expect(listOf(1, 2, 3)) toContain o inAny order exactly 2 entry {
            it toBeGreaterThan 1
        }

        fails { // because "B" is more than twice in the List
            expect(listOf("A", "B", "B", "B")) toContain o inAny order exactly 2 entry {
                it toEqual "B"
            }
        }

        fails { // because only 1 element is less than or equal to 1
            expect(listOf(1, 2, 3)) toContain o inAny order exactly 2 entry {
                it toBeLessThanOrEqualTo 1
            }
        }
    }

    @Test
    fun notOrAtMost() {
        expect(listOf("A", "A", "B", "C")) toContain o inAny order notOrAtMost 2 entry {
            it toEqual "A"
        }

        expect(listOf(1, 2, 3)) toContain o inAny order notOrAtMost 2 entry {
            // none fulfils the expectation which is fine because we use notOrAtMost
            // use atMost if you want that at least one element matches
            it toBeGreaterThan 4
        }

        fails { // because "B" is 3 times in the List
            expect(listOf("A", "B", "B", "B")) toContain o inAny order notOrAtMost 2 entry {
                it toEqual "B"
            }
        }

        fails { // because there are 3 elements greater than 1
            expect(listOf(1, 2, 3, 3)) toContain o inAny order notOrAtMost 2 entry {
                it toBeGreaterThan 1
            }
        }
    }

    @Test
    fun atMost() {
        expect(listOf("A", "B", "B", "A", "B")) toContain o inAny order atMost 2 entry {
            it toEqual "A"
        }

        expect(listOf(1, 2, 3)) toContain o inAny order atMost 2 entry {
            it toBeLessThanOrEqualTo 2
        }

        fails { // because "B" is 3 times in the List
            expect(listOf("A", "B", "B", "A", "B")) toContain o inAny order atMost 2 entry {
                it toEqual "B"
            }
        }

        fails { // because there are 3 elements which are greater than 2
            expect(listOf(1, 2, 3, 2, 4, 3)) toContain o inAny order atMost 2 entry {
                it toBeGreaterThan 2
            }
        }

        fails { // because atMost always implicitly also means atLeast(1) and "C" is not in the List
            //     use notOrAtMost if you want such a behaviour
            expect(listOf("A", "B")) toContain o inAny order atMost 2 entry {
                it toEqual "C"
            }
        }
    }
}
