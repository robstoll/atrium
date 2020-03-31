@file:Suppress("ClassName")

package ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3

import ch.tutteli.atrium.api.infix.en_GB.Keyword
import ch.tutteli.atrium.api.infix.en_GB.toBe

interface Keyword

/**
 * Represents the pseudo keyword `success` as in [toBe] `success`
 * It can be used for a parameter less function so that it has one parameter and thus can be used as infix function
 */
object success : Keyword
