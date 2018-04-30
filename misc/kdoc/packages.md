# Module atrium

## KDoc of Atrium
Atrium is built up by different modules. The packages shown below contain classes etc. 
of all modules excluding the one of _atrium-core-robstoll_ and _atrium-core-robstoll-lib_.

Bundle modules:
- **atrium-cc-de&#95;CH-robstoll** defines to have a dependency on 
  atrium-api-cc-de&#95;CH, atrium-translations-de&#95;CH, atrium-domain-robstoll and atrium-core-robstoll
- **atrium-cc-en&#95;GB-robstoll**  defines to have a dependency on   
  atrium-api-cc-en&#95;GB, atrium-translations-en&#95;UK, atrium-domain-robstoll and and atrium-core-robstoll
- **atrium-cc-infix-en&#95;GB-robstoll**  defines to have a dependency on  
  atrium-api-cc-infix-en&#95;GB, atrium-translations-en&#95;UK, atrium-domain-robstoll and and atrium-core-robstoll
  
API modules:  
- **atrium-api-cc-de&#95;CH** provides an assertion function API in German where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
- **atrium-api-cc-en&#95;GB** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
- **atrium-api-cc-infix-en&#95;GB** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is an infix API.  
  
Domain modules:
- **atrium-domain-api** defines the contracts of the domain of Atrium 
  (contracts for impl-functions and sophisticated assertion builders). 
- **atrium-domain-robstoll** provides the different domain services like 
  [CharSequenceAssertions](./ch.tutteli.atrium.domain.creating/-char-sequence-assertions/index.html)  
  which use the implementations of *atrium-domain-robstoll-lib*
