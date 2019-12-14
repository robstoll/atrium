package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.creating.Expect

/**
 * Represents a domain for which a given [expect] applies - for instance, if the subject of the [Expect] is of type
 * [Int], then the Domain with type [Int] applies.
 *
 * This can be seen an analogue of [Expect] but not on the API level but on the domain level.
 *
 * Atrium is made up of three levels, API, domain and core. The following outlines the purpose of the three levels
 * (though there are currently more but this shall be simplified with 0.10.0):
 * - the API is user agnostic (style -- fluent vs. infix -- or language -- en_GB vs. de_CH -- etc.)
 * - the domain is only in en_GB and is programming language agnostic (e.g. Kotlin or Scala)
 * - the core is only in en_GB and shall be programming language agnostic, i.e. be usable from Java, Scala etc.
 */
interface Domain<T> {
    /**
     * The expect of the API level.
     */
    val expect: Expect<T>
}
