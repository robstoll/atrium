package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableLikeToContainInOrderOnlyCreatorSamples {
    @Test
    fun value(){
        expect(listOf("A")).toContain.inOrder.only.value("A")

        fails { // because subject list does not contain expected value
            expect(listOf("B")).toContain.inOrder.only.value("A")
        }

        fails { // because subject list contains multiple elements
            expect(listOf("A","A")).toContain.inOrder.only.value("A")
        }
    }

    @Test
    fun elementsOf(){
        expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
            listOf("A","B","C")
        )

        fails { // although same elements but not in same order
            expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
                listOf("A","C","B")
            )
        }

         fails { // because not all elements found
            expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
                listOf("A","B")
            )
        }

        fails { // because more elements expected than found
            expect(listOf("A","B","C")).toContain.inOrder.only.elementsOf(
                listOf("A","B","C","D")
            )
        }
    }
}
