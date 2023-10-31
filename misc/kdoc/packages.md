# Module atrium-api-fluent

Provides an expectation function API in a pure fluent style.

The main focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.

Every expectation function starts with `to..`, feature extractors such as `Expect<List<...>>.get(...)` do not start 
with `to..` where mixtures of feature extractor and expectation function such as `toThrow` are exceptions.

Expectation functions which you use often, will usually have a shortcut function, right at the fingertips after the 
expectation verb (e.g. `Expect<IterableLike>.toContainExactly(...)`) whereas functionality which are not that often use
are behind a sophisticated builder such as `Expect<IterableLike>.toContain` and offer all available functions. For 
instance: `Expect<IterableLik>.toContain.inOrder.only.grouped.within.inAnyOrder(...)`

# Module atrium-api-infix

Provides an expectation function API in English in an infix style.

The main focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.

Every expectation function starts with `to..`, feature extractors such as `Expect<List<...>>.get(...)` do not start
with `to..` where mixtures of feature extractor and expectation function such as `toThrow` are exceptions.

Expectation functions which you use often, will usually have a shortcut function, right at the fingertips after the
expectation verb (e.g. `Expect<IterableLike> toContainExactly ...`) whereas functionality which are not that often use
are behind a sophisticated builder such as `Expect<IterableLike> toContain o` and offer all available functions. For
instance: `Expect<IterableLik> toContain o inGiven order and only grouped entries within group inAny order(...)`

# Module atrium-core

Defines core contracts of Atrium and contains default implementation.

# Module atrium-logic

Provides the implementation of expectation functions as well as helper functions for
expectation function writers.

# Module atrium-verbs
Contains the predefined expectation verb `expect` and `expectGrouped`,  
see <a href="https://github.com/robstoll/atrium#use-own-expectation-verb">Use own expectation verb</a> 
if you want to define your own.


<!-- ---------------------------------------------------------------------------------------------------- -->
<!-- ---------------------------------------------------------------------------------------------------- -->


# Package ch.tutteli.atrium.api.fluent.en_GB
Contains the API which provides a pure fluent API in English and which has its design focus on usability
in conjunction with code completion.

# Package ch.tutteli.atrium.api.fluent.en_GB.creating.feature

Contains helper classes/functions etc. in conjunction with feature extractors.

<!--  Infix API  ------------------------------------------------------------------------------------------------ -->

# Package ch.tutteli.atrium.api.infix.en_GB
Contains API which provides an infix API in English and which has its design focus on usability
in conjunction with code completion.

# Package ch.tutteli.atrium.api.infix.en_GB.creating
Contains parameter objects which help out in case Atrium requires more than one argument.

# Package ch.tutteli.atrium.api.infix.en_GB.creating.feature
Contains parameter objects related to feature extraction.

# Package ch.tutteli.atrium.api.infix.en_GB.creating.iterable
Contains parameter objects related to `Iterable`.

# Package ch.tutteli.atrium.api.infix.en_GB.creating.map
Contains parameter objects related to `Map`.

# Package ch.tutteli.atrium.api.infix.en_GB.creating.path
Contains parameter objects related to `Path`.

# Package ch.tutteli.atrium.api.infix.en_GB.workaround
Contains functions necessary for the infix API to work properly due to Kotlin related bugs / or insufficient type
inference capabilities.

<!--  core ------------------------------------------------------------------------------------------------ -->

# Package ch.tutteli.atrium.assertions
Contains different types of `Assertions`.

# Package ch.tutteli.atrium.assertions.builders
The AssertionBuilder and other builders to ease the creation of `Assertion`s.

# Package ch.tutteli.atrium.assertions.builders.common
Package containing/defining common steps for builders.

# Package ch.tutteli.atrium.assertions.builders.impl.representationOnly
Contains (default) implementations for the `RepresentationOnlyAssertion`.


# Package ch.tutteli.atrium.core
Contains `Option` and `Either`.

# Package ch.tutteli.atrium.core.polyfills
Contains polyfills for functionality which is missing on certain platforms so that they can be used in a common way.


# Package ch.tutteli.atrium.creating
Everything involved in creating `Assertion`s.

# Package ch.tutteli.atrium.creating.feature
Contains feature related contracts, such as `FeatureInfo` which is responsible to determine the description of a feature extraction.

# Package ch.tutteli.atrium.creating.feature.impl
Contains (default) implementations for feature related contracts.

# Package ch.tutteli.atrium.creating.impl
Contains (default) implementations for things like `Expect`.


# Package ch.tutteli.atrium.reporting
Everything involved in reporting Assertions.

# Package ch.tutteli.atrium.reporting.erroradjusters
Contains marker interfaces for different kinds of `AtriumErrorAdjuster`s.

# Package ch.tutteli.atrium.reporting.erroradjusters.impl
Contains (default) implementations of the interfaces defined in `ch.tutteli.atrium.reporting.erroradjusters`.

# Package ch.tutteli.atrium.reporting.impl
Contains (default) implementations of the interfaces defined in `ch.tutteli.atrium.reporting`.

# Package ch.tutteli.atrium.reporting.text
Text specific, in other words terminal specific, reporting contracts.

# Package ch.tutteli.atrium.reporting.text.impl
Contains (default) implementations of the interfaces defined in `ch.tutteli.atrium.reporting.text`.

