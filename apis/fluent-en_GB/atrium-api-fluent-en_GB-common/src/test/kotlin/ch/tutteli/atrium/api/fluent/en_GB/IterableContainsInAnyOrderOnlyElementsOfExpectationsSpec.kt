package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderOnlyElementsOfExpectationsSpec.Companion as C

class IterableContainsInAnyOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(BuilderIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderOnlyValuesExpectationsSpec(
        functionDescription to C::toContainElementsOf,
        (functionDescription to C::toContainElementsOfNullable).withNullableSuffix()
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        functionDescription,
        listOf(1, 2),
        { input -> toContain.inAnyOrder.only.elementsOf(input) }
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$elementsOf"

        private fun toContainElementsOf(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.toContain.inAnyOrder.only.elementsOf(listOf(a, *aX))

        private fun toContainElementsOfNullable(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.toContain.inAnyOrder.only.elementsOf(sequenceOf(a, *aX))
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.only.elementsOf(listOf<Int>())
        nList = nList.toContain.inAnyOrder.only.elementsOf(listOf<Int>())
        subList = subList.toContain.inAnyOrder.only.elementsOf(listOf<Int>())
        star = star.toContain.inAnyOrder.only.elementsOf(listOf<Int>())
    }
}
