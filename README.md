# Project Setup Guide

This document outlines the initial setup steps required for new engineers
to contribute to this Java project using Maven and Git hooks.

## Prerequisites

- **Java Development Kit (JDK):** Ensure you have a compatible JDK (17+)
  installed (check `pom.xml` for the required version).
- **Maven:** Maven is used for dependency management and build automation.
  Install Maven if you haven't already.
- **Git:** Git is essential for version control.
- **Python (for pre-commit):** The `pre-commit` framework requires Python.
  Ensure Python 3.6+ is installed.

## One-Time Setup

1. **Install Git Hooks:**

   - Run the `setup-hooks.sh` script. This script will:
     - Install the `pre-commit` framework and runs `pre-commit install`.
       This configures everything required for `.pre-commit-config.yaml`
     - Configure the `pre-push` hook to execute the script in ./git_hooks/pre-push.sh.
   - This script should be run after every git clone.
   - The pre-commit uses the configuration file `.pre-commit-config.yaml`.
     Along with other things, the yaml has an instruction to run the script `./git_hooks/pre-commit-spotless.sh`
     - The script `./git_hooks/pre-commit-spotless.sh` will run `mvn spotless:check`;
       if there are any violations it will apply `mvn spotless:apply`

   ```bash
   ./setup-hooks.sh
   ```

1. **Install pre-commit hooks:**

   - This step is not required, it is handled in `setup-hooks.sh`
   - However, this command must be run every time `.pre-commit-config.yaml` is modified.

   ```bash
   pre-commit install
   ```

1. **Maven Build:**

   - Build the project using Maven to download dependencies and compile the code:

   ```bash
   mvn clean install
   ```

1. **Manual run of pre-commit:**

   - You can run the pre-commit checks manually, before `git commit ....`

   ```bash
   pre-commit run --all-files
   ```

## Understanding Git Hooks

- **`pre-commit`:**
  - The `pre-commit` framework is used to run checks before each commit.
  - The configuration for these checks is defined in `.pre-commit-config.yaml`.
  - This project uses `pre-commit` to execute a local script within the repository
    to enforce coding standards or perform other checks.
  - To manually run the pre-commit hooks, execute `pre-commit run --all-files`
- **`pre-push`:**
  - The `pre-push` hook is executed before pushing changes to a remote repository.
  - This project has configured a `pre-push` hook to run script `./git_hooks/pre-push.sh`.

## Workflow

- **Make Changes:** Make your code changes in your local branch.
- **Commit Changes:** Before committing, `pre-commit` will automatically
  run the configured checks.
  If any checks fail, you'll need to fix the issues before committing.
- **Push Changes:** Before pushing, the `pre-push` hook will run the configured script.
  If any checks fail, you'll need to fix the issues before pushing.
- **Create Pull Request:** Once your changes are pushed,
  create a pull request for review.

## Troubleshooting

- **`pre-commit` Issues:** If you encounter issues with `pre-commit`,
  check the output for error messages.
  You can also try running `pre-commit run --all-files` manually to debug.
- **`pre-push` Issues:** If your push fails due to the `pre-push` hook,
  check the error messages displayed in the terminal.
- **Maven Issues:** If you encounter Maven build issues,
  check the `pom.xml` file for dependencies and configurations.
  Ensure your JDK and Maven versions are compatible.
- **Hook Permission issues:** If you encounter permission issues while
  running the hooks, ensure the hook files inside `.git/hooks` are executable.
  You can add execute permission using `chmod +x .git/hooks/<hook_name>`.

## Further Information

- Refer to the `pom.xml` file for project dependencies and build configurations.
- Refer to the `.pre-commit-config.yaml` file to understand the pre-commit
  hooks that are executed.
- Refer to the `setup-hooks.sh` file to understand the hook installation process.
- Refer to the script that is mapped to the pre-push hook,
  to understand the pre-push validations.
