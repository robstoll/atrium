package ch.tutteli.atrium.logic.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ComponentFactory
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import kotlin.reflect.KClass

@ExperimentalComponentFactoryContainer
@ExperimentalNewExpectTypes
class RootExpectOptionsChooserImpl<T> : RootExpectBuilder.OptionsChooser<T> {
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    private var description: ch.tutteli.atrium.reporting.translating.Translatable? = null
    private var representationInsteadOfSubject: ((T) -> Any)? = null
    private var components = mutableMapOf<KClass<*>, ComponentFactory>()
    private var chainedComponents = mutableMapOf<KClass<*>, Sequence<ComponentFactory>>()

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun withVerb(verb: ch.tutteli.atrium.reporting.translating.Translatable) {
        this.description = verb
    }

    override fun withRepresentation(representationProvider: (T) -> Any) {
        this.representationInsteadOfSubject = representationProvider
    }

    @ExperimentalComponentFactoryContainer
    override fun <I : Any> withComponent(kClass: KClass<I>, factory: (ComponentFactoryContainer) -> I) {
        components[kClass] = ComponentFactory(factory, producesSingleton = false)
    }

    @ExperimentalComponentFactoryContainer
    override fun <I : Any> withSingletonComponent(kClass: KClass<I>, factory: (ComponentFactoryContainer) -> I) {
        components[kClass] = ComponentFactory(factory, producesSingleton = true)
    }

    @ExperimentalComponentFactoryContainer
    override fun <I : Any> prependChainedComponents(kClass: KClass<I>, factories: Sequence<ComponentFactory>) {
        chainedComponents[kClass] = factories
    }

    fun build(): RootExpectOptions<T> =
        RootExpectOptions(
            description,
            representationInsteadOfSubject,
            ComponentFactoryContainer.createIfNotEmpty(components, chainedComponents)
        )


}
