package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec.Companion as C

class IterableContainsInAnyOrderAtLeast1EntriesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec(
        functionDescription to C::toContainInAnyOrderEntries,
        (functionDescription to C::toContainNullableEntries).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec(
        fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>(Expect<Iterable<Double>>::toContain),
        fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>(Expect<Iterable<Double?>>::toContain).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$atLeast(1).$entry/$entries"

        private fun toContainInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).entry(a)
            else expect.toContain.inAnyOrder.atLeast(1).entries(a, *aX)

        private fun toContainNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).entry(a)
            else expect.toContain.inAnyOrder.atLeast(1).entries(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.atLeast(1).entry {}
        nList = nList.toContain.inAnyOrder.atLeast(1).entry {}
        subList = subList.toContain.inAnyOrder.atLeast(1).entry {}
        star = star.toContain.inAnyOrder.atLeast(1).entry {}

        nList = nList.toContain.inAnyOrder.atLeast(1).entry(null)
        star = star.toContain.inAnyOrder.atLeast(1).entry(null)

        list = list.toContain.inAnyOrder.atLeast(1).entries({}, {})
        nList = nList.toContain.inAnyOrder.atLeast(1).entries({}, {})
        subList = subList.toContain.inAnyOrder.atLeast(1).entries({}, {})
        star = star.toContain.inAnyOrder.atLeast(1).entries({}, {})

        nList = nList.toContain.inAnyOrder.atLeast(1).entries(null, {}, null)
        star = star.toContain.inAnyOrder.atLeast(1).entries(null, {}, null)

        list = list.toContain {}
        nList = nList.toContain {}
        subList = subList.toContain {}
        star = star.toContain {}

        nList = nList.toContain(null)
        star = star.toContain(null)

        list = list.toContain({}, {})
        nList = nList.toContain({}, {})
        subList = subList.toContain({}, {})
        star = star.toContain({}, {})

        nList = nList.toContain(null, {}, null)
        star = star.toContain(null, {}, null)
    }
}
