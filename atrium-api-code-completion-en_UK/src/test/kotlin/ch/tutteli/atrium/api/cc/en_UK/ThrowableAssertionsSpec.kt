package ch.tutteli.atrium.api.cc.en_UK

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

        private fun getToThrowTriple(): Triple<String, ThrowableThrownBuilder.() -> Unit, ThrowableThrownBuilder.(createAssertions: IAssertionPlant<Throwable>.() -> Unit) -> Unit>
            = Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrownBuilder) {
            builder.toThrow<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrownBuilder, createAssertions: IAssertionPlant<Throwable>.() -> Unit) {
            builder.toThrow<IllegalArgumentException>(createAssertions)
        }

        private fun getMessagePair() =
            IAssertionPlant<Throwable>::message.name to IAssertionPlant<Throwable>::message

        private fun messageContains(plant: IAssertionPlant<Throwable>, expected: Any)
            = plant.message { contains(expected) }
    }
}
