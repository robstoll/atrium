package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.IteratorAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.DUPLICATE_FOUND
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT

class DefaultIteratorAssertions : IteratorAssertions {
    override fun <E, T : Iterator<E>> hasNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(HAS, NEXT_ELEMENT) { it.hasNext() }

    override fun <E, T : Iterator<E>> hasNotNext(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(HAS_NOT, NEXT_ELEMENT) { !it.hasNext() }

    override fun <E, T : Iterator<E>> containsNoDuplicates(container: AssertionContainer<T>): Assertion {
        val finalList = mutableListOf<E>()

        return container.createDescriptiveAssertion(CONTAINS_DUPLICATES, DUPLICATE_FOUND) {
            if(finalList.isEmpty()){
                finalList.addAll(it.asSequence().toMutableList())
            }

            HashSet(finalList).size == finalList.size
        }
    }

}
