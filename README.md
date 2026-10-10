# SQAT

Java software quality assurance project. It uses **JDK 21**, **Maven**, and
**JUnit Jupiter 5.10.2**. Run the commands below from the project root (the
directory containing `pom.xml`).

## Quick test commands

| What you want to do | Command | Result |
| --- | --- | --- |
| Run every test | `mvn test` | Compiles the project and runs all tests. |
| Clean, then run every test | `mvn clean test` | Removes old build output, compiles, and runs all tests. |
| Compile tests only | `mvn test-compile` | Compiles main and test code; does **not** run tests. |
| Compile tests in non-interactive mode | `mvn test-compile --batch-mode` | Same compile-only result; batch mode is useful for scripts and CI. |
| Run one test class | `mvn -Dtest=LeapYearDetectorTest test` | Runs only `LeapYearDetectorTest`. |
| Run one test method | `mvn -Dtest=LeapYearDetectorTest#shouldReturnTrueForLeapYear test` | Runs only the named method. |
| Run all tests without interactive Maven output | `mvn test --batch-mode` | Same tests as `mvn test`; batch mode does not change test selection. |

The other test class is `CalculatorTest`; to run it alone, use
`mvn -Dtest=CalculatorTest test`. Maven stores compiled files and test reports
in `target/`, with test reports in `target/surefire-reports/`.

## Git hooks

The pre-commit hook is a Bash script at `.git/hooks/pre-commit`. When enabled,
Git runs it automatically before a commit. It checks that Maven is available
and runs `mvn clean test --batch-mode`; a failure stops the commit. Maven
commands in the table run directly and do not trigger Git hooks.

### Enable the hook

Open **Git Bash** in the project root. If the script exists but is not
executable, enable it with:

```shell
chmod +x .git/hooks/pre-commit
```

Git Bash is needed because the hook uses Bash. Make sure Maven is available on
Git Bash's `PATH`. Once enabled, Git runs the hook automatically before a
commit.

### Run the hook manually

To run the same checks without making a commit, use Git Bash from the project
root:

```shell
bash "$(git rev-parse --git-path hooks)/pre-commit"
```

### Hook location and other hooks

The `.git` directory is hidden and is not normally included in a clone or
shared with other developers. Find the local hooks directory with
`git rev-parse --git-path hooks` (usually `.git/hooks`). If
`.git/hooks/pre-commit` is missing in a clone, the hook is not installed there.
Other hooks, such as `commit-msg` and `pre-push`, are not configured for this
project; Git's `.sample` files are examples and do not run automatically.

## Where to find the tests

Application code is under `src/main/java/com/ashis/sqat/`. JUnit tests are
under `src/test/java/com/ashis/sqat/`, including `LeapYearDetectorTest` and
`service/CalculatorTest`. JUnit Jupiter is declared in `pom.xml`.

Jupyter is not configured or needed for this Java project's tests; use Maven
commands above.
