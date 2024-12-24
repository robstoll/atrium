package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.collections.*

class IterableLikeToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect(listOf("A", "B", "C", "A", "B", "B")).toContain.inAnyOrder.atLeast(3).entry {
            toEqual("B")
        }

        expect(listOf(1, 2, 3, 4, 5, 6, 4)).toContain.inAnyOrder.atLeast(2).entry {
            toBeGreaterThan(4)
        }

        fails { // because "A" is only 1 in the List
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.atLeast(2).entry {
                toEqual("A")
            }
        }
    }


    @Test
    fun butAtMost() {
        expect(listOf("A", "B", "C", "A", "B", "B")).toContain.inAnyOrder.atLeast(1).butAtMost(2).entry {
            toEqual("A")
        }

        fails { // because "B" is three times in the List
            expect(listOf("A", "B", "C", "A", "B", "B")).toContain.inAnyOrder.atLeast(1).butAtMost(2).entry {
                toEqual("B")
            }
        }

        fails { // because "C" is not in the List
            expect(listOf("A", "B", "C", "A", "B", "B")).toContain.inAnyOrder.atLeast(1).butAtMost(2).entry {
                toEqual("D")
            }
        }
    }

    @Test
    fun exactly() {
        expect(listOf("A", "B", "A")).toContain.inAnyOrder.exactly(2).entry {
            toEqual("A")
        }

        expect(listOf(1, 2, 3)).toContain.inAnyOrder.exactly(2).entry {
            toBeGreaterThan(1)
        }

        fails { // because "B" is more than twice in the List
            expect(listOf("A", "B", "B", "B")).toContain.inAnyOrder.exactly(2).entry {
                toEqual("B")
            }
        }

        fails { // because only 1 element is less than or equal to 1
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

        fails { // because there are 3 elements greater than 1
            expect(listOf(1, 2, 3, 3)).toContain.inAnyOrder.notOrAtMost(2).entry {
                toBeGreaterThan(1)
            }
        }
    }

    @Test
    fun atMost() {
        expect(listOf("A", "B", "B", "A", "B")).toContain.inAnyOrder.atMost(2).entry {
            toEqual("A")
        }

        expect(listOf(1, 2, 3)).toContain.inAnyOrder.atMost(2).entry {
            toBeLessThanOrEqualTo(2)
        }

        fails { // because "B" is 3 times in the List
            expect(listOf("A", "B", "B", "A", "B")).toContain.inAnyOrder.atMost(2).entry {
                toEqual("B")
            }
        }

        fails { // because there are 3 elements which are greater than 2
            expect(listOf(1, 2, 3, 2, 4, 3)).toContain.inAnyOrder.atMost(2).entry {
                toBeGreaterThan(2)
            }
        }

        fails { // because atMost always implicitly also means atLeast(1) and "C" is not in the List
            //     use notOrAtMost if you want such a behaviour
            expect(listOf("A", "B")).toContain.inAnyOrder.atMost(2).entry {
                toEqual("C")
            }
        }
    }
}
