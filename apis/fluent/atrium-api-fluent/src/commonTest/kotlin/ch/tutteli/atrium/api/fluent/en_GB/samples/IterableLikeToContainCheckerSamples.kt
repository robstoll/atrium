package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainCheckerSamples {
    @Test
    fun exactly() {
        expect(listOf("A", "B", "A")).toContain.inAnyOrder.exactly(2).entry {
            toEqual("A")
        }

        expect(listOf(1, 2, 3)).toContain.inAnyOrder.exactly(2).entry {
            toBeGreaterThan(1)
        }

        fails {
            expect(listOf("A", "B", "A", "C")).toContain.inAnyOrder.exactly(2).entry {
                toEqual("B")
            }
        }

        fails {
            expect(listOf(1, 2, 3)).toContain.inAnyOrder.exactly(2).entry {
                toBeLessThanOrEqualTo(1)
            }
        }
    }

    @Test
    fun notOrAtMost() {
        expect(listOf("A", "A", "B", "C")).toContain.inAnyOrder.notOrAtMost(2).entry {
            toEqual("A")
        }

        expect(listOf(1, 2, 3)).toContain.inAnyOrder.notOrAtMost(2).entry {
            // none fulfils the expectation which is fine because we use notOrAtMost
            // use atMost if you want that at least one element matches
            toBeGreaterThan(4)
        }

        fails { // because "B" is 3 times in the List
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.notOrAtMost(2).entry {
                toEqual("B")
            }
        }

        fails { // because there are 3 items greater than 1
            expect(listOf(1, 2, 3, 3)).toContain.inAnyOrder.notOrAtMost(2).entry {
                toBeGreaterThan(1)
            }
        }

    }
}
