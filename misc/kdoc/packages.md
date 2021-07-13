# Module atrium

## KDoc of Atrium
Atrium is built up by different modules, each available for different platforms.

The following names do not include the platform specific suffixes which are `-common`, `-jvm`, and `-js`. 

Bundle modules:
- **atrium-fluent-en&#95;GB**  defines to have a dependency on   
  atrium-api-fluent-en&#95;GB, atrium-translations-en&#95;GB and atrium-logic
- **atrium-infix-en&#95;GB-robstoll** defines to have a dependency on  
  atrium-api-infix-en&#95;GB, atrium-translations-en&#95;GB and atrium-logic
  
API modules:  
- **atrium-api-fluent-en&#95;GB** provides an expectation function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
  extensions:
  - **atrium-api-fluent-en&#95;GB-kotlin_1_3** provides additional expectation functions for types introduced in Kotlin 1.3 (e.g. for `Result`)
  
- **atrium-api-infix-en&#95;GB** provides an expectation function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is an infix API.
  extensions:
    - **atrium-api-infix-en&#95;GB-kotlin_1_3** provides additional expectation functions for types introduced in Kotlin 1.3 (e.g. for `Result`) 
  
Logic modules:
- **atrium-logic** provides the implementation of expectation functions as well as helper functions for 
  expectation function writers.
  extensions:
     - **atrium-logic-kotlin_1_3** provides the implementation of expectation functions for types introduced in Kotlin 1.3 (e.g. for `Result`)
  
Core modules:
- **atrium-core** defines core contracts of Atrium and contains default implementation.

Translation modules:
- **atrium-translations-de&#95;CH** provides translations in German for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in expectation functions. 
- **atrium-translations-en&#95;GB** provides translations in English for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in expectation functions. 

Misc modules:
- **atrium-specs** contains [Spek](https://spekframework.org/) specifications for interfaces (defined in 
  _atrium-core_) and expectation functions. The specifications can be reused by implementations of the core of Atrium 
  as well as by API implementations to assure they fulfill the specifications.  
- **atrium-verbs** contains out of the box expectation verbs 
- **atrium-verbs-internal** contains the expectation verbs Atrium uses internally - use with care, 
  no backward compatibility guarantees.

# ch.tutteli.atrium.api.fluent.en_GB
Contains API which provides a pure fluent API in English and which has its design focus on usability 
in conjunction with code completion.

# ch.tutteli.atrium.api.fluent.en_GB.creating.feature

Contains the [MetaFeatureOptions](./ch.tutteli.atrium.api.fluent.en_-g-b.creating.feature/-meta-feature-option/index.html) 
which is used `feature`.

# ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3
Contains an API for types introduced with Kotlin 1.3

# ch.tutteli.atrium.api.infix.en_GB
Contains API which provides an infix API in English and which has its design focus on usability 
in conjunction with code completion.

# ch.tutteli.atrium.api.infix.en_GB.creating
Contains parameter objects  .

# ch.tutteli.atrium.api.infix.en_GB.creating.feature
Contains parameter objects related to feature extraction.

# ch.tutteli.atrium.api.infix.en_GB.creating.iterable
Contains parameter objects related to Iterable.

# ch.tutteli.atrium.api.infix.en_GB.creating.map
Contains parameter objects related to Map.

# ch.tutteli.atrium.api.infix.en_GB.creating.path
Contains parameter objects related to Path.

# ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3
Contains an API for types introduced with Kotlin 1.3

# ch.tutteli.atrium.api.infix.en_GB.workaround
Contains functions necessary for the infix API to work due to Kotlin related bugs / or insufficient type 
inference capabilities .

# ch.tutteli.atrium.api.verbs
Contains the out-of-the-box expectation verb [expect](./ch.tutteli.atrium.api.verbs/expect.html).

# ch.tutteli.atrium.assertions
Contains different types of [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html),  
e.g. [DescriptiveAssertion](./ch.tutteli.atrium.assertions/-descriptive-assertion/index.html).
Currently, it contains also `@Deprecated` types which will be made internal with 0.17.0 or earlier

# ch.tutteli.atrium.assertions.builders
The [AssertionBuilder](./ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) 
and other builders to ease the creation of Assertions.

# ch.tutteli.atrium.assertions.builders.common
Package containing/defining common steps for builders. 

# ch.tutteli.atrium.core
Contains the [CoreFactory](./ch.tutteli.atrium.core/-core-factory/index.html).

