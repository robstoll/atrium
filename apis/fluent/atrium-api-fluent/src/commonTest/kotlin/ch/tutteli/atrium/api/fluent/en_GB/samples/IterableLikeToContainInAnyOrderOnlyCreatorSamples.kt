package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInAnyOrderOnlyCreatorSamples {
    @Test
    fun value() {
        expect(listOf("A")).toContain.inAnyOrder.only.value("A")

        fails { // because subject list does not contain expected value
            expect(listOf("B")).toContain.inAnyOrder.only.value("A")
        }

        fails { // because subject list contains multiple elements
            expect(listOf("A", "A")).toContain.inAnyOrder.only.value("A")
        }
    }

    @Test
    fun values() {
        expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
            "C", "B", "A"
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
                "B", "A"
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.values(
                "D", "C", "B", "A"
            )
        }
    }

    @Test
    fun elementsOf() {
        expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
            listOf("A", "B", "C")
        )

        fails { // because not all elements found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
                listOf("B", "A")
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A", "B", "C")).toContain.inAnyOrder.only.elementsOf(
                listOf("B", "A", "C", "D")
            )
        }
    }
}
