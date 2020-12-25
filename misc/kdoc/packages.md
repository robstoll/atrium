# Module atrium

## KDoc of Atrium
Atrium is built up by different modules, each available for different platforms.

The following names do not include the platform specific suffixes which are `-common`, `-jvm`, and `-js`. 

Bundle modules:
- **atrium-fluent-en&#95;GB**  defines to have a dependency on   
  atrium-api-fluent-en&#95;GB, atrium-translations-en&#95;GB, atrium-logic and atrium-core-robstoll
- **atrium-infix-en&#95;GB-robstoll** defines to have a dependency on  
  atrium-api-infix-en&#95;GB, atrium-translations-en&#95;GB, atrium-logic and atrium-core-robstoll
  
API modules:  
- **atrium-api-fluent-en&#95;GB** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
  extensions:
  - **atrium-api-fluent-en&#95;GB-kotlin_1_3** provides additional assertion functions for types introduced in Kotlin 1.3 (e.g. for `Result`)
  
- **atrium-api-infix-en&#95;GB** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is an infix API.
  extensions:
    - **atrium-api-infix-en&#95;GB-kotlin_1_3** provides additional assertion functions for types introduced in Kotlin 1.3 (e.g. for `Result`) 
  
Logic modules:
- **atrium-logic** provides the implementation of assertion functions as well as helper functions for 
  assertion writers.
  extensions:
     - **atrium-logic-kotlin_1_3** provides the implementation of assertion functions for types introduced in Kotlin 1.3 (e.g. for `Result`)
  
Core modules:
- **atrium-core-api** defines the contracts of the core of Atrium.

- **atrium-core-robstoll** provides an implementation for 
  [CoreFactory](./ch.tutteli.atrium.core/-core-factory/index.html) which uses the implementations 
  of *atrium-core-robstoll-lib*