# ch.tutteli.atrium.core.migration
Contains helper functions which shall ease the migration to a newer version of Atrium. 
For instance, it contains [toAtriumLocale](./ch.tutteli.atrium.core.migration/java.util.-locale/to-atrium-locale.html)
to convert a Java `Locale` to an Atrium specific and platform independent representation of an `Locale`.

# ch.tutteli.atrium.core.polyfills
Contains polyfills for functionality which is missing on certain platforms so that they can be used in a common way.

# ch.tutteli.atrium.creating
Everything involved in creating [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# ch.tutteli.atrium.creating.feature
Contains feature related contracts, such  as [FeatureInfo](./ch.tutteli.atrium.creating.feature/-feature-info/index.html)
which is responsible to determine the description of a feature extraction.

# ch.tutteli.atrium.creating.feature.impl
Contains (default) implementations for feature related contracts.

# ch.tutteli.atrium.creating.impl
Contains (default) implementations for things like [Expect](./ch.tutteli.atrium.creating/-expect/index.html).

# ch.tutteli.atrium.logic
Contains all the assertion interfaces (e.g. [AnyAssertions](./ch.tutteli.atrium.logic/-any-assertions/index.html)
as well as [_logic](./ch.tutteli.atrium.logic/_logic.html) and helper functions for [AssertionContainer](./ch.tutteli.atrium.creating/-assertion-container/index.html)

# ch.tutteli.atrium.logic.assertions.impl
Contains internal implementations of [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html).

# ch.tutteli.atrium.logic.creating.basic.contains
Contains the abstract contract sophisticated `contains` assertion builders: [Contains](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/index.html)

# ch.tutteli.atrium.logic.creating
Contains builders which help in creating different types of [Expect](./ch.tutteli.atrium.creating/-expect/index.html).

# ch.tutteli.atrium.logic.creating.basic.contains.checkers
Contains interfaces implementing [Contains.Checker](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/-checker/index.html)

# ch.tutteli.atrium.logic.creating.basic.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.basic.contains.checkers].

# ch.tutteli.atrium.logic.creating.basic.contains.creators.impl
Contains base classes which can be handy to implement [Contains.Creator](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/-creator/index.html)

# ch.tutteli.atrium.logic.creating.basic.contains.steps.impl
Contains base classes which can be handy to implement steps of the building process.

# ch.tutteli.atrium.logic.creating.charsequence.contains
Contains the contract for sophisticated `CharSequence.contains` assertion builders: [CharSequenceContains](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/index.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.checkers
Contains interfaces implementing [CharSequenceContains.Checker](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-checker.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.checkers].

# ch.tutteli.atrium.logic.creating.charsequence.contains.creators
Contains inter alia [CharSequenceContains.Creator](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-creator.html)s
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.creators].

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours
Contains interfaces implementing [CharSequenceContains.SearchBehaviour](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-search-behaviour.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours].

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl
Contains implementations of [CharSequenceContains.Searcher](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-searcher/index.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.steps
Contains steps for sophisticated `CharSequence.contains` expectation builders.

# ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl
Contains the default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.steps].


# ch.tutteli.atrium.logic.creating.collectors
Contains helper functions to collection [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# ch.tutteli.atrium.logic.creating.feature
Contains interfaces which are used in the building process of feature extractors.

# ch.tutteli.atrium.logic.creating.filesystem
Contains constracts / data structures which help in dealing with file-IO.

# ch.tutteli.atrium.logic.creating.filesystem.hints
Contains helper functions which help in defining failure hints in case your expectation function deals with the file system.

# ch.tutteli.atrium.logic.creating.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating].


# ch.tutteli.atrium.logic.creating.iterable.contains
Contains the contract for sophisticated `IterableLike.contains` expectation builders: 
[IterableLikeContains](./ch.tutteli.atrium.logic.creating.iterable.contains/-iterable-like-contains/index.html)

# ch.tutteli.atrium.logic.creating.iterable.contains.checkers
Contains interfaces implementing [IterableLikeContains.Checker](./ch.tutteli.atrium.logic.creating.iterable.contains/-iterable-like-contains/-checker.html)

# ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.iterable.contains.checkers].

# ch.tutteli.atrium.logic.creating.iterable.contains.creators
Contains inter alia [IterableLikeContains.Creator](./ch.tutteli.atrium.logic.creating.iterable.contains/-iterable-like-contains/-creator.html)s
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.iterable.contains.creators].

# ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours
Contains interfaces implementing [IterableLikeContains.SearchBehaviour](./ch.tutteli.atrium.logic.creating.iterable.contains/-iterable-like-contains/-search-behaviour.html)

# ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours].

# ch.tutteli.atrium.logic.creating.iterable.contains.steps
Contains steps for sophisticated `IterableLike.contains` expectation builders.

# ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl
Contains the default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.iterable.contains.steps].

# ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting
Reporting related interfaces for `IterableLike.contains`.


# ch.tutteli.atrium.logic.creating.maplike.contains
Contains the contract for sophisticated `MapLike.contains` expectation builders:
[MapLikeContains](./ch.tutteli.atrium.logic.creating.maplike.contains/-map-like-contains/index.html)

# ch.tutteli.atrium.logic.creating.maplike.contains.checkers
Contains interfaces implementing [MapLikeContains.Checker](./ch.tutteli.atrium.logic.creating.maplike.contains/-map-like-contains/-checker.html)

# ch.tutteli.atrium.logic.creating.maplike.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.maplike.contains.checkers].

# ch.tutteli.atrium.logic.creating.maplike.contains.creators
Contains inter alia [MapLikeContains.Creator](./ch.tutteli.atrium.logic.creating.maplike.contains/-map-like-contains/-creator.html)s
as well as the minimum set of functions which are intended to be used as part of the final step of the building process.

# ch.tutteli.atrium.logic.creating.maplike.contains.creators.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.maplike.contains.creators].

# ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours
Contains interfaces implementing [MapLikeContains.SearchBehaviour](./ch.tutteli.atrium.logic.creating.maplike.contains/-map-like-contains/-search-behaviour.html)

# ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours].

# ch.tutteli.atrium.logic.creating.maplike.contains.steps
Contains steps for sophisticated `MaplikeLike.contains` expectation builders.

# ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl
Contains the default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.maplike.contains.steps].


