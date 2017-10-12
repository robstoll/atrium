package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

fun <E, T : Iterable<E>> _containsNot(plant: IAssertionPlant<T>, expected: E, vararg otherExpected: E): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    listOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            BasicAssertion(DescriptionIterableAssertion.CONTAINS_NOT, it ?: RawString.NULL, { !plant.subject.contains(it) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}
