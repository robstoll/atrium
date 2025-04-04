package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.translations.DescriptionComparableExpectation;

public class ComparableExpectationsSpec extends ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec {
    ComparableExpectationsSpec() {
        super(
            null, //ch.tutteli.atrium.api.fluent.java.ComparableExpectationsSpec.pair("toBeLessThan",  (coreExpect, integer) -> expect(coreExpect).toBeLessThan(integer).getCoreExpect()),
            null,//new Pair<String, Function2<Expect<Integer>, Integer, Expect<Integer>>>("toBeLessThan", (coreExpect, integer) -> expect(coreExpect).toBeLessThan(integer).getCoreExpect()),
            null,//new Pair<String, Function2<Expect<Integer>, Integer, Expect<Integer>>>("toBeLessThan", (coreExpect, integer) -> expect(coreExpect).toBeLessThan(integer).getCoreExpect()),
            null,//new Pair<String, Function2<Expect<Integer>, Integer, Expect<Integer>>>("toBeLessThan", (coreExpect, integer) -> expect(coreExpect).toBeLessThan(integer).getCoreExpect()),
            null,//new Pair<String, Function2<Expect<Integer>, Integer, Expect<Integer>>>("toBeLessThan", (coreExpect, integer) -> expect(coreExpect).toBeLessThan(integer).getCoreExpect()),
           null,//new Pair<String, Function2<Expect<DiffEqualsCompareTo>, DiffEqualsCompareTo, Expect<DiffEqualsCompareTo>>>("toBeLessThan", (coreExpect, integer) ->coreExpect),
           null,//new Pair<String, Function2<Expect<DiffEqualsCompareTo>, DiffEqualsCompareTo, Expect<DiffEqualsCompareTo>>>("toBeLessThan", (coreExpect, integer) ->coreExpect),
           null,//new Pair<String, Function2<Expect<DiffEqualsCompareTo>, DiffEqualsCompareTo, Expect<DiffEqualsCompareTo>>>("toBeLessThan", (coreExpect, integer) ->coreExpect),
            DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault(),
            DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault(),
            "[Atrium]"
        );
    }
}
