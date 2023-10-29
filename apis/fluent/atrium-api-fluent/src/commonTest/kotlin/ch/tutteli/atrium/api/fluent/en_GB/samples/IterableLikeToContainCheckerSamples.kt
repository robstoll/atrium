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