# Package ch.tutteli.atrium.reporting.translating
Everything involved in translating `Translatable`s.

# Package ch.tutteli.atrium.reporting.translating.impl
Contains (default) implementations of the interfaces defined in `ch.tutteli.atrium.reporting.translating`.


<!--  logic ------------------------------------------------------------------------------------------------ -->


# Package ch.tutteli.atrium.logic
Contains all the assertion interfaces (e.g. `AnyAssertions`)
as well as `_logic` and helper functions for `AssertionContainer`.

# Package ch.tutteli.atrium.logic.assertions.impl
Contains internal implementations of `Assertion`s.


# Package ch.tutteli.atrium.logic.creating
Contains builders which help in creating different types of `Expect`.

# Package ch.tutteli.atrium.logic.creating.basic.contains
Contains the abstract contract sophisticated `toContain` assertion builders follow.

# Package ch.tutteli.atrium.logic.creating.basic.contains.checkers
Contains interfaces implementing `Contains.Checker`.

# Package ch.tutteli.atrium.logic.creating.basic.contains.checkers.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.basic.contains.checkers`.

# Package ch.tutteli.atrium.logic.creating.basic.contains.creators.impl
Contains base classes which can be handy to implement `Contains.Creator`.

# Package ch.tutteli.atrium.logic.creating.basic.contains.steps.impl
Contains base classes which can be handy to implement steps of the building process.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains
Contains the contract for sophisticated `CharSequence.toContain` assertion builders.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers
Contains interfaces implementing `CharSequenceContains.Checker`

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl
Contains (default) implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.checkers].

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.creators
Contains inter alia `CharSequenceContains.Creator`s
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.charsequence.contains.creators`.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours
Contains interfaces implementing `CharSequenceContains.SearchBehaviour`.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours`.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl
Contains implementations of `CharSequenceContains.Searcher`.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.steps
Contains steps for sophisticated `CharSequence.toContain` expectation builders.

# Package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl
Contains the default implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.charsequence.contains.steps`.


# Package ch.tutteli.atrium.logic.creating.collectors
Contains helper functions to collect `Assertion`s.

# Package ch.tutteli.atrium.logic.creating.feature
Contains interfaces which are used in the building process of feature extractors.

# Package ch.tutteli.atrium.logic.creating.filesystem
Contains contracts / data structures which help in dealing with file-IO.

# Package ch.tutteli.atrium.logic.creating.filesystem.hints
Contains helper functions which help in defining failure hints in case your expectation function deals with the file system.

# Package ch.tutteli.atrium.logic.creating.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating`.


# Package ch.tutteli.atrium.logic.creating.iterable.contains
Contains the contract for sophisticated `IterableLike.toContain` assertion builders.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.checkers
Contains interfaces implementing `IterableLikeContains.Checker`.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.iterable.contains.checkers`.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.creators
Contains inter alia `IterableLikeContains.Creator`s
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.iterable.contains.creators`.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours
Contains interfaces implementing `IterableLikeContains.SearchBehaviour`.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours`.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.steps
Contains steps for sophisticated `IterableLike.contains` expectation builders.

# Package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl
Contains the default implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.iterable.contains.steps`.

# Package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting
Reporting related interfaces for `IterableLike.contains`.


# Package ch.tutteli.atrium.logic.creating.maplike.contains
Contains the contract for sophisticated `MapLike.toContain` expectation builders.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.checkers
Contains interfaces implementing `MapLikeContains.Checker`.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.checkers.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.maplike.contains.checkers`.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.creators
Contains inter alia `MapLikeContains.Creator`.
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.creators.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.maplike.contains.creators`.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours
Contains interfaces implementing `MapLikeContains.SearchBehaviour`.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours`.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.steps
Contains steps for sophisticated `MaplikeLike.contains` expectation builders.

# Package ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl
Contains the default implementations for the interfaces defined in `ch.tutteli.atrium.logic.creating.maplike.contains.steps`.


# Package ch.tutteli.atrium.logic.creating.transformers
Contains contracts involved in transforming [Expect](ch.tutteli.atrium.creating.Expect), their subject respectively.

# Package ch.tutteli.atrium.logic.creating.transformers.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.transformers`.

# Package ch.tutteli.atrium.logic.creating.transformers.impl.featureextractor
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.transformers` specific to feature extraction.

# Package ch.tutteli.atrium.logic.creating.transformers.impl.subjectchanger
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.transformers` specific to subject changing.


# Package ch.tutteli.atrium.logic.creating.typeutils
Contains type alias used in Atrium to better describe the intent behind the types.

# Package ch.tutteli.atrium.logic.creating.typeutils.impl
Contains (default) implementations for the interfaces defined in `ch.tutteli.atrium.logic.typeutils.impl`.

# Package ch.tutteli.atrium.logic.impl
Contains (default) implementations of the assertion interfaces defined in `ch.tutteli.atrium.logic`.

# Package ch.tutteli.atrium.logic.utils
Contains inter alia the `mapArgument` function next to other helper functions such as `nullable` and co.

# Package ch.tutteli.atrium.api.verbs
Contains the out-of-the-box expectation verb `expect` and `expectGrouped`.
