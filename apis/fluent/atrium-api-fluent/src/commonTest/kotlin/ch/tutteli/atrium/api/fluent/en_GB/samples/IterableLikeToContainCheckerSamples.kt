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
}
