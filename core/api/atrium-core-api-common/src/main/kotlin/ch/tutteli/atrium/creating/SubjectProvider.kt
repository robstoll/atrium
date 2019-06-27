package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Provides the subject of an [Assertion].
 *
 * Notice, this interface has its mere purpose to facilitate the transition from [Assert] to [Expect].
 * It might well be that we are going to remove it with 1.0.0 without previous notice.
 * Hence, to be on the safe side, you should use [Expect] instead.
 */
interface SubjectProvider<out T> {
    /**
     * The subject of an [Assertion].
     */
    val subject: T
}
