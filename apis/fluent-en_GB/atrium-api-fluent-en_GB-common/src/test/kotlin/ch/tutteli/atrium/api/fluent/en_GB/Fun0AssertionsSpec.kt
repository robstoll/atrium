package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.notImplemented

class Fun0AssertionsSpec : ch.tutteli.atrium.specs.integration.Fun0AssertionsSpec(
    "toThrow" to Companion::toThrowFeature,
    "toThrow" to Companion::toThrow,
    feature0<() -> Int, Int>(Expect<() -> Int>::notToThrow),
    feature1<() -> Int, Expect<Int>.() -> Unit, Int>(Expect<() -> Int>::notToThrow),
    "⚬ ", "» "
) {

    companion object {
        fun toThrowFeature(expect: Expect<out () -> Any?>) = expect.toThrow<IllegalArgumentException>()
        fun toThrow(expect: Expect<out () -> Any?>, assertionCreator: Expect<IllegalArgumentException>.() -> Unit) =
            expect.toThrow<IllegalArgumentException> { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE", "UNUSED_VARIABLE")
    private fun ambiguityTest() {
        val a1: Expect<() -> Any?> = notImplemented()
        val a2: Expect<out () -> Int> = notImplemented()

        val r1: Expect<IllegalArgumentException> = a1.toThrow<IllegalArgumentException>()
        val r2: Expect<IllegalArgumentException> = a2.toThrow<IllegalArgumentException>()

        val r3: Expect<IllegalArgumentException> = a1.toThrow<IllegalArgumentException> {}
        val r4: Expect<IllegalArgumentException> = a2.toThrow<IllegalArgumentException> {}

        val r5: Expect<Any?> = a1.notToThrow()
        val r6: Expect<Int> = a2.notToThrow()

        val r7: Expect<Any?> = a1.notToThrow {}
        val r8: Expect<Int> = a2.notToThrow {}
    }
}
