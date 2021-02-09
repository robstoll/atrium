package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.impl.ComponentFactoryContainerImpl
import kotlin.reflect.KClass
import ch.tutteli.atrium.reporting.Reporter

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.LOCAL_VARIABLE)
annotation class ExperimentalComponentFactoryContainer

@ExperimentalComponentFactoryContainer
interface ComponentFactoryContainer {

    /**
     * Returns the component of type [I] using a corresponding factory or `null` in case no factory was found
     * which is able to build a component of the given type.
     *
     * Delegates to [getFactoryOrNull] per default and applies a cast to the result (if not null)
     * to the specified [kClass].
     *
     * @throws ClassCastException in case
     */
    fun <I : Any> buildOrNull(kClass: KClass<I>): I? =
        getFactoryOrNull(kClass)?.let { factory -> safeBuild(factory, kClass) }

    /**
     * Returns a chain of components of type [I] using a corresponding factory or `null` in case no factory was found
     * which is able to build a chain of components of the given type.
     *
     * Delegates to [getFactoryForChainedOrNull] per default and applies a cast to the result (if not null)
     * to the specified [kClass].
     */
    fun <I : Any> buildChainedOrNull(kClass: KClass<I>): Sequence<I>? =
        getFactoryForChainedOrNull(kClass)?.map { factory -> safeBuild(factory, kClass) }

    private fun <I : Any> safeBuild(factory: (ComponentFactoryContainer) -> Any, kClass: KClass<I>): I {
        val component = factory(this)
        return kClass.cast(component)
    }

    /**
     * Returns a factory which is able to build a component for the given [kClass]
     * or `null` in case no factory was found which is able to build a component of the given type.
     */
    fun getFactoryOrNull(kClass: KClass<*>): ((ComponentFactoryContainer) -> Any)?

    /**
     * Returns a factory which is able to build a chain of components of the specified [kClass]
     * or `null` in case no factory was found which is able to build a chain of components of the given type.
     */
    fun getFactoryForChainedOrNull(kClass: KClass<*>): (Sequence<(ComponentFactoryContainer) -> Any>)?


    /**
     * Merges the given [componentFactoryContainer] (if not `null`) with `this` [ComponentFactoryContainer]
     * creating a new [ComponentFactoryContainer] where defined dependencies in [componentFactoryContainer]
     * will have precedence over dependencies defined in this instance.
     *
     * For instance, this object has defined a [Reporter] and
     * the given [componentFactoryContainer] as well, then the resulting [ComponentFactoryContainer] will return the [Reporter]
     * of [componentFactoryContainer] when asked for it.
     */
    fun merge(componentFactoryContainer: ComponentFactoryContainer?): ComponentFactoryContainer

    companion object {
        // should create an immutable ComponentContainer in one go
        fun createIfNotEmpty(
            components: Map<out KClass<*>, (ComponentFactoryContainer) -> Any>,
            chainedComponents: Map<out KClass<*>, Sequence<(ComponentFactoryContainer) -> Any>>
        ): ComponentFactoryContainer? =
            if (components.isEmpty() && chainedComponents.isEmpty()) null
            else ComponentFactoryContainerImpl(
                if (components.isEmpty()) emptyMap() else HashMap(components),
                if (chainedComponents.isEmpty()) emptyMap() else HashMap(chainedComponents)
            )

    }
}

/**
 * Returns the component of type [I] using a corresponding factory or throws an [IllegalStateException]
 * in case no factory was found which is able to build a component of the given type.
 *
 * @throws IllegalStateException in case [ComponentFactoryContainer.buildOrNull] returns `null`
 *   because not suitable factory was found.
 */
@ExperimentalComponentFactoryContainer
inline fun <reified I : Any> ComponentFactoryContainer.build(): I = buildOrNull(I::class)
    ?: throw IllegalStateException("No factory is registered in this ComponentContainer which is able to build a ${I::class.fullName}")


/**
 * Returns a chain of components of type [I] using a corresponding factory or throws an [IllegalStateException]
 * in case no factory was found which is able to build a chain of components of the given type.

 * @throws IllegalStateException in case [ComponentFactoryContainer.buildChainedOrNull] returns `null`
 *   because no suitable factory was found.
 */
@ExperimentalComponentFactoryContainer
inline fun <reified I : Any> ComponentFactoryContainer.buildChained(): Sequence<I> = buildChainedOrNull(I::class)
    ?: throw IllegalStateException("No factory is registered in this ComponentContainer which is able to build a chain of ${I::class.fullName}")
