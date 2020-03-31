@file:Suppress("ClassName")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

/**
 * Marker interface for keywords.
 *
 * For instance, can be used to add a deprecated overload in case an assertion function accepts Any as argument type
 * (see toBe).
 */
interface Keyword

//TODO not used yet, should be used though
internal const val ERR_KEYWORD_GIVEN_COLLECTION_ASSUMED =
    "This call will most probably fail at runtime because the given subject is not a collection as you might have assumed. If you really want to compare the subject against the keyword, then cast the keyword to Any"

/**
 * Represents a helper construct which allows to express emptiness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Empty : Keyword

/**
 * Represents a helper construct which allows to express blankness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Blank : Keyword

/**
 * Represents the pseudo keyword `contain` as in [to] `contain`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object contain : Keyword

/**
 * Represents the pseudo keyword `case` as in [ignoring] `case`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object case : Keyword

/**
 * Represents the pseudo keyword `entries` as in [grouped] `entries`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object entries : Keyword

/**
 * Represents the pseudo keyword `group` as in [within] `group`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object group : Keyword

/**
 * Represents the pseudo keyword `not` as in [contains] `not`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 *
 * @since 0.11.0
 */
object not : Keyword

/**
 * Represents a filler, a pseudo keyword where there isn't really a good keyword.
 * A reader should skip this filler without reading it. For instance, `contains o atLeast 1...` should be read as
 * `contains at least once...`
 *
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 *
 * @since 0.11.0
 */
object o : Keyword

/**
 * Represents the pseudo keyword `only` as in [and] `only`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object only : Keyword

/**
 * Represents the pseudo keyword `order` as in [inAny] `order`.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object order : Keyword

/**
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 *
 * @since 0.11.0
 */
object success : Keyword
