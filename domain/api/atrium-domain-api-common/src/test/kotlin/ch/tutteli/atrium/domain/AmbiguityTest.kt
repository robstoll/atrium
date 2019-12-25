package ch.tutteli.atrium.domain

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import kotlin.test.Test

class AmbiguityTest {

    @Test
    fun iterable() {
        val elementNullable: Expect<Iterable<Int?>> = expect(listOf(1, null).asIterable())
        val elementComparable: Expect<Iterable<Int>> = expect(listOf(1, 2).asIterable())

        // IterableDomain
        elementNullable.apply { addAssertion(_domain.hasNext()) }

        // IterableElementComparableDomain -> IterableDomain
        elementComparable.apply { addAssertion(_domain.hasNext()) }

        // ListElementComparable -> IterableElementComparableDomain
        elementComparable._domain.min().addToInitial { addAssertion(_domain.toBe(1)) }
    }

    @Test
    fun collection() {
        val elementNullable: Expect<Collection<Int?>> = expect(listOf(1, null) as Collection<Int?>)
        val elementComparable: Expect<Collection<Int>> = expect(listOf(1, 2) as Collection<Int>)

        // CollectionDomain -> IterableDomain
        elementNullable.apply { addAssertion(_domain.hasNext()) }

        // CollectionElementComparableDomain -> CollectionDomain
        elementComparable.apply { addAssertion(_domain.hasNext()) }

        // CollectionElementComparableDomain -> IterableDomain
        elementComparable.apply { addAssertion(_domain.hasNext()) }

        // CollectionElementComparableDomain -> IterableElementComparableDomain
        elementComparable._domain.min().addToInitial { addAssertion(_domain.toBe(1)) }
    }

    @Test
    fun list() {
        val elementNullable: Expect<List<Int?>> = expect(listOf(1, null))
        val elementComparable: Expect<List<Int>> = expect(listOf(1, 2))

        // ListDomain
        elementNullable._domain.get(1)

        // ListElementComparable -> ListDomain
        elementComparable._domain.get(1)

        // ListElementComparable -> CollectionDomain
        elementComparable._domain.size

        // ListElementComparable -> IterableDomain
        elementComparable._domain.hasNext()

        // ListElementComparable -> IterableElementComparableDomain
        elementComparable._domain.min()
    }

    @Test
    fun map() {
        val nullableValue: Expect<Map<Int, Int?>> = expect(mapOf(1 to null))
        val nonNullableValue: Expect<Map<Int, Int>> = expect(mapOf(1 to 1))

        // MapDomain
        nullableValue._domain.size.addToInitial { addAssertion(_domain.toBe(1)) }

        // MapValueAnyDomain -> MapDomain
        nonNullableValue._domain.size.addToInitial { addAssertion(_domain.toBe(1)) }

        // MapValueAnyDomain
        nonNullableValue.apply { addAssertion(_domain.contains(listOf(1 to 1))) }
    }
}
