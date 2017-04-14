[atrium](../index.md) / [ch.tutteli.atrium.assertions](.)

## Package ch.tutteli.atrium.assertions

### Types

| [AssertionGroup](-assertion-group/index.md) | `data class AssertionGroup : `[`IAssertionGroup`](-i-assertion-group/index.md)<br>Represent a group of [IAssertion](-i-assertion/index.md)s identified by a [name](-assertion-group/name.md) and an associated [subject](-assertion-group/subject.md). |
| [FeatureAssertionGroup](-feature-assertion-group/index.md) | `data class FeatureAssertionGroup : `[`IFeatureAssertionGroup`](-i-feature-assertion-group/index.md)<br>Represent a group of [IAssertion](-i-assertion/index.md) identified by a [featureName](-feature-assertion-group/feature-name.md) and a belonging [subSubject](-feature-assertion-group/sub-subject.md). |
| [IAssertion](-i-assertion/index.md) | `interface IAssertion`<br>The base interface of all assertions, providing the method [holds](-i-assertion/holds.md). |
| [IAssertionGroup](-i-assertion-group/index.md) | `interface IAssertionGroup : `[`IAssertion`](-i-assertion/index.md)<br>The base interface for [IAssertion](-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](-i-assertion/holds.md)
which returns `true` if all its [assertions](-i-assertion-group/assertions.md) hold. |
| [IFeatureAssertionGroup](-i-feature-assertion-group/index.md) | `interface IFeatureAssertionGroup : `[`IAssertion`](-i-assertion/index.md)<br>The base interface for feature [IAssertion](-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](-i-assertion/holds.md)
which returns `true` if all its [assertions](-i-feature-assertion-group/assertions.md) hold. |
| [IMultiMessageAssertion](-i-multi-message-assertion/index.md) | `interface IMultiMessageAssertion : `[`IAssertion`](-i-assertion/index.md)<br>The base interface for [IAssertion](-i-assertion/index.md)s which have multiple [Message](-message/index.md)s. |
| [IOneMessageAssertion](-i-one-message-assertion/index.md) | `interface IOneMessageAssertion : `[`IAssertion`](-i-assertion/index.md)<br>The base interface for [IAssertion](-i-assertion/index.md)s which have one [Message](-message/index.md). |
| [Message](-message/index.md) | `data class Message`<br>Represents a message of an [IAssertion](-i-assertion/index.md). |
| [OneMessageAssertion](-one-message-assertion/index.md) | `class OneMessageAssertion : `[`IOneMessageAssertion`](-i-one-message-assertion/index.md)<br>A default implementation for [IOneMessageAssertion](-i-one-message-assertion/index.md). |

