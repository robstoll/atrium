package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.RootExpectOptions
import kotlin.reflect.KClass

/**
 * Helper function to create a [RootExpectOptions] via
 */
@ExperimentalNewExpectTypes
@Suppress("FunctionName")
fun <T> RootExpectOptions(configuration: RootExpectBuilder.OptionsChooser<T>.() -> Unit): RootExpectOptions<T> =
    RootExpectBuilder.OptionsChooser(configuration)

/**
 * Convenience function which infers the [KClass] usually required for [RootExpectBuilder.OptionsChooser.withComponent].
 */
@ExperimentalComponentFactoryContainer
// TODO 1.3.0 remove
@Deprecated("will be removed with Atrium 1.3.0, use the overload which requires a KClass -- this doesn't realy help as it infers a concrete type instead of an interface",
    ReplaceWith("withComponent(I::class, factory)")
)
inline fun <reified I : Any, T> RootExpectBuilder.OptionsChooser<T>.withComponent(
    noinline factory: (ComponentFactoryContainer) -> I
) = withComponent(I::class, factory)
