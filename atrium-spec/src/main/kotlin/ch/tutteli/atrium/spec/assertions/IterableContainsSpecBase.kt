package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

abstract class IterableContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        val text = listOf(1.9, 5.8, 3.7, 2.6, 4.5)
        val oneToSeven = listOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0)

        val containsInAnyOrder = String.format(DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!
    }
}