# ch.tutteli.atrium.logic.creating.transformers
Contains contracts involved in transforming [Expect](./ch.tutteli.atrium.creating/-expect/index.html), their subject respectively.

# ch.tutteli.atrium.logic.creating.transformers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.transformers].

# ch.tutteli.atrium.logic.creating.transformers.impl.featureextractor
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.transformers] specific to feature extraction.

# ch.tutteli.atrium.logic.creating.transformers.impl.subjectchanger
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.transformers] specific to subject changing.


# ch.tutteli.atrium.logic.creating.typeutils
Contains type alias used in Atrium to better describe the intent behind the types.

# ch.tutteli.atrium.logic.creating.typeutils.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.typeutils.impl] specific to subject changing.

# ch.tutteli.atrium.logic.impl
Contains default implementations of the assertion interfaces defined in [ch.tutteli.atrium.logic].

# ch.tutteli.atrium.logic.kotlin_1_3
Contains all the assertion interfaces for the Kotlin 1.3 extension (e.g. [ResultAssertions](./ch.tutteli.atrium.logic.kotlin_1_3/-result-assertions/index.html)

# ch.tutteli.atrium.logic.kotlin_1_3.impl
Contains default implementations of the assertion interfaces defined in [ch.tutteli.atrium.logic.kotlin_1_3].


# ch.tutteli.atrium.logic.utils
Contains inter alia the [mapArgument](./ch.tutteli.atrium.logic.utils/map-arguments.html) function next to other
helper functions such as [nullable](./ch.tutteli.atrium.logic.utils/nullable.html) and co.

# ch.tutteli.atrium.reporting
Everything involved in reporting [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# ch.tutteli.atrium.reporting.text
Text specific, in other words terminal specific, reporting contracts.

# ch.tutteli.atrium.reporting.text.impl
Contains default implementations of the interfaces defined in [ch.tutteli.atrium.reporting.text].

# ch.tutteli.atrium.reporting.impl
Contains default implementations of the interfaces defined in [ch.tutteli.atrium.reporting].

# ch.tutteli.atrium.reporting.erroradjusters
Contains marker interfaces for different kinds of [AtriumErroAdjuster](./ch.tutteli.atrium.reporting/-atrium-error-adjuster/index.html)s.

# ch.tutteli.atrium.reporting.erroradjusters.impl
Contains default implementations of the interfaces defined in [ch.tutteli.atrium.reporting.erroradjusters].


# ch.tutteli.atrium.reporting.translating
Everything involved in translating [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.

# ch.tutteli.atrium.reporting.translating.impl
Contains default implementations of the interfaces defined in [ch.tutteli.atrium.reporting.translating].


# ch.tutteli.atrium.translations
Contains [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.
