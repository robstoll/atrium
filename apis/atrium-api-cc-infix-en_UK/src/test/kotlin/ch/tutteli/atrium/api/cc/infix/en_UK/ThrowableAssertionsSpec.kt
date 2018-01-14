package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.Assert

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getToThrowTriple(): Triple<String, ThrowableThrownBuilder.() -> Unit, ThrowableThrownBuilder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit>
            = Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrownBuilder) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>()
        }

        private fun toThrowLazy(builder: ThrowableThrownBuilder, assertionCreator: Assert<Throwable>.() -> Unit) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>(assertionCreator)
        }

        private fun getMessagePair() =
            Assert<Throwable>::message.name to Assert<Throwable>::message

        private fun messageContains(plant: Assert<Throwable>, expected: Any)
            = plant message { contains(expected) }
    }
}
