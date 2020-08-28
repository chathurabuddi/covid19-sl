### Always start with an issue

Before you begin anything else, summarize your ideas in an issue and share it. It’s such a simple rule, but the impact is huge. The benefits of collaboration start at the point of making an issue. By making an issue, you get your ideas out there, and this allows your collaborators to have their say early in the process. The issue opens the conversation, and you gain the benefits of collaboration from the start.

### Issue tracker guidelines

Search the existing issues for similar entries before submitting your own, there's a good chance somebody else had the same issue or discussion. Show your support with an award emoji and/or join the discussion. Apply the relevent lables once you create a new issue. For example, if it is a bug, use `bug` lable. This will help us to categorize and prioritize issues.  

### Start contributing to the project

To contribute to the project, you need to fork the repository to your github account and clone it to the local development environment. 

### Use feature branches, no direct commits on master.

If you're coming over from SVN, for example, you'll be used to a trunk-based workflow. When using Git you should create a branch for whatever you’re working on, so that you end up doing a code review before you merge. To create new feature branch from master

```
git checkout master
git checkout -b feature-branch-name
```

### Committing often with the right message

We recommend to commit early and often. Each time you have **a functioning set of code** a commit can be made. The advantage is that when an extension or refactor goes wrong it is easy to revert to a working version. 

This is quite a change for programmers that used SVN before, they used to commit when their work was ready to share. The trick is to use the merge/pull request with multiple commits when your work is ready to share. 

The commit message should reflect your **intention**, not the contents of the commit. The contents of the commit can be easily seen anyway, the question is why you did it. 

An example of a good commit message is: `Combine templates to dry up the user views.`.

Some words that are bad commit messages because they don't contain much information are: `change`, `improve` and `refactor`. The word `fix` or `fixes` is also a red flag, unless it comes after the commit sentence and references an issue number. To see more information about the formatting of commit messages please see **[this great blog post](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html)** by Tim Pope.

**Bad** : `git commit -m "add User.Java"`  
**Good** : `git commit -m "create user model to store user information"`

### Link issues with commit message

In your commit message, you can add the # symbol followed by an issue number related to that commit message. By doing so, you create a link between the two stages of the development workflow: the issue itself and the commit(s) related to that issue.

```
git commit -m "create user model to store user information. Ref #xxx"
```

### Create a pull request

Once you push your changes to a feature-branch in your fork, GitHub will identify this change and will prompt you to create a Pull Request (PR) to the base repository.

Every PR will have a title (something that summarizes that implementation) and a description supported by Markdown. In the description, you can shortly describe what that PR is doing, mention any related issues and PRs (creating a link between them), and you can also add the **[issue closing pattern](https://docs.github.com/en/github/managing-your-work-on-github/linking-a-pull-request-to-an-issue#linking-a-pull-request-to-an-issue-using-a-keyword)**, which will close that issue(s) once the PR is merged.

### Ask to merge

If your PR is ready to merge, comment on the PR and ask to merge.
