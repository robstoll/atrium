package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some

//TODO remove with 0.16.0
/**
 * Provides the subject of an [Assertion].
 *
 * Notice, this interface has its mere purpose to facilitate the transition from [Assert] to [Expect].
 * It might well be that we are going to remove it with 1.0.0 without previous notice.
 * Hence, to be on the safe side, you should use [Expect] instead.
 */
interface SubjectProvider<out T> : AssertionHolder {


    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject change could not be
     * carried out.
     */
    val maybeSubject: Option<T>
}
