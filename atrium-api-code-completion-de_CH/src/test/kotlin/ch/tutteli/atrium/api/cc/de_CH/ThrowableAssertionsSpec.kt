package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getToThrowTriple() = Triple("wirft", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrownBuilder) {
            builder.wirft<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrownBuilder, assertionCreator: IAssertionPlant<Throwable>.() -> Unit) {
            builder.wirft<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            IAssertionPlant<Throwable>::message.name to IAssertionPlant<Throwable>::message

        private fun messageContains(plant: IAssertionPlant<Throwable>, expected: Any)
            = plant.message { enthaelt(expected) }
    }
}
