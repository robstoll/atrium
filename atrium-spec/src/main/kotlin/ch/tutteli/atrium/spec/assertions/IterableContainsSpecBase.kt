package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

abstract class IterableContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        val oneToSeven = listOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0)

        val containsInAnyOrder = String.format(DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInAnyOrderOnly = String.format(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()

        val atLeast = DescriptionIterableAssertion.AT_LEAST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!
    }
}
