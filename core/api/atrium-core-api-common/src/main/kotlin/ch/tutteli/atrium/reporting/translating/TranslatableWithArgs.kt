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
expect class TranslatableWithArgs constructor(translatable: Translatable, arguments: Array<Any>) : Translatable {
    val translatable: Translatable
    val arguments: Array<Any>

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable] and its one and only [argument].
     *
     * @param translatable A [Translatable] which one placeholder for an [argument].
     * @param argument The argument which will be used to substitute the placeholder of the given [translatable].
     */
    constructor(translatable: Translatable, argument: Any)

    /**
     * Creates a [TranslatableWithArgs] with the given [translatable], the first argument [arg1] and the [otherArguments].
     *
     * @param translatable A [Translatable] with an variable number of placeholders (at least one for [arg1]).
     * @param arg1 The argument for the first placeholder of the given [translatable].
     * @param otherArguments The remaining arguments for the remaining placeholders of the given [translatable].
     */
    constructor(translatable: Translatable, arg1: Any, vararg otherArguments: Any)
}
