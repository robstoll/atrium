package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes

/**
 * Additional (non-mandatory) options to create a [RootExpect].
 *
 * See atrium-logic for helper functions to create an instance for this. Core is kept simple on purpose because
 * it is also used in other JVM languages than Kotlin.
 *
 * @property expectationVerb Defines a custom assertion verb if not null.
 * @property representationInsteadOfSubject Defines a custom representation based on a present subject if not null.
 * @property componentFactoryContainer Defines a custom components.
 */
@ExperimentalNewExpectTypes
@OptIn(ExperimentalComponentFactoryContainer::class)
data class RootExpectOptions<T>(
    //TODO 1.3.0 replace with InlineElement or make InlineElement a Translatable instead and provide a second
    // constructor? it's experimental we can also just change it (is most likely not used often
    @Suppress("DEPRECATION")
    val expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable?,
    val representationInsteadOfSubject: ((T) -> Any)?,
    val componentFactoryContainer: ComponentFactoryContainer?
) {

    /**
     * Merges the given [options] with `this` [RootExpectOptions] object creating a new [RootExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfSubject] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [RootExpectOptions] will have the
     * [representationInsteadOfSubject] of [options].
     */
    fun merge(options: RootExpectOptions<T>): RootExpectOptions<T> =
        RootExpectOptions(
            options.expectationVerb ?: expectationVerb,
            options.representationInsteadOfSubject ?: representationInsteadOfSubject,
            options.componentFactoryContainer?.let { c -> componentFactoryContainer?.merge(c) ?: c }
        )
}
