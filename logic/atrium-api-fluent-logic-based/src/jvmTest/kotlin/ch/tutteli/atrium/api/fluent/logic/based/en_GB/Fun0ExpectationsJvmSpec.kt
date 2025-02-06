package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.withFeatureSuffix

class Fun0ExpectationsJvmSpec : ch.tutteli.atrium.specs.integration.Fun0ExpectationsJvmSpec(
    ("toThrow" to Companion::toThrowFeature).withFeatureSuffix(),
    "toThrow" to Companion::toThrow,
    feature0<() -> Int, Int>(Expect<() -> Int>::notToThrow),
    feature1<() -> Int, Expect<Int>.() -> Unit, Int>(Expect<() -> Int>::notToThrow)
) {

    companion object {
        fun toThrowFeature(expect: Expect<out () -> Any?>) = expect.toThrow<IllegalArgumentException>()
        fun toThrow(expect: Expect<out () -> Any?>, assertionCreator: Expect<IllegalArgumentException>.() -> Unit) =
            expect.toThrow<IllegalArgumentException> { assertionCreator() }
    }
}
