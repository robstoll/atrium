package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.HAS_SIZE
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

fun <E, T : Collection<E>> _contains(plant: IAssertionPlant<T>, expected: E, vararg otherExpected: E): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionCollectionAssertion.CONTAINS, it ?: RawString.NULL, { plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <E, T : Collection<E>> _containsNot(plant: IAssertionPlant<T>, expected: E, vararg otherExpected: E): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionCollectionAssertion.CONTAINS_NOT, it ?: RawString.NULL, { !plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <T : Collection<*>> _hasSize(plant: IAssertionPlant<T>, size: Int): IAssertion
    = BasicAssertion(HAS_SIZE, size, { plant.subject.size == size })

fun <T : Collection<*>> _isEmpty(plant: IAssertionPlant<T>): IAssertion
    = BasicAssertion(DescriptionBasic.IS, TranslatableRawString(EMPTY), { plant.subject.isEmpty() })