- **atrium-core-robstoll-lib** [robstoll](https://github.com/robstoll)'s implementation of the core of Atrium.

Translation modules:
- **atrium-translations-de&#95;CH** provides translations in German for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 
- **atrium-translations-en&#95;GB** provides translations in English for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 

Misc modules:
- **atrium-specs** contains [Spek](https://spekframework.org/) specifications for interfaces (defined in 
  _atrium-core-api_) and assertion functions. The specifications can be reused by implementation of the core of Atrium 
  as well as by API implementations to assure they fulfill the specifications.  
- **atrium-verbs** contains out of the box assertion verbs 
- **atrium-verbs-internal** contains the assertion verbs Atrium uses internally - use with care, 
  no backward compatibility guarantees.

Atrium has currently several modules to retain backward compatibility. 
You should not rely on them and move to the suggested predecessor: 
- **atrium-domain-api** use `atrium-logic` instead. 
- **atrium-domain-robstoll**  use `atrium-logic` instead. 
- **atrium-domain-robstoll-lib**  use `atrium-logic` instead. 
- **atrium-domain-builders** use `atrium-logic` instead.   

# ch.tutteli.atrium.api.fluent.en_GB
Contains API which provides a pure fluent API in English and which has its design focus on usability 
in conjunction with code completion.

# ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3
Contains an API for types introduced with Kotlin 1.3

# ch.tutteli.atrium.api.infix.en_GB
Contains API which provides an infix API in English and which has its design focus on usability 
in conjunction with code completion.

# ch.tutteli.atrium.api.infix.en_GB.creating
Contains parameter objects  .

# ch.tutteli.atrium.api.infix.en_GB.creating.feature
Contains parameter objects related to feature assertions.

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
Contains the  [out-of-the-box Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs)
such as [expect](./ch.tutteli.atrium.api.verbs/expect.html), [assert](./ch.tutteli.atrium.api.verbs/assert.html)
and [assertThat](./ch.tutteli.atrium.api.verbs/assert-that.html).

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

# ch.tutteli.atrium.creating.impl
Contains implementations which are involved in creating [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# ch.tutteli.atrium.domain.assertions.composers
The [AssertionComposer](./ch.tutteli.atrium.domain.assertions.composers/-assertion-composer/index.html) 
which delegates to an implementation which composes different assertions to new 
[Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.


# ch.tutteli.atrium.domain.builders
Contains [ExpectImpl](./ch.tutteli.atrium.domain.builders/-expect-impl/index.html).

# ch.tutteli.atrium.domain.builders.assertions.builders
Contains extension functions for the 
[AssertionBuilder](./ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html).

# ch.tutteli.atrium.domain.builders.creating
Builders involved in delegating to assertion implementations.

# ch.tutteli.atrium.domain.builders.creating.collectors
Contains the builder behind 
[ExpectImpl.collector](./ch.tutteli.atrium.domain.builders.creating.collectors/-assertion-collector-builder/index.html)

# ch.tutteli.atrium.domain.builders.reporting
Contains the [ReporterBuilder](./ch.tutteli.atrium.domain.builders.reporting/-reporter-builder/index.html).

# ch.tutteli.atrium.domain.builders.utils
Contains utility functions for APIs.



# ch.tutteli.atrium.domain.creating
Contains interfaces defining the minimum set of assertion functions (on level domain) which an implementation has to provide.

# ch.tutteli.atrium.domain.creating.collectors
Contains [AssertionCollector](./ch.tutteli.atrium.domain.creating.collectors/-assertion-collector/index.html). 

# ch.tutteli.atrium.logic
Contains all the assertion interfaces (e.g. [AnyAssertions](./ch.tutteli.atrium.logic/-any-assertions/index.html)
as well as [_logic](./ch.tutteli.atrium.logic/_logic.html) and helper functions for [AssertionContainer](./ch.tutteli.atrium.creating/-assertion-container/index.html)

# ch.tutteli.atrium.logic.creating.basic.contains
Contains the abstract contract sophisticated `contains` assertion builders: [Contains](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/index.html)

# ch.tutteli.atrium.logic.creating.basic.contains.checkers
Contains interfaces implementing [Contains.Checker](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/-checker/index.html)

# ch.tutteli.atrium.logic.creating.basic.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.basic.contains.checkers].

# ch.tutteli.atrium.logic.creating.basic.contains.creators.impl
Contains base classes which can be handy to implement [Contains.Creator](./ch.tutteli.atrium.logic.creating.basic.contains/-contains/-creator/index.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains
Contains the contract for sophisticated `CharSequence.contains` assertion builders: [CharSequenceContains](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/index.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.checkers
Contains interfaces implementing [CharSequence.Checker](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-checker.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.checkers].

# ch.tutteli.atrium.logic.creating.charsequence.contains.creators
Contains [CharSequenceContainsAssertions](./ch.tutteli.atrium.logic.creating.charsequence.contains.creators/-char-sequence-contains-assertions/index.html) 
which defines the minimum set of [CharSequence.Creator](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-creator.html)s 
an implementation has to deliver as well as some extension functions which delegate to those.

# ch.tutteli.atrium.logic.creating.charsequence.contains.creators.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.creators].

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours
Contains interfaces implementing [CharSequence.SearchBehaviour](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-search-behaviour.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl
Contains default implementations for the interfaces defined in [ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours].

# ch.tutteli.atrium.logic.creating.charsequence.contains.searchers.impl
Contains implementations of [CharSequence.Searcher](./ch.tutteli.atrium.logic.creating.charsequence.contains/-char-sequence-contains/-searcher/index.html)

# ch.tutteli.atrium.logic.creating.charsequence.contains.steps
Contains steps for sophisticated `CharSequence.contains` assertion builders.

# ch.tutteli.atrium.logic.impl
Contains default implementations of the assertion interfaces defined in [ch.tutteli.atrium.logic].

# ch.tutteli.atrium.logic.kotlin_1_3
Contains all the assertion interfaces for the Kotlin 1.3 extension (e.g. [ResultAssertions](./ch.tutteli.atrium.logic.kotlin_1_3/-result-assertions/index.html)

# ch.tutteli.atrium.logic.kotlin_1_3.impl
Contains default implementations of the assertion interfaces defined in [ch.tutteli.atrium.logic.kotlin_1_3].

# ch.tutteli.atrium.reporting
Everything involved in reporting [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# ch.tutteli.atrium.reporting.translating
Everything involved in translating [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.

# ch.tutteli.atrium.translations
Contains [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.
