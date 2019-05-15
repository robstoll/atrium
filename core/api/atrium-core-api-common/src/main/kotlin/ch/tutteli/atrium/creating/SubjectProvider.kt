package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Provides the subject of an [Assertion].
 */
interface SubjectProvider<out T> {
    /**
     * The subject of an [Assertion].
     */
    val subject: T
}
