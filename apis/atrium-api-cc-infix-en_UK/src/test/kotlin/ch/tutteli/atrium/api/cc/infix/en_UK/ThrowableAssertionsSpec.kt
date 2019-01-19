@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.integration.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getToThrowTriple(),
    getNotThrownPair(),
    getMessagePair(),
    Companion::messageWithContainsFun,
    getMessageContainsPair(),
    "⚬ ", "» "
) {

    companion object {

        private fun getToThrowTriple(): Triple<String, ThrowableThrown.Builder.() -> Unit, ThrowableThrown.Builder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit> =
            Triple("toThrow", Companion::toThrowImmediate, Companion::toThrowLazy)

        private fun toThrowImmediate(builder: ThrowableThrown.Builder) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>{}
        }

        private fun toThrowLazy(builder: ThrowableThrown.Builder, assertionCreator: Assert<Throwable>.() -> Unit) {
            //TODO change to infix as soon as https://youtrack.jetbrains.com/issue/KT-21593 is fixed
            builder.toThrow<IllegalArgumentException>(assertionCreator)
        }

        private fun getNotThrownPair()
            = "does not exist in in this API, using AssertImpl.throwable.thrown.nothingThrown" to Companion::notToThrow

        private fun notToThrow(builder: ThrowableThrown.Builder)
            = AssertImpl.throwable.thrown.nothingThrown(builder)

        private fun getMessagePair()
            = Assert<Throwable>::message.name to Assert<Throwable>::message

        private fun messageWithContainsFun(plant: Assert<Throwable>, expected: Any)
            = plant message { contains(expected) }

        private fun getMessageContainsPair()
            = "does not exist in in this API, using message { this contains ... }" to Companion::messageContains


        private fun messageContains(plant: Assert<Throwable>, expected: Any, otherExpected: Array<out Any>) {
            return if (otherExpected.isEmpty()) {
                plant message { this contains expected }
            } else {
                plant message { this contains Values(expected, *otherExpected) }
            }
        }
    }
}
