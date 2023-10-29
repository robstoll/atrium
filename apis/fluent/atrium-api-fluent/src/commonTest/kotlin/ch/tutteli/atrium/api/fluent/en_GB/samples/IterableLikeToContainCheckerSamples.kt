package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.collections.*

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

    @Test
    fun atMost() {
        expect(listOf("A,A,B,C,A,B,B")).toContain.inAnyOrder.atMost(2).entry{
            toEqual("C")
        }

        expect(listOf(1,2,3,2,3,3)).toContain.inAnyOrder.atMost(2).entry{
            toBeLessThanOrEqualTo(2)
        }

        fails {
            expect(listOf("A,A,B,B,C,C")).toContain.inAnyOrder.atMost(1).entry{
                toEqual("A")
            }
        }

        fails {
            expect(listOf(1,2,3,2,3,3)).toContain.inAnyOrder.atMost(2).entry{
                toBeGreaterThan(2)
            }
        }
    }

    @Test
    fun butAtMost() {
        expect(listOf("A,B,C,A,B,B")).toContain.inAnyOrder.atLeast(2).butAtMost(2).entry{
            toEqual("A")
        }

        expect(listOf(1,2,3,4,5,6,4,5,5,7,7,7,7)).toContain.inAnyOrder.atLeast(3).butAtMost(4).entry{
            toEqual(5)
        }

        fails {
            expect(listOf("A,B,B,B,B,B,C,C")).toContain.inAnyOrder.atLeast(3).butAtMost(4).entry{
                toEqual("A")
            }
        }
    }

    @Test
    fun atLeast() {
        expect(listOf("A,B,C,A,B,B")).toContain.inAnyOrder.atLeast(3).entry{
            toEqual("B")
        }

        expect(listOf(1,2,3,4,5,6,4)).toContain.inAnyOrder.atLeast(2).entry{
            toEqual(4)
        }

        fails {
            expect(listOf("A,B,C")).toContain.inAnyOrder.atLeast(2).entry{
                toEqual("A")
            }
        }
    }
}
