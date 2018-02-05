# Module atrium

## KDoc of Atrium
Atrium is built up by different modules. The packages shown below contain classes etc. 
of all modules excluding the one of _atrium-core-robstoll_ and _atrium-core-robstoll-lib_.

Bundle modules:
- **atrium-cc-de&#95;CH-robstoll** defines to have a dependency on 
  atrium-api-cc-de&#95;CH, atrium-translations-de&#95;CH, atrium-domain-robstoll and atrium-core-robstoll
- **atrium-cc-en&#95;UK-robstoll**  defines to have a dependency on   
  atrium-api-cc-en&#95;UK, atrium-translations-en&#95;UK, atrium-domain-robstoll and and atrium-core-robstoll
- **atrium-cc-infix-en&#95;UK-robstoll**  defines to have a dependency on  
  atrium-api-cc-infix-en&#95;UK, atrium-translations-en&#95;UK, atrium-domain-robstoll and and atrium-core-robstoll
  
API modules:  
- **atrium-api-cc-de&#95;CH** provides an assertion function API in German where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
- **atrium-api-cc-en&#95;UK** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is a pure fluent API.
- **atrium-api-cc-infix-en&#95;UK** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  It is an infix API.  
  
Assertion modules:
- **atrium-assertions** contains all [impl-functions](https://github.com/robstoll/atrium#api-in-a-different-language)
  and builder templates which can be used to create an API. Moreover it contains the 
  [ReporterBuilder](./ch.tutteli.atrium.reporting/-reporter-builder/index.html).
- **atrium-translations-de&#95;CH** provides translations in German for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 
- **atrium-translations-en&#95;UK** provides translations in English for 
  [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s used in assertion functions. 
- **atrium-verbs** contains out of the box assertion verbs (we suggest to [use your own assertion verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs)).

Core modules:
- **atrium-core-api** defines the contracts of the core of Atrium.
- **atrium-core-api-late-binding** defines the contract for 
  [CoreFactory](./ch.tutteli.atrium/-core-factory/index.html)
  by providing a dummy implementation which eventually needs to be replaced by a real implementation. 
- **atrium-core-robstoll** provides a [CoreFactory](./ch.tutteli.atrium/-core-factory/index.html)
  which uses the implementations of *atrium-core-robstoll-lib*
- **atrium-core-robstoll-lib** [robstoll](https://github.com/robstoll)'s implementation of the core of Atrium.

Misc modules:
- **atrium-spec** contains [Spek](http://spekframework.org/) specifications for interfaces (defined in 
  _atrium-core-api_) and assertion functions. The specifications can be reused by implementation of the core of Atrium 
  as well as by API implementations to assure they fulfill the specifications.  

# Package ch.tutteli.atrium
Contains the [ICoreFactory](./ch.tutteli.atrium/-i-core-factory/index.html).


# Package ch.tutteli.atrium.api.cc.de_CH
Contains API which provides a pure fluent API in German and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.


# Package ch.tutteli.atrium.api.cc.en_UK
Contains API which provides a pure fluent API in English and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.


# Package ch.tutteli.atrium.api.cc.infix.en_UK
Contains API which provides an infix API in English and which has its design focus on usability in conjunction with code completion.

# Package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.

# Package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for Iterable.

# Package ch.tutteli.atrium.assertions
Contains different types of [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html) --  
e.g. [BasicDescriptiveAssertion](./ch.tutteli.atrium.assertions/-basic-descriptive-assertion/index.html) -- 
as well as assertion function templates which can be used in API modules such as 
[ch.tutteli.atrium.api.cc.en_UK].

# Package ch.tutteli.atrium.assertions.any.narrow
Contains inter alia the contract for narrowing assertion builders -- [AnyNarrow](./ch.tutteli.atrium.assertions.any.narrow/-any-narrow/index.html).

# Package ch.tutteli.atrium.assertions.any.narrow.failurehandler
Contains [DownCastFailureHandler](./ch.tutteli.atrium.assertions.any.narrow/-any-narrow/-down-cast-failure-handler/index.html)s.


# Package ch.tutteli.atrium.assertions.basic.contains
Contains the basic contract for contains assertion builders 
-- [Contains](./ch.tutteli.atrium.assertions.basic.contains/-contains/index.html).

# Package ch.tutteli.atrium.assertions.basic.contains.builders
Contains base classes for builders representing a step in the process of creating sophisticated `contains` assertions.

# Package ch.tutteli.atrium.assertions.basic.contains.checkers
Contains base classes for [Contains.Checker](./ch.tutteli.atrium.assertions.basic.contains/-contains/-checker/index.html).

# Package ch.tutteli.atrium.assertions.basic.contains.creators
Contains base classes for [Contains.Creators](./ch.tutteli.atrium.assertions.basic.contains/-contains/-creator/index.html).


# Package ch.tutteli.atrium.assertions.charsequence.contains
Contains the contract for sophisticated CharSequence `contains` assertions 
-- [CharSequenceContains](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains/index.html)

# Package ch.tutteli.atrium.assertions.charsequence.contains.builders
Contains base classes for fluent API builders used in assertion function APIs 
(e.g. [ch.tutteli.atrium.api.cc.infix.en_UK]).

# Package ch.tutteli.atrium.assertions.charsequence.contains.checkers
Contains [CharSequenceContains.Checker](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains/-checker.html)s.

# Package ch.tutteli.atrium.assertions.charsequence.contains.creators
Contains [CharSequenceContains.Creator](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains/-creator.html)s.

# Package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours
Contains [CharSequenceContains.SearchBehaviour](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains/-search-behaviour.html).

# Package ch.tutteli.atrium.assertions.charsequence.contains.searchers
Contains [CharSequenceContains.Searcher](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains/-searcher/index.html)s.


# Package ch.tutteli.atrium.assertions.iterable.contains
Contains the contract for sophisticated Iterable `contains` assertions 
-- [IterableContains](./ch.tutteli.atrium.assertions.iterable.contains/-iterable-contains/index.html)

# Package ch.tutteli.atrium.assertions.iterable.contains.builders
Contains base classes for fluent API builders used in assertion function APIs 
(e.g. [ch.tutteli.atrium.api.cc.en_UK]).

# Package ch.tutteli.atrium.assertions.iterable.contains.checkers
Contains [IterableContains.Checker](./ch.tutteli.atrium.assertions.iterable.contains/-iterable-contains/-checker.html)s.

# Package ch.tutteli.atrium.assertions.iterable.contains.creators
Contains [IterableContains.Creator](./ch.tutteli.atrium.assertions.iterable.contains/-iterable-contains/-creator.html)s.

# Package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours
Contains [IterableContains.SearchBehaviour](./ch.tutteli.atrium.assertions.iterable.contains/-iterable-contains/-search-behaviour.html).


# Package ch.tutteli.atrium.assertions.throwable.thrown
Contains the contract for sophisticated a Throwable was thrown assertions 
-- [ThrowableThrown](./ch.tutteli.atrium.assertions.throwable.thrown/-throwable-thrown/index.html)

# Package ch.tutteli.atrium.assertions.throwable.thrown.builders
Contains the [ThrowableThrownBuilder](./ch.tutteli.atrium.assertions.throwable.thrown.builders/-throwable-thrown-builder/index.html)
which typically is used to create an [assertion verb for expected exceptions](https://github.com/robstoll/atrium#use-own-assertion-verbs) 

# Package ch.tutteli.atrium.assertions.throwable.thrown.creators
Contains [ThrowableThrown.Creator](./ch.tutteli.atrium.assertions.throwable.thrown/-throwable-thrown/-creator/index.html)s.

# Package ch.tutteli.atrium.assertions.throwable.thrown.providers
Contains [IterableContains.AbsentThrowableMessageProvider](./ch.tutteli.atrium.assertions.throwable.thrown/-throwable-thrown/-absent-throwable-message-provider/index.html)s.


# Package ch.tutteli.atrium.checking
Everything involved in checking [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html).

# Package ch.tutteli.atrium.creating
Everything involved in creating [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html).

# Package ch.tutteli.atrium.reporting
Everything involved in reporting [Assertion](./ch.tutteli.atrium.assertions/-assertion/index.html).

# Package ch.tutteli.atrium.reporting.translating
Everything involved in translating [Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)s.


# Package ch.tutteli.atrium.spec
Helper functions for specifications as well as the contract of [AssertionVerbFactory](./ch.tutteli.atrium.spec/-assertion-verb-factory/index.html).

# Package ch.tutteli.atrium.spec.assertions
Specifications for assertion function APIs (usually placed in an API module -- e.g. [ch.tutteli.atrium.api.cc.de_CH]).

# Package ch.tutteli.atrium.spec.checking
Specifications for the API of the package [ch.tutteli.atrium.checking](./ch.tutteli.atrium.checking/index.html).

# Package ch.tutteli.atrium.spec.creating
Specifications for the API of the package [ch.tutteli.atrium.creating](./ch.tutteli.atrium.creating/index.html).

# Package ch.tutteli.atrium.spec.reporting
Specifications for the API of the package [ch.tutteli.atrium.reporting](./ch.tutteli.atrium.reporting/index.html).

# Package ch.tutteli.atrium.spec.reporting.translating
Specifications for the API of the package [ch.tutteli.atrium.reporting.translating](./ch.tutteli.atrium.reporting.translating/index.html).

# Package ch.tutteli.atrium.spec.verbs
Specifications for [Assertion Verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs).


# Package ch.tutteli.atrium.verbs
Contains the [AssertionVerb](https://github.com/robstoll/atrium#use-own-assertion-verbs) which is an enum defining the 
[Translatable](./ch.tutteli.atrium.reporting.translating/-translatable/index.html)
of the [out-of-the-box Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs).
 
# Package ch.tutteli.atrium.verbs.assert
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `assert`.

# Package ch.tutteli.atrium.verbs.assertthat
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `assertThat`.

# Package ch.tutteli.atrium.verbs.expect
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `expect`.