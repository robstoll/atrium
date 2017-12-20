package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.AssertionPlant

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getToThrowTriple(): Triple<String, ThrowableThrownBuilder.() -> Unit, ThrowableThrownBuilder.(assertionCreator: AssertionPlant<Throwable>.() -> Unit) -> Unit>
            = Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrownBuilder) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrownBuilder, assertionCreator: AssertionPlant<Throwable>.() -> Unit) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            AssertionPlant<Throwable>::message.name to AssertionPlant<Throwable>::message

        private fun messageContains(plant: AssertionPlant<Throwable>, expected: Any)
            = plant message { contains(expected) }
    }
}
