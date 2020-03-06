package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.specs.withFeatureSuffix

class ThrowableAssertionsSpec : ch.tutteli.atrium.specs.integration.ThrowableAssertionsSpec(
    property<Throwable, String>(Expect<Throwable>::message),
    fun1<Throwable, Expect<String>.() -> Unit>(Expect<Throwable>::message),
    fun2(::messageContains),
    ("cause" to ::causeFeature).withFeatureSuffix(),
    "cause" to ::cause
) {

    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<ClassCastException> = notImplemented()

        a1.message
        a1 = a1 message {}
        a1 = a1 messageContains "a"
        a1 = a1 messageContains 'a'
        a1 = a1 messageContains Values("a", 1, 'b')

        a1.cause<ClassCastException>()
        a1.cause<ClassCastException> { message { } }
    }
}

private fun messageContains(expect: Expect<Throwable>, expected: Any, vararg otherExpected: Any): Expect<Throwable> =
    if (otherExpected.isEmpty()) expect messageContains expected
    else expect messageContains Values(expected, *otherExpected)

@Suppress("RemoveExplicitTypeArguments")
private fun causeFeature(expect: Expect<out Throwable>): Expect<IllegalArgumentException> =
    expect.cause<IllegalArgumentException>()

@Suppress("RemoveExplicitTypeArguments")
private fun cause(expect: Expect<out Throwable>, assertionCreator: Expect<IllegalArgumentException>.() -> Unit) =
    expect.cause<IllegalArgumentException>(assertionCreator)
