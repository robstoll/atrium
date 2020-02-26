package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import ch.tutteli.atrium.specs.withFeatureSuffix

class ThrowableAssertionsSpec : ch.tutteli.atrium.specs.integration.ThrowableAssertionsSpec(
    property<Throwable, String>(Expect<Throwable>::message),
    fun1<Throwable, Expect<String>.() -> Unit>(Expect<Throwable>::message),
    fun2(Expect<Throwable>::messageContains),
    ("cause" to Companion::causeFeature).withFeatureSuffix(),
    "cause" to Companion::cause
) {

    companion object {

        @Suppress("RemoveExplicitTypeArguments")
        private fun causeFeature(expect: Expect<out Throwable>): Expect<IllegalArgumentException> = expect.cause<IllegalArgumentException>()

        @Suppress("RemoveExplicitTypeArguments")
        private fun cause(expect: Expect<out Throwable>, assertionCreator: Expect<IllegalArgumentException>.() -> Unit) =
            expect.cause<IllegalArgumentException>(assertionCreator)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Throwable> = notImplemented()

        a1.message
        a1 = a1.message {}
        a1 = a1.messageContains("asdf")

        var a2: Expect<ClassCastException> = notImplemented()
        a2.cause<ClassCastException>().message { }
        a2.cause<ClassCastException> {  message {  }}
    }
}
