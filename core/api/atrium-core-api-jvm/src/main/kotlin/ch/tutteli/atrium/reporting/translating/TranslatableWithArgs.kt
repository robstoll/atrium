package ch.tutteli.atrium.reporting.translating

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
actual class TranslatableWithArgs actual constructor(actual val translatable: Translatable, actual val arguments: Array<Any>) : Translatable {

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable] and its one and only [argument].
     *
     * @param translatable A [Translatable] which one placeholder for an [argument].
     * @param argument The argument which will be used to substitute the placeholder of the given [translatable].
     */
    actual constructor(translatable: Translatable, argument: Any) : this(translatable, arrayOf(argument))

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable], the first argument [arg1] and the [otherArguments].
     *
     * @param translatable A [Translatable] with an variable number of placeholders (at least one for [arg1]).
     * @param arg1 The argument for the first placeholder of the given [translatable].
     * @param otherArguments The remaining arguments for the remaining placeholders of the given [translatable].
     */
    actual constructor(translatable: Translatable, arg1: Any, vararg otherArguments: Any) : this(translatable, arrayOf(arg1, *otherArguments))

    override val name get() = translatable.name
    override val id get() = translatable::class.qualifiedName + name
    override fun getDefault() = String.format(translatable.getDefault(), *arguments)
}