- **atrium-domain-robstoll-lib** [robstoll](https://github.com/robstoll)'s implementation of the domain of Atrium.
- **atrium-domain-builders** contains the [ReporterBuilder](./ch.tutteli.atrium.domain.builders.reporting/-reporter-builder/index.html),
  provides extension functions for [AssertionBuilder](./ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html)
  and last but not least provides base classes of sophisticated assertion builders for the API modules.

Core modules:
- **atrium-core-api** defines the contracts of the core of Atrium.

- **atrium-core-robstoll** provides an implementation for [CoreFactory](./ch.tutteli.atrium.core/-core-factory/index.html)
  which uses the implementations of *atrium-core-robstoll-lib*
- **atrium-core-robstoll-lib** [robstoll](https://github.com/robstoll)'s implementation of the core of Atrium.

Translation modules:
- **atrium-translations-de&#95;CH** provides translations in German for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 
- **atrium-translations-en&#95;UK** provides translations in English for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 

Misc modules:
- **atrium-spec** contains [Spek](http://spekframework.org/) specifications for interfaces (defined in 
  _atrium-core-api_) and assertion functions. The specifications can be reused by implementation of the core of Atrium 
  as well as by API implementations to assure they fulfill the specifications.  
- **atrium-verbs** contains out of the box assertion verbs (we suggest to [use your own assertion verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs)).

Deprecated modules:
- **atrium-cc-en&#95;UK-robstoll** use `atrium-cc-en_GB-robstoll` instead. This bundle defines to have a dependency on   
  atrium-api-cc-en&#95;UK, atrium-translations-en&#95;UK, atrium-domain-robstoll, atrium-core-robstoll and some deprecated modules
- **atrium-cc-infix-en&#95;UK-robstoll** use `atrium-cc-infix-en_GB-robstoll` instead. This bundle defines to have a dependency on  
  atrium-api-cc-infix-en&#95;UK, atrium-translations-en&#95;UK, atrium-domain-robstoll, atrium-core-robstoll and some deprecated modules
- **atrium-api-cc-en&#95;UK** use `atrium-api-cc-en_GB` instead.
- **atrium-api-cc-infix-en&#95;UK** use `atrium-api-cc-infix-en_GB` instead.
  
- **atrium-domain-api-deprecated** contains deprecated domain-api code
- **atrium-core-robstoll-deprecated** contains the deprecated AtriumFactory => use [coreFactory](./ch.tutteli.atrium.core/core-factory.html) instead
- **atrium-assertions** is `deprecated` and you should not longer rely on it. 
  It will be removed with 1.0.0. 
  Use the replacements suggested in the `@Deprecated` annotations.

Atrium has currently several modules to retain backward compatibility. 
You should not rely on them on and move to the suggested predecessor:
- 

# Package ch.tutteli.atrium
Contains the deprecated [IAtriumFactory](./ch.tutteli.atrium/-i-atrium-factory/index.html).


# Package ch.tutteli.atrium.api.cc.de_CH
Contains API which provides a pure fluent API in German and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.

# Package ch.tutteli.atrium.api.cc.en_UK
`@Depreacted` API use  [ch.tutteli.atrium.api.cc.en_GB] instead.

# Package ch.tutteli.atrium.api.cc.en_GB
Contains API which provides a pure fluent API in English and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders
Contains `@Deprecated` builders, use the builders from package `en_GB`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders
Contains `@Deprecated` builders, use the builders from package `en_GB`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.


# Package ch.tutteli.atrium.api.cc.infix.en_UK
`@Depreacted` API use  [ch.tutteli.atrium.api.cc.infix.en_GB] instead.
# Package ch.tutteli.atrium.api.cc.infix.en_GB
Contains API which provides an infix API in English and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders
Contains `@Deprecated` builders, use the builders from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders
Contains `@Deprecated` builders, use the builders from package `en_GB`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders
Contains `@Deprecated` builders, use the builders from package `en_GB`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.

# Package ch.tutteli.atrium.api.cc.infix.en_GB.keywords
Contains pseudo keywords such as [order](./ch.tutteli.atrium.api.cc.infix.en_-g-b.keywords/order.html).


# Package ch.tutteli.atrium.assertions
Contains different types of [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html),  
e.g. [DescriptiveAssertion](./ch.tutteli.atrium.assertions/-descriptive-assertion/index.html).
Currently it contains also `@Deprecated` impl-functions which will be removed with 1.0.0

# Package ch.tutteli.atrium.assertions.any.typetransformation
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.basic.contains
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.basic.contains.builders
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.basic.contains.checkers
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.basic.contains.creators
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.assertions.builders
The [AssertionBuilder](./ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) 
and other builders to ease the creation of Assertions.

# Package ch.tutteli.atrium.assertions.charsequence.contains
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.charsequence.contains.builders
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.charsequence.contains.checkers
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.charsequence.contains.creators
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.charsequence.contains.searchers
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0

# Package ch.tutteli.atrium.assertions.iterable.contains
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.iterable.contains.builders
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.iterable.contains.checkers
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.iterable.contains.creators
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.throwable.thrown
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.throwable.thrown.builders
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.throwable.thrown.creators
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0
# Package ch.tutteli.atrium.assertions.throwable.thrown.providers
Contains `@Deprecated` classes/interfaces/functions, use the ones from package `creating`, will all be removed with 1.0.0



# Package ch.tutteli.atrium.checking
Everything involved in checking [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# Package ch.tutteli.atrium.core
Contains the [CoreFactory](./ch.tutteli.atrium.core/-core-factory/index.html).

# Package ch.tutteli.atrium.creating
Everything involved in creating [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.


# Package ch.tutteli.atrium.domain.assertions.composers
The [AssertionComposer](./ch.tutteli.atrium.domain.assertions.composers/-assertion-composer/index.html) 
which delegates to an implementation which composes different assertions to new [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.


# Package ch.tutteli.atrium.domain.builders
Contains [AssertImpl](./ch.tutteli.atrium.domain.builders/index.html).

# Package ch.tutteli.atrium.domain.builders.assertions.builders
Contains extension functions for the [AssertionBuilder](./ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html).

# Package ch.tutteli.atrium.domain.builders.creating 
Builders involved in creating [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# Package ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders
Contains base classes for [CharSequenceContains.CheckerOption](./ch.tutteli.atrium.domain.creating.charsequence.contains/-char-sequence-contains/-checker-option.html).

# Package ch.tutteli.atrium.domain.builders.creating.collectors
Contains the builder behind [AssertImpl.collector](./ch.tutteli.atrium.domain.builders.creating.collectors/-assertion-collector-builder/index.html)

# Package ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders
Contains base classes for [IterableContains.CheckerOption](./ch.tutteli.atrium.domain.creating.iterable.contains/-iterable-contains/-checker-option.html).

# Package ch.tutteli.atrium.domain.builders.reporting
Contains the [ReporterBuilder](./ch.tutteli.atrium.domain.builders.reporting/-reporter-builder/index.html).

# Package ch.tutteli.atrium.domain.builders.utils
Contains utility functions for APIs.



# Package ch.tutteli.atrium.domain.creating
Contains interfaces defining the minimum set of assertion functions (on level domain) which an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.any.typetransformation
Contains inter alia the contract for the type transformation assertion builders -- [AnyTypeTransformation](./ch.tutteli.atrium.domain.creating.any.typetransformation/-any-type-transformation/index.html).

# Package ch.tutteli.atrium.domain.creating.any.typetransformation.creators
Contains [AnyTypeTransformationAssertions](./ch.tutteli.atrium.domain.creating.any.typetransformation.creators/-any-type-transformation-assertions/index.html)
which defines the minimum set of type transformation assertion functions (on level domain) an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers
Contains the contract for failure handlers involved in failed type transformations as well as the
[FailureHandlerFactory](./ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers/-failure-handler-factory/index.html)
which defines the minimum set of [AnyTypeTransformation.FailureHandler](ch.tutteli.atrium.domain.creating.any.typetransformation/-any-type-transformation/-failure-handler/index.html) 
an implementation has to provide.


# Package ch.tutteli.atrium.domain.creating.basic.contains
Contains the basic contract for contains assertion builders 
-- [Contains](./ch.tutteli.atrium.domain.creating.basic.contains/-contains/index.html).


# Package ch.tutteli.atrium.domain.creating.charsequence.contains
Contains the contract for sophisticated CharSequence `contains` assertions 
-- [CharSequenceContains](./ch.tutteli.atrium.domain.creating.charsequence.contains/-char-sequence-contains/index.html)

# Package ch.tutteli.atrium.domain.creating.charsequence.contains.checkers
Contains [CheckerFactory](./ch.tutteli.atrium.domain.creating.charsequence.contains.checkers/-checker-factory/index.html) 
which defines the minimum set of [CharSequenceContains.Checker](./ch.tutteli.atrium.domain.creating.charsequence.contains/-char-sequence-contains/-checker.html)
an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.charsequence.contains.creators
Contains [CharSequenceContainsAssertions](./ch.tutteli.atrium.domain.creating.charsequence.contains.creators/-char-sequence-contains-assertions/index.html)
which defines the minimum set of `CharSequence contains` assertion functions (on level domain) an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours
Contains [SearchBehaviourFactory](./ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours/-search-behaviour-factory/index.html)
which defines the minimum set of [CharSequenceContains.SearchBehaviour](./ch.tutteli.atrium.domain.creating.charsequence.contains/-char-sequence-contains/-search-behaviour.html)
an implementation has to provide.


# Package ch.tutteli.atrium.domain.creating.collectors
Contains [AssertionCollector](./ch.tutteli.atrium.domain.creating.collectors/-assertion-collector/index.html). 


# Package ch.tutteli.atrium.domain.creating.iterable.contains
Contains the contract for sophisticated Iterable `contains` assertions 
-- [IterableContains](./ch.tutteli.atrium.domain.creating.iterable.contains/-iterable-contains/index.html)

# Package ch.tutteli.atrium.domain.creating.iterable.contains.checkers
Contains [CheckerFactory](./ch.tutteli.atrium.domain.creating.iterable.contains.checkers/-checker-factory/index.html) 
which defines the minimum set of [IterableContains.Checker](./ch.tutteli.atrium.domain.creating.iterable.contains/-iterable-contains/-checker.html)
an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.iterable.contains.creators
Contains [IterableContainsAssertions](./ch.tutteli.atrium.domain.creating.iterable.contains.creators/-iterable-contains-assertions/index.html)
which defines the minimum set of `Iterable contains` assertion functions (on level domain) an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours
Contains [SearchBehaviourFactory](./ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours/-search-behaviour-factory/index.html)
which defines the minimum set of [IterableContains.SearchBehaviour](./ch.tutteli.atrium.domain.creating.iterable.contains/-iterable-contains/-search-behaviour.html)
an implementation has to provide.


# Package ch.tutteli.atrium.domain.creating.throwable.thrown
Contains the contract for sophisticated a Throwable was thrown assertions 
-- [ThrowableThrown](./ch.tutteli.atrium.domain.creating.throwable.thrown/-throwable-thrown/index.html)

# Package ch.tutteli.atrium.domain.creating.throwable.thrown.creators
Contains [IterableContainsAssertions](./ch.tutteli.atrium.domain.creating.throwable.thrown.creators/-throwable-thrown-assertions/index.html)
which defines the minimum set of `a Throwable was thrown` assertion functions (on level domain) an implementation has to provide.

# Package ch.tutteli.atrium.domain.creating.throwable.thrown.providers
Contains [AbsentThrowableMessageProviderFactory](./ch.tutteli.atrium.domain.creating.throwable.thrown.providers/-absent-throwable-message-provider-factory/index.html)
which defines the minimum set of [IterableContains.AbsentThrowableMessageProvider](./ch.tutteli.atrium.domain.creating.throwable.thrown/-throwable-thrown/-absent-throwable-message-provider/index.html)
an implementation has to provide.


# Package ch.tutteli.atrium.reporting
Everything involved in reporting [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html)s.

# Package ch.tutteli.atrium.reporting.translating
Everything involved in translating [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.


# Package ch.tutteli.atrium.spec
Contains inter alia [DeprecationTestEngine](ch.tutteli.atrium.spec/-deprecation-test-engine/index.html)
which is used in backward compatibility tests.


# Package ch.tutteli.atrium.translations/index.html
Contains [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.


# Package ch.tutteli.atrium.verbs
Contains the  [out-of-the-box Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs)
such as [assert](ch.tutteli.atrium.verbs/assert.html), [assertThat](ch.tutteli.atrium.verbs/assert-that.html) and
[expect](ch.tutteli.atrium.verbs/expect.html).
 
# Package ch.tutteli.atrium.verbs.assert
The `@Deprecated` version of the assertion verb `assert`, will be removed with 1.0.0

# Package ch.tutteli.atrium.verbs.assertthat
The `@Deprecated` version of the assertion verb `assertThat`, will be removed with 1.0.0

# Package ch.tutteli.atrium.verbs.expect
The `@Deprecated` version of the assertion verb `expect`, will be removed with 1.0.0