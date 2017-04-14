

### All Types

| [ch.tutteli.atrium.assertions.AssertionGroup](../ch.tutteli.atrium.assertions/-assertion-group/index.md) | Represent a group of [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s identified by a [name](../ch.tutteli.atrium.assertions/-assertion-group/name.md) and an associated [subject](../ch.tutteli.atrium.assertions/-assertion-group/subject.md). |
| [ch.tutteli.atrium.test.creating.AssertionPlantCheckLazilySpec](../ch.tutteli.atrium.test.creating/-assertion-plant-check-lazily-spec/index.md) |  |
| [ch.tutteli.atrium.test.creating.AssertionPlantNullableSpec](../ch.tutteli.atrium.test.creating/-assertion-plant-nullable-spec/index.md) |  |
| [ch.tutteli.atrium.AtriumFactory](../ch.tutteli.atrium/-atrium-factory/index.md) | The `abstract factory` of atrium. |
| [ch.tutteli.atrium.verbs.AtriumReporterSupplier](../ch.tutteli.atrium.verbs/-atrium-reporter-supplier/index.md) | Supplies the [IReporter](../ch.tutteli.atrium.reporting/-i-reporter/index.md) for the assertion verbs [assert](../ch.tutteli.atrium.verbs.assert/assert.md), [assertThat](../ch.tutteli.atrium.verbs.assertthat/assert-that.md) and [expect](../ch.tutteli.atrium.verbs.expect/expect.md). |
| [ch.tutteli.atrium.creating.DownCastBuilder](../ch.tutteli.atrium.creating/-down-cast-builder/index.md) | A builder for creating a down-casting assertion. |
| [ch.tutteli.atrium.test.creating.DownCastBuilderSpec](../ch.tutteli.atrium.test.creating/-down-cast-builder-spec/index.md) |  |
| [ch.tutteli.atrium.test.checking.FeatureAssertionCheckerSpec](../ch.tutteli.atrium.test.checking/-feature-assertion-checker-spec/index.md) |  |
| [ch.tutteli.atrium.assertions.FeatureAssertionGroup](../ch.tutteli.atrium.assertions/-feature-assertion-group/index.md) | Represent a group of [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) identified by a [featureName](../ch.tutteli.atrium.assertions/-feature-assertion-group/feature-name.md) and a belonging [subSubject](../ch.tutteli.atrium.assertions/-feature-assertion-group/sub-subject.md). |
| [ch.tutteli.atrium.assertions.IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) | The base interface of all assertions, providing the method [holds](../ch.tutteli.atrium.assertions/-i-assertion/holds.md). |
| [ch.tutteli.atrium.checking.IAssertionChecker](../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) | Checks given [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and reports if one of them fails. |
| [ch.tutteli.atrium.checking.IAssertionCheckerDelegateFail](../ch.tutteli.atrium.checking/-i-assertion-checker-delegate-fail/index.md) | Provides a default-implementation for [IAssertionChecker.fail](../ch.tutteli.atrium.checking/-i-assertion-checker/fail.md) which first checks
that the given [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) fails and then delegates to [IAssertionChecker.check](../ch.tutteli.atrium.checking/-i-assertion-checker/check.md). |
| [ch.tutteli.atrium.assertions.IAssertionGroup](../ch.tutteli.atrium.assertions/-i-assertion-group/index.md) | The base interface for [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](../ch.tutteli.atrium.assertions/-i-assertion/holds.md)
which returns `true` if all its [assertions](../ch.tutteli.atrium.assertions/-i-assertion-group/assertions.md) hold. |
| [ch.tutteli.atrium.reporting.IAssertionMessageFormatter](../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) | Represents a formatter for an [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) and its [Message](../ch.tutteli.atrium.assertions/-message/index.md)s. |
| [ch.tutteli.atrium.creating.IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) | Represents a plant for [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and offers the possibility to check all the added assertions. |
| [ch.tutteli.atrium.creating.IAssertionPlantNullable](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md) | Represents an assertion plant for nullable types. |
| [ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/index.md) | An assertion plant which has [CommonFields](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md); provides the property [subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) for ease of use. |
| [ch.tutteli.atrium.test.IAssertionVerbFactory](../ch.tutteli.atrium.test/-i-assertion-verb-factory/index.md) |  |
| [ch.tutteli.atrium.IAtriumFactory](../ch.tutteli.atrium/-i-atrium-factory/index.md) | The minimum contract of the `abstract factory` of atrium. |
| [ch.tutteli.atrium.assertions.IFeatureAssertionGroup](../ch.tutteli.atrium.assertions/-i-feature-assertion-group/index.md) | The base interface for feature [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](../ch.tutteli.atrium.assertions/-i-assertion/holds.md)
which returns `true` if all its [assertions](../ch.tutteli.atrium.assertions/-i-feature-assertion-group/assertions.md) hold. |
| [ch.tutteli.atrium.assertions.IMultiMessageAssertion](../ch.tutteli.atrium.assertions/-i-multi-message-assertion/index.md) | The base interface for [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s which have multiple [Message](../ch.tutteli.atrium.assertions/-message/index.md)s. |
| [ch.tutteli.atrium.reporting.IObjectFormatter](../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) | Represents a formatter for objects. |
| [ch.tutteli.atrium.assertions.IOneMessageAssertion](../ch.tutteli.atrium.assertions/-i-one-message-assertion/index.md) | The base interface for [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s which have one [Message](../ch.tutteli.atrium.assertions/-message/index.md). |
| [ch.tutteli.atrium.reporting.IReporter](../ch.tutteli.atrium.reporting/-i-reporter/index.md) | Represents a reporter which reports about [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |
| [ch.tutteli.atrium.assertions.Message](../ch.tutteli.atrium.assertions/-message/index.md) | Represents a message of an [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md). |
| [ch.tutteli.atrium.assertions.OneMessageAssertion](../ch.tutteli.atrium.assertions/-one-message-assertion/index.md) | A default implementation for [IOneMessageAssertion](../ch.tutteli.atrium.assertions/-i-one-message-assertion/index.md). |
| [ch.tutteli.atrium.test.reporting.OnlyFailureReporterSpec](../ch.tutteli.atrium.test.reporting/-only-failure-reporter-spec/index.md) |  |
| [ch.tutteli.atrium.reporting.RawString](../ch.tutteli.atrium.reporting/-raw-string/index.md) | Use this class to represent a [String](#) which should be treated as raw [String](#) in reporting. |
| [ch.tutteli.atrium.reporting.ReporterBuilder](../ch.tutteli.atrium.reporting/-reporter-builder/index.md) | A builder to create an [IReporter](../ch.tutteli.atrium.reporting/-i-reporter/index.md) consisting of an [IObjectFormatter](../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) which is used by an
[IAssertionMessageFormatter](../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) which in turn is used by the created [IReporter](../ch.tutteli.atrium.reporting/-i-reporter/index.md). |
| [ch.tutteli.atrium.test.reporting.SameLineAssertionMessageFormatterSpec](../ch.tutteli.atrium.test.reporting/-same-line-assertion-message-formatter-spec/index.md) |  |
| [ch.tutteli.atrium.creating.ThrowableFluent](../ch.tutteli.atrium.creating/-throwable-fluent/index.md) | Provides [toThrow](../ch.tutteli.atrium.creating/-throwable-fluent/to-throw.md) methods for making assertions about a [Throwable](#)
which one expects was thrown. |
| [ch.tutteli.atrium.test.creating.ThrowableFluentSpec](../ch.tutteli.atrium.test.creating/-throwable-fluent-spec/index.md) |  |
| [ch.tutteli.atrium.test.checking.ThrowingAssertionCheckerSpec](../ch.tutteli.atrium.test.checking/-throwing-assertion-checker-spec/index.md) |  |
| [ch.tutteli.atrium.test.reporting.ToStringObjectFormatter](../ch.tutteli.atrium.test.reporting/-to-string-object-formatter/index.md) |  |
| [ch.tutteli.atrium.test.verbs.VerbSpec](../ch.tutteli.atrium.test.verbs/-verb-spec/index.md) |  |

