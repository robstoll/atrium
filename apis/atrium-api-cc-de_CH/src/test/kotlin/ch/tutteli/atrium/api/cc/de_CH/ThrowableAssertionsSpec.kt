package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.integration.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getToThrowTriple() = Triple("wirft", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrown.Builder) {
            builder.wirft<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrown.Builder, assertionCreator: Assert<Throwable>.() -> Unit) {
            builder.wirft<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            Assert<Throwable>::message.name to Assert<Throwable>::message

        private fun messageContains(plant: Assert<Throwable>, expected: Any)
            = plant.message { enthaelt(expected) }
    }
}
