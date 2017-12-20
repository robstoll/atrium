package ch.tutteli.atrium.reporting.translating

/**
 * Something which is translatable, identified by [id] with a default representation given by [getDefault].
 */
interface Translatable {
    /**
     * Returns the default representation of this [Translatable].
     *
     * @return The default representation of this [Translatable].
     */
    fun getDefault(): String

    /**
     * The name of this [Translatable] -- the name together with its [Class.name] should identify a [Translatable] (see [id]).
     */
    val name: String

    /**
     * The id of this [Translatable] -- per default it is "[Class.name][ID_SEPARATOR][name]"
     */
    val id: String get() = this::class.java.name + ID_SEPARATOR + name

    companion object {
        /**
         * The separator used in [id] to separate [Class.name] and [name].
         */
        const val ID_SEPARATOR = "-"
    }
}
