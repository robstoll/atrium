package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

class ThrowableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ThrowableAssertionsSpec(
    AssertionVerbFactory,
    getMessageTriple(),
    getMessageContainsPair()
) {
    companion object {

        private fun getNameMessage(): String {
            val messageProp: KProperty<IAssertionPlant<String>> = IAssertionPlant<Throwable>::message
            return messageProp.name
        }

        private fun getMessageTriple() = Triple(
            getNameMessage(),
            Companion::messageProp,
            Companion::messageFun
        )

        private fun messageProp(plant: IAssertionPlant<Throwable>)
            = plant.message

        private fun messageFun(plant: IAssertionPlant<Throwable>, createAssertions: IAssertionPlant<String>.() -> Unit)
            = plant.message(createAssertions)

        private fun getMessageContainsPair() = Companion::messageContains to Companion::messageContainsLazy

        private fun messageContains(plant: IAssertionPlant<Throwable>, expected: Any)
            = plant.message.contains(expected)

        private fun messageContainsLazy(plant: IAssertionPlant<Throwable>, expected: Any)
            = plant.message { contains(expected) }
    }
}
