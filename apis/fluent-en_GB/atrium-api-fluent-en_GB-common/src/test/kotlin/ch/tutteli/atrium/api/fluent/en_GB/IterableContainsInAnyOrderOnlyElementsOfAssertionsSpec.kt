package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderOnlyElementsOfAssertionsSpec.Companion as C

class IterableContainsInAnyOrderOnlyElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(BuilderIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
          functionDescription to C::containsElementsOf,
        (functionDescription to C::containsElementsOfNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» "
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        functionDescription,
        listOf(1, 2),
        { input -> contains.inAnyOrder.only.elementsOf(input) }
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$only.$elementsOf"

        private fun containsElementsOf(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inAnyOrder.only.elementsOf(listOf(a, *aX))

        private fun containsElementsOfNullable(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inAnyOrder.only.elementsOf(sequenceOf(a, *aX))
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.only.elementsOf(listOf<Int>())
        nList = nList.contains.inAnyOrder.only.elementsOf(listOf<Int>())
        subList = subList.contains.inAnyOrder.only.elementsOf(listOf<Int>())
        star = star.contains.inAnyOrder.only.elementsOf(listOf<Int>())
    }
}
