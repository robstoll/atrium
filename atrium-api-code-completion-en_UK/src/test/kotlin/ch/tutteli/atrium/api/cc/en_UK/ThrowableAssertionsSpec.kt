package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getMessagePair(),
    Companion::messageContains
) {

    companion object {

        private fun getMessagePair() =
            IAssertionPlant<Throwable>::message.name to IAssertionPlant<Throwable>::message

        private fun messageContains(plant: IAssertionPlant<Throwable>, expected: Any)
            = plant.message { contains(expected) }
    }
}
