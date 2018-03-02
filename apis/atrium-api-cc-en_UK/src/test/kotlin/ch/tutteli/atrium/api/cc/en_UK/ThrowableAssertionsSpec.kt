package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.integration.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getToThrowTriple(): Triple<String, ThrowableThrown.Builder.() -> Unit, ThrowableThrown.Builder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit>
            = Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrown.Builder) {
            builder.toThrow<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrown.Builder, assertionCreator: Assert<Throwable>.() -> Unit) {
            builder.toThrow<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            Assert<Throwable>::message.name to Assert<Throwable>::message

        private fun messageContains(plant: Assert<Throwable>, expected: Any)
            = plant.message { contains(expected) }
    }
}
