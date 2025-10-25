package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.withFeatureSuffix
import kotlin.test.Test

class Fun0ExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractFun0ExpectationsTest(
    ("toThrow" to Companion::toThrowFeature).withFeatureSuffix(),
    "toThrow" to Companion::toThrow,
    feature0(Expect<() -> Int>::notToThrow),
    feature1(Expect<() -> Int>::notToThrow)
) {
    companion object {
        private fun toThrowFeature(expect: Expect<out () -> Any?>) =
            expect.toThrow<IllegalArgumentException>()

        private fun toThrow(
            expect: Expect<out () -> Any?>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.toThrow<IllegalArgumentException> { assertionCreator() }
    }

    @Suppress("UNUSED_VARIABLE", "unused")
    @Test
    fun ambiguityTest() {
        val a1a: Expect<() -> Any?> = expect { throw IllegalArgumentException("test") }
        val a1b: Expect<out () -> Int> = expect { throw IllegalArgumentException("test") }

        val a2a: Expect<() -> Any?> = expect { "bla" }
        val a2b: Expect<out () -> Int> = expect { 1 }

        val r1: Expect<IllegalArgumentException> = a1a.toThrow<IllegalArgumentException>()
        val r2: Expect<IllegalArgumentException> = a1b.toThrow<IllegalArgumentException>()

        val r3: Expect<IllegalArgumentException> = a1a.toThrow<IllegalArgumentException> { message.toEqual("test") }
        val r4: Expect<IllegalArgumentException> = a1b.toThrow<IllegalArgumentException> { message.toEqual("test") }

        val r5: Expect<Any?> = a2a.notToThrow()
        val r6: Expect<Int> = a2b.notToThrow()

        val r7: Expect<Any?> = a2a.notToThrow { toEqual("bla") }
        val r8: Expect<Int> = a2b.notToThrow { toEqual(1) }
    }
}

