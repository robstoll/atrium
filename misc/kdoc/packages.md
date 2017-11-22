# Module atrium

## KDoc of Atrium
Atrium is split up in different modules. The packages shown below contain classes etc. 
of all modules excluding the one of _atrium-imp-robstoll_ and _atrium-imp-robstoll-lib_.

API modules:
- **atrium-cc-en&#95;UK-robstoll** can be used as "shortcut" -- it defines dependencies on 
  atrium-api-code-completion-en&#95;UK, atrium-translations-en_UK and atrium-core-impl-robstoll
- **atrium-api-code-completion-de&#95;CH** provides an assertion function API in German where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
- **atrium-api-code-completion-en&#95;UK** provides an assertion function API in English where the main 
  focus of this API's design is put on ease of use/compatibility with code completion functionality of an IDE.
  
  
Assertion modules:
- **atrium-assertions** contains all functions which can be used to create assertions as well as the 
  [ReporterBuilder](./ch.tutteli.atrium.reporting/-reporter-builder/index.html).
- **atrium-translations-de&#95;CH** provides translations in German for 
  [ITranslatable](./ch.tutteli.atrium.reporting.translating/-i-translatable/index.html)s used in assertion functions. 
- **atrium-translations-en&#95;UK** provides translations in English for 
  [ITranslatable](./ch.tutteli.atrium.reporting.translating/-i-translatable/index.html)s used in assertion functions. 
- **atrium-verbs** contains out of the box assertion verbs (we suggest to [use your own assertion verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs)).

Core modules:
- **atrium-core-api** defines the contracts of the core of Atrium.
- **atrium-core-api-late-binding** defines the contract for 
  [AtriumFactory](./ch.tutteli.atrium/-atrium-factory/index.html)
  by providing dummy implementations which are eventually replaced by an atrium implementation. 
- **atrium-core-impl-robstoll** provides an [AtriumFactory](./ch.tutteli.atrium/-atrium-factory/index.html)
  which uses the implementations of *atrium-impl-robstoll-lib*
