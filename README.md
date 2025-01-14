# Checklist for TestRail Site

## Login
- Verify successful login with valid credentials.
- Verify unsuccessful login with invalid credentials.
    - Verify error message display when email field is empty.
    - Verify error message display when password field is empty.
    - Verify error message display when password is incorrect.
- Verify error message display when email is incorrect.

## Project Management
- Verify successful opening of the project page for the specified project number.
    - Verify successful creation of a new project with the specified name.
    - Verify display of the project creation message.
- Verify successful editing of an existing project.
    - Verify setting access and defects.
    - Verify adding references and user variables.
    - Verify adding a webhook.
    - Verify display of the project update message.

## Milestones
- Verify successful opening of the milestones page for the specified project number.
- Verify successful addition of a completed milestone.
    - Verify setting details of the completed milestone (name, references, parent value).
    - Verify adding a description to the completed milestone.
    - Verify selecting start and end dates for the completed milestone.
    - Verify display of the completed milestone addition message.
- Verify successful addition of an upcoming milestone.
    - Verify setting details of the upcoming milestone (name, references, parent value).
    - Verify selecting an image for the upcoming milestone.
    - Verify selecting start and end dates for the upcoming milestone.
    - Verify display of the upcoming milestone addition message.

## Webhooks
- Verify successful addition of a webhook.
- Verify successful deletion of a webhook.
    - Verify that the webhook is no longer present after deletion.
    - Verify presence of the webhook by name.

## Reports
- Verify successful opening of the reports page for the specified project number.
- Verify successful creation of a new report with the specified name.
    - Verify adding a description to the report.
    - Verify successful selection of grouping changes.
    - Verify successful selection of the time frame.
    - Verify successful inclusion of changes.
    - Verify successful selection of test suites.
    - Verify successful application of filters to test cases.
    - Verify successful addition of columns to the report.
    - Verify successful setting of the test case limit.
    - Verify successful setting of report access.
    - Verify successful scheduling of notifications.
    - Verify successful submission of the report.
- Verify display of the report addition message.

## API Testing
- Verify retrieving user information by email.
- Verify successful test case addition.
    - Verify successful addition of a new test case.
