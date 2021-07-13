# Contributing to Atrium

Thank you very much for taking your time to contribute to Atrium :smiley:

Following a few guidelines so that others can quickly benefit from your contribution.

*Table of Content*: [Code of Conduct](#code-of-conduct), [How to Contribute](#how-to-contribute), 
[Your First Code Contribution](#your-first-code-contribution), [Coding Conventions](#coding-conventions),
[Pull Request Checklist](#pull-request-checklist).



## Code of Conduct
This project and everyone participating in it is governed by Atrium's 
[Code of Conduct](https://github.com/robstoll/atrium/tree/v0.17.0-RC1/.github/CODE_OF_CONDUCT.md). 
By participating, you are expected to uphold this code. Please report unacceptable behaviour to info@tutteli.ch

## How to Contribute
- Star Atrium if you like it.

- Need help in using Atrium?  
  Write your question on 
  [slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
  and we will get back to you.
  You do not yet have a account on kotlinlang.slack.com? 
  [Invite yourself](https://slack.kotlinlang.org/) :wink:
  
- Found a bug?  
  [Open an issue](https://github.com/robstoll/atrium/issues/new?template=bug_report.md).
  
- Missing a feature?  
  [Create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
  
- You would like to write an assertion function but you're having trouble starting?
  Please read the section [Your First Code Contribution](#your-first-code-contribution). 
  If you still have trouble starting, then contact us on
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/) 
  ([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet)
  and we will try to give you some additional hints.

- You do not have a particular assertion function in mind but would like to contribute with code?
  Please have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  -- ping us on 
  [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ)
  ([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet) 
  if there are not any.  
  
- You would like to add a new feature to Atrium?  
  Contact use on 
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
  ([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet) 
  so that we can discuss it before you start.
  
- Found spelling mistakes?  
  Nice catch :mag: Please fix it and create a pull request.
    
- You wrote an assertion function and would like to contribute it to Atrium?  
  Awesome :+1: please review the [pull request checklist](#pull-request-checklist) and create a pull request

- You would like to support the project financially?  
  Cool :sunglasses: this can be done via [GitHub Sponsors](https://github.com/sponsors/robstoll)  
  
- You have other ideas how Atrium could be improved?  
  Contact us on 
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/),
  we are looking forward to your ideas.

In any case, if you are uncertain how you can contribute, then contact us on 
[slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
and we will figure it out together :smile:

## Your First Code Contribution
Fantastic, thanks for your effort! 
 
The following are a few guidelines on how we suggest you start.
 
1. Fork the repository to your repositories (see [Fork a repo](https://help.github.com/en/articles/fork-a-repo) for help). 
2. Use an IDE which supports Kotlin and gradle projects.
   We suggest [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/).
   Because of [IDEA-228368](https://youtrack.jetbrains.com/issue/IDEA-228368) you will need at least IntelliJ 2020.1, or the Gradle import will fail.
   Install the following plugins:
   - EditorConfig by JetBrains (might be already activated)
   - Spek Framework by Spek Team
3. Import the project (import the build.gradle -> import as project, works more reliably)
4. Open up a terminal (e.g. Intellij's built in -> usually ALT+F12) and run `./gradlew build` 
   This builds the project (compile, run tests etc) via gradle
5. Read up the [Coding Conventions of Atrium](#coding-conventions) (there are only 5 points).

Note: IntelliJ requires a few workarounds.
- IntelliJ may warn you about duplicate content roots.
  Simply ignore it (IntelliJ doesn't support sharing of sources).
- IntelliJ may warn you about a Symbol being declared in an unnamed module.
  This is an [IntelliJ bug](https://youtrack.jetbrains.com/issue/KT-35343) (feel free to upvote), and can be worked around by using JDK 8, which you can switch to in File &rarr; Project Structure &rarr; Project &rarr; Project SDK.

Perfect, you are setup and ready to go. 
Have a look at [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
where [good first issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22)
are easier to start with.
Please write a comment such as `I am working on this` in the issue,
this way we can assign the task to you (so that others know there is already someone working on the issue)
and it gives us the chance to have a look at the description again and revise if necessary.

<a name="git"></a>
*Git*  

Dealing with Git for the first time? Here are some recommendations for how to set up Git when working on an issue: 
- create a new branch for the issue using `git checkout -b <branch-name>` (preferably, the branch name
  should be descriptive of the issue or the change being made, e.g `#108-path-exists`.) Working
  on a new branch makes it easier to make more than one pull request.
- add this repository as a remote repository using
 `git remote add upstream https://github.com/robstoll/atrium.git`. You will use this to
  fetch changes made in this repository.
- to ensure your branch is up-to-date, rebase your work on
  upstream/master using `git rebase upstream/master` or `git pull -r upstream master`.
  This will add all new changes in this repository into your branch and place your
  local unpushed changes at the top of the branch.

You can read more on Git [here](https://git-scm.com/book/).

Contact us on
[slack](https://kotlinlang.slack.com/messages/C887ZKGCQ/)
([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet)  
whenever you need help to get up and running or have questions or simply write in the issue.

We recommend you create a pull request (see [About pull requests](https://help.github.com/en/articles/about-pull-requests) for help)
in case you are not sure how you should do something. 
This way we can give you fast feedback regarding multiple things (style, does it go in the right direction etc.) before you spend time for nothing.
Prepend the title with `[WIP]` (work in progress) in this case and leave a comment with your questions.

Finally, when you think your PR (short for pull request) is ready, then please:

1. read the [Pull Request Checklist](#pull-request-checklist) 
2. Create your first pull-request
3. 👏👏👏 you have submitted your first code contribution to Atrium :blush:

## Coding Conventions
So far we do not try to enforce too much. We will review your patches and comment if necessary.
However, here a few hints in order that your pull request is merged quickly.
1. Make sure the compiler does not generate warnings.
2. Try to write code in a similar style as the existing 
   (We suggest you copy something existing and modify it).
3. Write readable code and express comments with code rather than comments.
4. Provide tests in form of [Spek](https://spekframework.org/specification/) specifications.
5. Write your commit message in an [imperative style](https://chris.beams.io/posts/git-commit/).     

## Pull Request Checklist
Please make sure you can check every item on the following list before you create a pull request:  
- [ ] your pull request is rebased on the [latest commit on master](https://github.com/robstoll/atrium/commits/master)
- [ ] Your pull request addresses only “one thing”. It cannot be meaningfully split up into multiple pull requests.
     
Once you have created and submitted your pull request, make sure:
- [ ] your pull request passes Continuous Integration and all other checks
