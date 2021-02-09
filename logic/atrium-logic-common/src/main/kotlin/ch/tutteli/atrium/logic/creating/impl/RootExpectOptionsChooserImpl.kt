package ch.tutteli.atrium.logic.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@ExperimentalComponentFactoryContainer
@ExperimentalNewExpectTypes
class RootExpectOptionsChooserImpl<T> : RootExpectBuilder.OptionsChooser<T> {
    private var description: Translatable? = null
    private var representationInsteadOfSubject: ((T) -> Any)? = null
    private var components = mutableMapOf<KClass<*>, (ComponentFactoryContainer) -> Any>()
    private var chainedComponents = mutableMapOf<KClass<*>, Sequence<(ComponentFactoryContainer) -> Any>>()

    override fun withVerb(verb: Translatable) {
        this.description = verb
    }

    override fun withRepresentation(representationProvider: (T) -> Any) {
        this.representationInsteadOfSubject = representationProvider
    }

    @ExperimentalComponentFactoryContainer
    override fun <I : Any> withComponent(kClass: KClass<I>, factory: (ComponentFactoryContainer) -> I) {
        components[kClass] = factory
    }

    @ExperimentalComponentFactoryContainer
    override fun <I : Any> withChainedComponents(kClass: KClass<I>, factories: Sequence<(ComponentFactoryContainer) -> I>) {
        chainedComponents[kClass] = factories
    }

    fun build(): RootExpectOptions<T> =
        RootExpectOptions(description, representationInsteadOfSubject, ComponentFactoryContainer.createIfNotEmpty(components, chainedComponents))
}
