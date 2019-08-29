package ch.tutteli.atrium.creating

/**
 * Represents a subject of an assertion which might be [Present] or [Absent].
 *
 * It provides a method [get] where one can get the underlying subject (if [Present]).
 **/
@Deprecated("Use something like ch.tutteli.atrium.core.Option instead; will be removed with 1.0.0")
sealed class MaybeSubject<out T> {

    /**
     * Returns the underlying subject or throws a [PlantHasNoSubjectException] if it is [Absent].
     */
    abstract fun get(): T

    /**
     * Represents an absent subject.
     */
    @Suppress("DEPRECATION")
    @Deprecated("Use something like ch.tutteli.atrium.core.None instead; will be removed with 1.0.0")
    object Absent : MaybeSubject<Nothing>() {
        /**
         * Throws a [PlantHasNoSubjectException].
         * @throws PlantHasNoSubjectException if this method is called.
         */
        override fun get(): Nothing = throw PlantHasNoSubjectException()
    }

    /**
     * Represents a present subject.
     *
     * @property subject The underlying subject.
     */
    @Suppress("DEPRECATION")
    @Deprecated("Use something like ch.tutteli.atrium.core.Some instead; will be removed with 1.0.0")
    data class Present<T>(val subject: T) : MaybeSubject<T>() {
        /**
         * Returns the [subject].
         */
        override fun get(): T = subject
    }

    companion object {
        @Suppress("DEPRECATION")
        operator fun <T : Any> invoke(subject: T?) =
            if (subject == null) Absent
            else Present(subject)
    }
}
