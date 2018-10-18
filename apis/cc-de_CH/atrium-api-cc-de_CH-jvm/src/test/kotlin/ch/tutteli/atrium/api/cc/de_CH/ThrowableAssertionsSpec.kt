package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.integration.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageWithContainsFun,
    getMessageContainsPair()
) {

    companion object {

        private fun getToThrowTriple() = Triple("wirft", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrown.Builder) {
            @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
            builder.wirft<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrown.Builder, assertionCreator: Assert<Throwable>.() -> Unit) {
            builder.wirft<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            Assert<Throwable>::message.name to Assert<Throwable>::message

        private fun messageWithContainsFun(plant: Assert<Throwable>, expected: Any)
            = plant.message { enthaelt(expected) }

        private fun getMessageContainsPair()
            = Assert<Throwable>::messageEnthaelt.name to Companion::messageContains

        private fun messageContains(plant: Assert<Throwable>, expected: Any, otherExpected: Array<out Any>)
            = plant.messageEnthaelt(expected, *otherExpected)
    }
}
