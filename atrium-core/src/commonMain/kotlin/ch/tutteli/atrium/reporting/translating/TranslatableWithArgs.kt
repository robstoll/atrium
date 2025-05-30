//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.polyfills.format

/**
 * A [Translatable] which contains placeholders for arguments.
 *
 * @property translatable The [Translatable] as such which contains placeholders and
 *   for which translations can be been defined.
 * @property arguments The arguments which should be used to substitute the placeholders of the [translatable].
 *
 * @constructor
 * @param translatable The [Translatable] as such which contains placeholders and
 *   for which translations can be been defined.
 * @param arguments The arguments which should be used to substitute the placeholders of the [translatable].
 *
 */
//TODO 1.3.0 describe how to replace once we introduced Representable and co.
@Deprecated("will be removed with 2.0.0 at the latest")
data class TranslatableWithArgs constructor(val translatable: Translatable, val arguments: List<Any>) : Translatable {

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable] and its one and only [argument].
     *
     * @param translatable A [Translatable] which one placeholder for an [argument].
     * @param argument The argument which will be used to substitute the placeholder of the given [translatable].
     */
    constructor(translatable: Translatable, argument: Any) : this(translatable, createList(argument))

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable], the first argument [arg1] and
     * the second argument [arg2].
     *
     * @param translatable A [Translatable] with an variable number of placeholders (at least one for [arg1]).
     * @param arg1 The argument for the first placeholder of the given [translatable].
     * @param arg2 The argument for the second placeholder of the given [translatable].
     */
    constructor(translatable: Translatable, arg1: Any, arg2: Any)
        : this(translatable, createList(arg1, arg2))

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable], the first argument [arg1],
     * the second argument [arg2] and the [otherArgs].
     *
     * @param translatable A [Translatable] with an variable number of placeholders (at least one for [arg1]).
     * @param arg1 The argument for the first placeholder of the given [translatable].
     * @param arg2 The argument for the second placeholder of the given [translatable].
     * @param otherArgs The remaining arguments for the remaining placeholders of the given [translatable].
     */
    constructor(translatable: Translatable, arg1: Any, arg2: Any, vararg otherArgs: Any)
        : this(translatable, createList(arg1, arg2, otherArgs))

    init {
        require(arguments.isNotEmpty()) {
            "No arguments specified, do not wrap the translatable into an ${TranslatableWithArgs::class.simpleName} if not needed." +
                "\nTranslatable: $translatable"
        }
        require(isArgumentsCountMatchedWithTranslatable()) {
            "The number of given arguments does not match the number of placeholders in [${translatable.getDefault()}]." +
                "\nnumber of arguments = ${arguments.size}, placeholders = ${getPlaceHoldersCount()}" +
                "\nspecify exact number of placeholders before wrapping translatable into an ${TranslatableWithArgs::class.simpleName}."
        }
    }

    private fun isArgumentsCountMatchedWithTranslatable() = arguments.size == getPlaceHoldersCount()

    private fun getPlaceHoldersCount() = translatable.getDefault().count { it == '%' }

    override val name get() = translatable.name
    override fun getDefault() = arguments.map {
        when (it) {
            is Translatable -> it.getDefault()
            else -> it
        }
    }.let { args -> translatable.getDefault().format(args[0], *args.drop(1).toTypedArray()) }

    companion object {

        private fun createList(arg: Any): List<Any> =
            ArrayList<Any>(1).apply { add(arg) }

        private fun createList(arg1: Any, arg2: Any): List<Any> =
            ArrayList<Any>(2).apply {
                add(arg1)
                add(arg2)
            }

        private fun createList(arg1: Any, arg2: Any, otherArgs: Array<out Any>): List<Any> =
            ArrayList<Any>(2 + otherArgs.size).apply {
                add(arg1)
                add(arg2)
                addAll(otherArgs)
            }
    }
}
