@file:Suppress("ClassName")
package ch.tutteli.atrium.api.cc.infix.en_GB.keywords

import ch.tutteli.atrium.api.cc.infix.en_GB.and
import ch.tutteli.atrium.api.cc.infix.en_GB.grouped
import ch.tutteli.atrium.api.cc.infix.en_GB.ignoring
import ch.tutteli.atrium.api.cc.infix.en_GB.inAny
import ch.tutteli.atrium.api.cc.infix.en_GB.to
import ch.tutteli.atrium.api.cc.infix.en_GB.within

/**
 * Represents a helper construct which allows to express emptiness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Empty

/**
 * Represents a helper construct which allows to express blankness.
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function.
 */
object Blank

/**
 * Represents the pseudo keyword `contain` as in [to] `contain`.
 */
object contain

/**
 * Represents the pseudo keyword `case` as in [ignoring] `case`.
 */
object case

/**
 * Represents the pseudo keyword `entries` as in [grouped] `entries`.
 */
object entries

/**
 * Represents the pseudo keyword `group` as in [within] `group`.
 */
object group

/**
 * Represents the pseudo keyword `only` as in [and] `only`.
 */
object only

/**
 * Represents the pseudo keyword `order` as in [inAny] `order`.
 */
object order

