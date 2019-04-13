# Contributing to Atrium

Thank you very much that you take your time to contribute to Atrium :smiley:

Following a few guidelines so that others can quickly benefit from your contribution.

*Table of Content*: [Code of Conduct](#code-of-conduct), [How to Contribute](#how-to-contribute), 
[Your First Code Contribution](#your-first-code-contribution), [Coding Conventions](#coding-conventions),
[Pull Request Checklist](#pull-request-checklist).



## Code of Conduct
This project and everyone participating in it is governed by Atrium's 
[Code of Conduct](https://github.com/robstoll/atrium/tree/v0.8.0-RC1/.github/CODE_OF_CONDUCT.md). 
By participating, you are expected to uphold this code. Please report unacceptable behavior to info@tutteli.ch

## How to Contribute
- Star Atrium if you like it.

- Need help in using Atrium?  
  Write your question on 
  [slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
  and I will get back to you.
  You do not yet have a account on kotlinlang.slack.com? 
  [Invite yourself](http://slack.kotlinlang.org/) :wink:
  
- Found a bug?  
  [Open an issue](https://github.com/robstoll/atrium/issues/new?template=bug_report.md).
  
- Missing a feature?  
  [Create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
  
- You would like to write an assertion function but have trouble to start?  
  Please read the section [Your First Code Contribution](#your-first-code-contribution). 
  If you still have trouble to start, then contact me on 
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
  and I will try to give you some additional hints.

- You do not have a particular assertion function in mind but would like to contribute with code?
  Please have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  (ping me on [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) if there are not any).  
  
- You would like to add a new feature to Atrium?  
  Contact me on [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
  so that we can discuss it before you start.
  
- Found spelling mistakes?  
  Nice catch :sunglasses: Please create a pull request.
    
- You wrote an assertion function and would like to contribute it to Atrium?  
  Awesome :+1: please review the [pull request checklist](#pull-request-checklist) and create a pull request
  
- You have other ideas how Atrium could be improved?  
  Contact me on 
  [slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/),
  I am looking forward to your ideas.

In any case, if you are uncertain how you can contribute, then contact me on 
[slack](https://kotlinlang.slack.com/messages/D3CL4DDLG/)
and we will figure it out together :smile:

## Your First Code Contribution
Fantastic, thanks for your effort! Contact me on 
[slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
or post into the 
[atrium channel](https://kotlinlang.slack.com/messages/C887ZKGCQ/) 
whenever you need help to get up and running. 
You can also create a pull request and prepend the title with `[NEED HELP]` 
and explain your problem in the description.
 
Following a small guidance how I would start.
 
1. Fork the repository to your repositories. 
2. Use an IDE which supports Kotlin and gradle projects (I am currently using [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)).  
3. Import the projects.
4. Read up the [Coding Conventions of Atrium](#coding-conventions) (there are only 5 points).
5. Get to know how [Spek](http://spekframework.org/docs/latest/) works or look at 
   the existing specifications in atrium-spec.
6. Write a test-case for an existing class. This way you get familiar with the classes of Atrium, 
   you use Atrium and learn how Spek works.
7. Read the [Pull Request Checklist](#pull-request-checklist) and create your first pull-request.
8. :clap: you have submitted your first code contribution to Atrium :blush:

I would suggest you wait for my feedback so that you get a first impression of what I consider to be important.
Yet, if your fingers almost automatically start to write more code then go on :wink:

## Coding Conventions
So far I do not try to enforce too much. I will review your patches and comment if necessary.
However, here a few hints in order that your pull request is merged quickly.
1. Make sure the compiler does not generate warnings.
2. Try to write code in a similar style as the existing 
   (I suggest you copy something existing and modify it).
3. Write readable code and express comments with code rather than comments.
4. Provide tests in form of [Spek](http://spekframework.org/docs/latest/) specifications.
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
