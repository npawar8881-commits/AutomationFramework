# Selenium Automation Framework

A Java-based UI test automation framework built with Selenium WebDriver,
Cucumber BDD, TestNG, and Maven. The project demonstrates a maintainable
Page Object Model structure, centralized browser/configuration
management, Excel-driven test data, execution reporting, logging,
failure screenshots, and a Jenkins pipeline.

## Technology Stack

-   Java 17
-   Selenium WebDriver 4.22.0
-   Cucumber 7.18.1 (Gherkin feature files and step definitions)
-   TestNG 7.10.2 (Cucumber test runner integration)
-   Maven
-   WebDriverManager 5.8.0
-   Apache POI 5.4.1 (Excel test data)
-   ExtentReports 5.1.2
-   Log4j2 2.23.1
-   Jenkins Pipeline

## Framework Capabilities

-   **Page Object Model:** Page classes encapsulate page elements and
    user actions; `BasePage` provides common browser interactions.
-   **Driver management:** `DriverFactory` creates and closes Chrome,
    Edge, or Firefox WebDriver instances. A `ThreadLocal<WebDriver>` is
    used to isolate driver instances by thread.
-   **Centralized configuration:** Browser, environment, headless mode,
    wait timeout, application URLs, and retry settings are read through
    `ConfigReader` and `config.properties`. JVM system properties can
    override configuration values.
-   **BDD test design:** Scenarios are written in Gherkin feature files
    and connected to Java step definitions.
-   **Excel test data:** `ExcelUtility` reads test data from XLSX
    resources.
-   **Wait and browser utilities:** Reusable wait, browser options,
    window, and assertion utilities.
-   **Failure diagnostics:** Failed scenarios capture screenshots and
    attach them to the Cucumber and Extent reports.
-   **Reporting:** ExtentReports generates an HTML report; Cucumber
    generates HTML and JSON execution outputs.
-   **Logging:** Log4j2 records framework and execution activity.
-   **Retry / rerun support:** The project contains TestNG retry support
    classes and a Cucumber failed-scenario rerun runner.
-   **Jenkins execution:** A `Jenkinsfile` checks out source code, runs
    `mvn clean test`, and archives files under `reports/**`.

## Project Structure

``` text
AutomationFramework/
├── Jenkinsfile
├── pom.xml
├── ExecutionFlow/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/             # BasePage and common page actions
│   │   │   ├── constants/        # Framework paths and constants
│   │   │   ├── driver/           # WebDriver lifecycle and factory
│   │   │   ├── exceptions/       # Custom framework exceptions
│   │   │   ├── pages/            # Page Object classes
│   │   │   ├── retry/             # Retry support
│   │   │   ├── testdata/          # Test data models
│   │   │   └── utilities/         # Config, waits, Excel, reports, screenshots
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.xml
│   └── test/
│       ├── java/
│       │   ├── hooks/             # Cucumber setup and teardown hooks
│       │   ├── runners/           # Cucumber-TestNG runners
│       │   └── stepdefinitions/   # Gherkin step implementations
│       └── resources/
│           ├── features/          # Gherkin feature files
│           └── test_data/         # Excel test data
└── reports/                       # Extent report and screenshots (generated)
```

## Prerequisites

1.  Java Development Kit (JDK) 17.
2.  Apache Maven installed and available on `PATH`.
3.  Google Chrome, Microsoft Edge, or Mozilla Firefox.
4.  Access to the application under test.

The project uses WebDriverManager to manage browser driver binaries.

## Configuration

Update `src/main/resources/config.properties` before execution:

``` properties
app.login.url=<application-login-url>
app.home.url=<application-home-url>

execution.environment=TEST
execution.browser=chrome
execution.headless=false
execution.explicit.wait=10

retry.enabled=true
retry.count=2
```

Supported browser values in the current driver factory are `chrome`,
`edge`, and `firefox`. Chrome headless mode is controlled through
`execution.headless`.

Do not commit real credentials, tokens, or other secrets. Keep test
credentials in a safe test-data source and use CI credentials/secrets
for Jenkins.

## Run Tests Locally

From the project root:

``` bash
mvn clean test
```

The Jenkinsfile currently uses this same Maven command.

To override a configuration value for a run, pass a JVM system property
using the key supported by `ConfigReader`, for example:

``` bash
mvn clean test -Dexecution.browser=firefox
```

The current feature runner is configured with the `@login` tag. Review
`src/test/java/runners/TestRunner.java` if you want to change the
selected tag or feature scope.

## Jenkins Pipeline

The repository contains a declarative `Jenkinsfile` with these stages:

1.  **Checkout** -- checks out the source configured for the Jenkins
    job.
2.  **Build & Test** -- executes `mvn clean test` on the Jenkins agent.
3.  **Post action** -- archives artifacts matching `reports/**`,
    including the Extent report and screenshots when they are generated
    in that directory.

### Jenkins setup

1.  Configure a Jenkins agent with JDK 17 and a compatible browser
    installed.
2.  Configure a Maven installation in **Manage Jenkins → Tools** with
    the name `Maven` (matching the `tools` entry in the Jenkinsfile).
3.  Create a Pipeline job connected to this repository and configure it
    to use the repository's `Jenkinsfile`.
4.  Ensure the agent can access the application under test and has the
    required browser/display or headless configuration.
5.  Run the pipeline and review the console output and archived
    artifacts.

**Agent note:** The current Jenkinsfile uses the Windows `bat` step. Use
a Windows Jenkins agent as written. For a Linux agent, change the
command step to `sh 'mvn clean test'`.

## Reports and Debugging

-   Extent report: `reports/ExtentReport.html`
-   Screenshots: `reports/screenshots/`
-   Cucumber HTML report: `target/cucumber-report.html`
-   Cucumber JSON report: `target/cucumber.json`
-   Maven Surefire / TestNG output: `target/surefire-reports/`
-   Framework log: `logs/framework.log` (depending on the Log4j2
    configuration)

When a scenario fails, the Cucumber hooks capture a screenshot and
attach it to the Cucumber and Extent reports. Review the report,
screenshot, and execution log when investigating failures.

## Current Example Scenarios

The checked-in feature files include examples for:

-   Successful login with valid credentials.
-   Login with invalid password.
-   Login with invalid username.
-   Login with invalid username and password.
-   Employee search.

These examples use the OrangeHRM demo application configured in
`config.properties`. Update the URLs and test data for another
environment or application.

## Notes on Current Implementation

-   The TestNG Cucumber runner currently sets
    `@DataProvider(parallel = false)`. The driver factory uses
    `ThreadLocal`, but parallel scenario execution is **not enabled by
    the current runner configuration**.
-   The repository includes retry-related classes and a separate
    Cucumber failed-scenario runner. The Jenkinsfile currently runs
    `mvn clean test`; it does not define a separate failed-scenario
    rerun stage.
-   The Jenkinsfile archives `reports/**`. Cucumber and Surefire outputs
    are written under `target/`; add `target/**` to the archive pattern
    if those reports should also be retained as Jenkins artifacts.
-   The uploaded project snapshot does not contain a GitHub Actions
    workflow file. Do not describe GitHub Actions as configured for this
    repository unless a workflow is added.
-   Review generated folders such as `target/`, `test-output/`, and
    run-specific reports before committing. Prefer committing source,
    configuration templates, and selected documentation rather than
    stale execution output.

## Author

**Nilesh Pawar**

GitHub: https://github.com/npawar8881-commits/AutomationFramework
