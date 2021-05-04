package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInOrderOnlyEntriesExpectationsSpec.Companion as C

class IterableToContainInOrderOnlyEntriesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        functionDescription to C::toContainInOrderOnly,
        (functionDescription to C::toContainInOrderOnlyNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyEntriesExpectationsSpec(
        fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>(Expect<Iterable<Double>>::toContainExactly),
        fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>(Expect<Iterable<Double?>>::toContainExactly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$entry/$entries"

        private fun toContainInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
            else expect.toContain.inOrder.only.entries(a, *aX)

        private fun toContainInOrderOnlyNullable(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inOrder.only.entry(a)
            else expect.toContain.inOrder.only.entries(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.entry {}
        nList = nList.toContain.inOrder.only.entry {}
        subList = subList.toContain.inOrder.only.entry {}
        star = star.toContain.inOrder.only.entry {}

        nList = nList.toContain.inOrder.only.entry(null)
        star = star.toContain.inOrder.only.entry(null)

        list = list.toContain.inOrder.only.entries({}, {})
        nList = nList.toContain.inOrder.only.entries({}, {})
        subList = subList.toContain.inOrder.only.entries({}, {})
        star = star.toContain.inOrder.only.entries({}, {})

        nList = nList.toContain.inOrder.only.entries(null, {}, null)
        star = star.toContain.inOrder.only.entries(null, {}, null)

        list = list.toContainExactly {}
        nList = nList.toContainExactly {}
        subList = subList.toContainExactly {}
        star = star.toContainExactly {}

        nList = nList.toContainExactly(null)
        star = star.toContainExactly(null)

        list = list.toContainExactly({}, {})
        nList = nList.toContainExactly({}, {})
        subList = subList.toContainExactly({}, {})
        star = star.toContainExactly({}, {})

        nList = nList.toContainExactly(null, {}, null)
        star = star.toContainExactly(null, {}, null)
    }
}
