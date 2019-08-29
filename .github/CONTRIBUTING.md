# Contributing to Atrium

Thank you very much that you take your time to contribute to Atrium :smiley:

Following a few guidelines so that others can quickly benefit from your contribution.

*Table of Content*: [Code of Conduct](#code-of-conduct), [How to Contribute](#how-to-contribute), 
[Your First Code Contribution](#your-first-code-contribution), [Coding Conventions](#coding-conventions),
[Pull Request Checklist](#pull-request-checklist).



## Code of Conduct
This project and everyone participating in it is governed by Atrium's 
[Code of Conduct](https://github.com/robstoll/atrium/tree/master/.github/CODE_OF_CONDUCT.md). 
By participating, you are expected to uphold this code. Please report unacceptable behavior to info@tutteli.ch

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
  
- You would like to write an assertion function but have trouble to start?  
  Please read the section [Your First Code Contribution](#your-first-code-contribution). 
  If you still have trouble to start, then contact us on 
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
  Nice catch :sunglasses: Please fix it and create a pull request.
    
- You wrote an assertion function and would like to contribute it to Atrium?  
  Awesome :+1: please review the [pull request checklist](#pull-request-checklist) and create a pull request
  
- You have other ideas how Atrium could be improved?  
  Contact us on 
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/),
  we are looking forward to your ideas.

In any case, if you are uncertain how you can contribute, then contact us on 
[slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
and we will figure it out together :smile:

## Your First Code Contribution
Fantastic, thanks for your effort! 
 
Following a small guidance how we suggest to start.
 
1. Fork the repository to your repositories (see [Fork a repo](https://help.github.com/en/articles/fork-a-repo) for help). 
2. Use an IDE which supports Kotlin and gradle projects
   We suggest [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
   and install the following plugins:
   - EditorConfig by JetBrains (might be already activated)
   - Spek Framework by Spek Team 
3. Import the project (import the build.gradle -> import as project, works more reliable)
4. Open up a terminal (e.g. Intellij's built in -> usually ALT+F12) and run `./gr build` 
   This builds the project (compile, run tests etc) via gradle (notice, we renamed `gradlew` to `gr`, more convenient no?) 
5. Read up the [Coding Conventions of Atrium](#coding-conventions) (there are only 5 points).

Perfect, you are setup and ready to go. 
Have a look at [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
where [good first issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22)
are easier to start with.
Please write a comment such like `I am working on this` into the issue, 
this way we can assign the task to you (so that others know there is already someone working on the issue)
and it gives us the chance to have a look at the description again and revise if necessary.

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
3. 👏👏:clap: you have submitted your first code contribution to Atrium :blush:

## Coding Conventions
So far we do not try to enforce too much. We will review your patches and comment if necessary.
However, here a few hints in order that your pull request is merged quickly.
1. Make sure the compiler does not generate warnings.
2. Try to write code in a similar style as the existing 
   (We suggest you copy something existing and modify it).
3. Write readable code and express comments with code rather than comments.
4. Provide tests in form of [Spek](https://spekframework.org/docs/latest/) specifications.
5. Write your commit message in an [imperative style](https://chris.beams.io/posts/git-commit/).     

## Pull Request Checklist
Please make sure you can check every item on the following list before you create a pull request:  
- [ ] your pull request is rebased on the [latest commit on master](https://github.com/robstoll/atrium/commits/master)
- [ ] your pull request should ideally consists of one small commit. If not then make sure:
     - [ ] it cannot be split up in several pull requests.
     - [ ] your commits are meaningful to others - e.g. do not include temporary commits like `before lunch`;
     [Squash commits](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History#_squashing) where appropriate.
     
Once you have created and submitted your pull request, then make sure:
- [ ] your pull request passes travis and all other checks 
     (if not, then improve your pull request and `git push -f`)