- **atrium-core-impl-robstoll-lib** [robstoll](https://github.com/robstoll)'s implementation of atrium.

Misc modules:
- **atrium-spec** contains [Spek](http://spekframework.org/) specifications for interfaces (defined in 
  _atrium-core-api_) and assertion functions which can be reused by Atrium implementations 
  and assertion function API implementations to assure they fulfill the specifications.  

# Package ch.tutteli.atrium
Contains the [IAtriumFactory](./ch.tutteli.atrium/-i-atrium-factory/index.html) and its extension functions.


# Package ch.tutteli.atrium.api.cc.de_CH
Contains the code completion focused assertion function API in German. 

# Package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.


# Package ch.tutteli.atrium.api.cc.en_UK
Contains the code completion focused assertion function API in English.

# Package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders
Contains the builders - necessary to provide an extensible fluent API - which allow to create sophisticated `contains` 
assertions for CharSequence.


# Package ch.tutteli.atrium.assertions
Contains different types of [IAssertion](./ch.tutteli.atrium.assertions/-i-assertion/index.html), 
e.g. [IAssertionGroup](./ch.tutteli.atrium.assertions/-i-assertion-group/index.html) etc.
as well as the [ITranslatable](./ch.tutteli.atrium.reporting.translating/-i-translatable/index.html)s 
used in the assertion functions (e.g. [DescriptionAnyAssertion](./ch.tutteli.atrium.assertions/-description-any-assertion/index.html)).


# Package ch.tutteli.atrium.assertions.any.narrow
Contains inter alia the contract for narrowing assertion builders -- [IAnyNarrow](./ch.tutteli.atrium.assertions.any.narrow/-i-any-narrow/index.html).

# Package ch.tutteli.atrium.assertions.any.narrow.failurehandler
Contains [IDownCastFailureHandler](./ch.tutteli.atrium.assertions.any.narrow/-i-any-narrow/-i-down-cast-failure-handler/index.html)s.


# Package ch.tutteli.atrium.assertions.basic.contains
Contains the basic contract for contains assertion builders -- [IContains](./ch.tutteli.atrium.assertions.basic.contains/-i-contains/index.html).

# Package ch.tutteli.atrium.assertions.basic.contains.builders
Contains base classes for builders representing a step in the process of creating sophisticated `contains` assertions.

# Package ch.tutteli.atrium.assertions.basic.contains.checkers
Contains base classes for [IContains.IChecker](./ch.tutteli.atrium.assertions.basic.contains/-i-contains/-i-checker/index.html).

# Package ch.tutteli.atrium.assertions.basic.contains.creators
Contains base classes for [IContains.ICreators](./ch.tutteli.atrium.assertions.basic.contains/-i-contains/-i-creator/index.html).


# Package ch.tutteli.atrium.assertions.charsequence.contains
Contains the [CharSequenceContainsAssertionCreator](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains-assertion-creator/index.html)
which can be used to create sophisticated contains assertions.

# Package ch.tutteli.atrium.assertions.charsequence.contains.builders
Contains base classes for fluent API builders used in assertion function APIs 
(e.g. [atrium-assertions-code-completion-en_UK](./ch.tutteli.atrium.api.cc.en_-u-k.assertions.charsequence.contains.builders/index.html)).

# Package ch.tutteli.atrium.assertions.charsequence.contains.checkers
Contains [CharSequenceContainsAssertionCreator.IChecker](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains-assertion-creator/-i-checker/index.html)s.

# Package ch.tutteli.atrium.assertions.charsequence.contains.decorators
Contains [CharSequenceContainsAssertionCreator.IDecorator](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains-assertion-creator/-i-decorator/index.html)s.

# Package ch.tutteli.atrium.assertions.charsequence.contains.searchers
Contains [CharSequenceContainsAssertionCreator.ISearcher](./ch.tutteli.atrium.assertions.charsequence.contains/-char-sequence-contains-assertion-creator/-i-searcher/index.html)s.

# Package ch.tutteli.atrium.builders.charsequence.contains
Code completion style API for sophisticated contains assertions for CharSequence. 


# Package ch.tutteli.atrium.checking
Everything involved in checking made [IAssertion](./ch.tutteli.atrium.assertions/-i-assertion/index.html).

# Package ch.tutteli.atrium.creating
Everything involved in creating [IAssertion](./ch.tutteli.atrium.assertions/-i-assertion/index.html).

# Package ch.tutteli.atrium.reporting
Everything involved in reporting made [IAssertion](./ch.tutteli.atrium.assertions/-i-assertion/index.html).

# Package ch.tutteli.atrium.reporting.translating
Everything involved in translating [ITranslatable](./ch.tutteli.atrium.reporting.translating/-i-translatable/index.html)s.


# Package ch.tutteli.atrium.spec
Helper functions for specifications as well as the contract of [IAssertionVerbFactory](./ch.tutteli.atrium.spec/-i-assertion-verb-factory/index.html).

# Package ch.tutteli.atrium.spec.assertions
Specifications for assertion function APIs (usually placed in the package [ch.tutteli.atrium])

# Package ch.tutteli.atrium.spec.checking
Specifications for the API of the package [ch.tutteli.atrium.checking](./ch.tutteli.atrium.checking/index.html).

# Package ch.tutteli.atrium.spec.creating
Specifications for the API of the package [ch.tutteli.atrium.creating](./ch.tutteli.atrium.creating/index.html).

# Package ch.tutteli.atrium.spec.reporting
Specifications for the API of the package [ch.tutteli.atrium.reporting](./ch.tutteli.atrium.reporting/index.html).

# Package ch.tutteli.atrium.spec.reporting.translating
Specifications for the API of the package [ch.tutteli.atrium.reporting.translating](./ch.tutteli.atrium.reporting.translating/index.html).

# Package ch.tutteli.atrium.spec.reporting.translating
Specifications for [Assertion Verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs).

# Package ch.tutteli.atrium.spec.verbs
Specifications for [Assertion Verbs](https://github.com/robstoll/atrium#use-own-assertion-verbs).


# Package ch.tutteli.atrium.verbs
Contains the [AssertionVerb](https://github.com/robstoll/atrium#use-own-assertion-verbs) which is an enum defining the 
[ITranslatable](./ch.tutteli.atrium.reporting.translating/-i-translatable/index.html)
of the [out-of-the-box Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs).
 
# Package ch.tutteli.atrium.verbs.assert
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `assert`.

# Package ch.tutteli.atrium.verbs.assertthat
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `assertThat`.

# Package ch.tutteli.atrium.verbs.expect
Contains the [Assertion Verbs](https://github.com/robstoll/atrium#out-of-the-box-assertion-verbs) functions named `expect`.