package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

class ThrowableAssertionsJvmSpec : ch.tutteli.atrium.specs.integration.ThrowableAssertionsJvmSpec(
    getToThrowTriple(),
    getNotThrownPair(),
    "⚬ ", "» "
) {

    companion object {

        private fun getToThrowTriple(): Triple<String, ThrowableThrown.Builder.() -> Unit, ThrowableThrown.Builder.(assertionCreator: Expect<IllegalArgumentException>.() -> Unit) -> Unit> =
            Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrown.Builder) {
            builder.toThrow<IllegalArgumentException>()
        }

        private fun toThrowLazy(
            builder: ThrowableThrown.Builder,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) {
            builder.toThrow(assertionCreator)
        }

        private fun getNotThrownPair() = ThrowableThrown.Builder::notToThrow.name to ThrowableThrown.Builder::notToThrow
    }
}
