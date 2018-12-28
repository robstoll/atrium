@file:Suppress("ClassName")
package ch.tutteli.atrium.api.cc.infix.en_GB.keywords

import ch.tutteli.atrium.api.cc.infix.en_GB.and
import ch.tutteli.atrium.api.cc.infix.en_GB.grouped
import ch.tutteli.atrium.api.cc.infix.en_GB.ignoring
import ch.tutteli.atrium.api.cc.infix.en_GB.inAny
import ch.tutteli.atrium.api.cc.infix.en_GB.to
import ch.tutteli.atrium.api.cc.infix.en_GB.within

/**
 * Marker interface for keywords.
 *
 * For instance, can be used to add a deprecated overload in case an assertion function accepts Any as argument type
 * (see toBe).
 */
interface Keyword
internal const val ERR_KEYWORD_GIVEN_COLLECTION_ASSUMED = "This call will most probably fail at runtime because the given subject is not a collection as you might have assumed. If you really want to compare the subject against the keyword, then cast the keyword to Any"

/**
 * Represents a helper construct which allows to express emptiness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Empty: Keyword

/**
 * Represents a helper construct which allows to express blankness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Blank: Keyword

/**
 * Represents the pseudo keyword `contain` as in [to] `contain`.
 */
object contain: Keyword

/**
 * Represents the pseudo keyword `case` as in [ignoring] `case`.
 */
object case: Keyword

/**
 * Represents the pseudo keyword `entries` as in [grouped] `entries`.
 */
object entries: Keyword

/**
 * Represents the pseudo keyword `group` as in [within] `group`.
 */
object group: Keyword

/**
 * Represents the pseudo keyword `only` as in [and] `only`.
 */
object only: Keyword

/**
 * Represents the pseudo keyword `order` as in [inAny] `order`.
 */
object order: Keyword

