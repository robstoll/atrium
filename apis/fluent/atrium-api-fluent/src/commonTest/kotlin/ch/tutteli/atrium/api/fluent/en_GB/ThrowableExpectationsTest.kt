package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class ThrowableExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractThrowableExpectationsTest(
    property<Throwable, String>(Expect<Throwable>::message),
    fun1<Throwable, Expect<String>.() -> Unit>(Expect<Throwable>::message),
    fun2(Expect<Throwable>::messageToContain),
    ("cause" to Companion::causeFeature).withFeatureSuffix(),
    "cause" to Companion::cause
) {

    companion object {

        @Suppress("RemoveExplicitTypeArguments")
        private fun causeFeature(expect: Expect<out Throwable>): Expect<IllegalArgumentException> =
            expect.cause<IllegalArgumentException>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun cause(
            expect: Expect<out Throwable>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.cause<IllegalArgumentException>(assertionCreator)

    }

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val cause = IllegalStateException("cause")
        var a1: Expect<IllegalStateException> = expect(IllegalStateException("asdf", cause))

        a1.message
        a1 = a1.message { toEqual("asdf") }
        a1 = a1.messageToContain("asdf")

        a1.cause<IllegalStateException>()
        a1.cause<IllegalStateException> { toEqual(cause) }
    }
}
