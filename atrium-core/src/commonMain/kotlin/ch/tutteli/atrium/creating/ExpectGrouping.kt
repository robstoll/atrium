package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion


/**
 * The internal type which we work with which provides methods we don't want to show to the user.
 *
 * We separate [Expect] from [AssertionContainer] so that we can provide extension functions for
 * [AssertionContainer] which are more or less identical to the ones defined for api-fluent but don't return an [Expect]
 * but [Assertion] etc.
 *
 * Also, we separate [Expect] form [AssertionContainer] since a lot of functionality defined for AssertionContainer is
 * not relevant for newcomers to Atrium (see [https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas](https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas)
 * for more information about the personas).
 */
interface ExpectGroupingInternal : ExpectGrouping, ExpectCollector

/**
 * Represents a collector of expectation groups providing a function [evaluate] which triggers evaluation of the
 * collected groups including throwing an [AssertionError] if one of them does not hold.
 *
 * See also [ExpectGrouping]
 *
 * @since 1.1.0
 */
interface ExpectCollector {

    fun append(assertion: Assertion): ExpectGrouping
    fun appendAsGroup(assertions: List<Assertion>): ExpectGrouping

    /**
     * Evaluates the collected expectation groups and throws an [AssertionError] in case one of the expectation groups
     * expectation do not hold.
     *
     * @since 1.1.0
     * @return Again an ExpectGroupingCollector
     */
    fun evaluate()
}



/**
 * Represents a group of expectations including nested groups of expectations (nested [ExpectGrouping]).
 *
 * It's the extension point for groups of expectations with unrelated subjects.
 *
 * @since 1.1.0
 */
interface ExpectGrouping



interface ExpectGroupingCollector : ExpectGrouping {

}
