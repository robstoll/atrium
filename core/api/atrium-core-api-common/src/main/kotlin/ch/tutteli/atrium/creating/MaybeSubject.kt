package ch.tutteli.atrium.creating

/**
 * Represents a subject of an assertion which might be [Present] or [Absent].
 *
 * It provides a method [get] where one can get the underlying subject (if [Present]).
 **/
sealed class MaybeSubject<out T> {

    /**
     * Returns the underlying subject or throws a [PlantHasNoSubjectException] if it is [Absent].
     */
    abstract fun get(): T

    /**
     * Represents an absent subject.
     */
    object Absent: MaybeSubject<Nothing>(){
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
    data class Present<T>(val subject: T): MaybeSubject<T>(){
        /**
         * Returns the [subject].
         */
        override fun get(): T = subject
    }

    companion object {
        operator fun <T: Any> invoke(subject: T?) =
            if (subject == null) Absent
            else Present(subject)
    }
}
