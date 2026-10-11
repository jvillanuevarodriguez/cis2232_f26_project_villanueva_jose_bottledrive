# Bottle Drive Log

CIS-2232 Advanced Object-Oriented Programming 

## Project

**Repository:** `cis2232_f26_project_villanueva_jose_bottledrive`
**Application name:** `bottledrive`
**Base colour:** Sea green (#2E8B57)


## Development team

| Role                 | Name            | Responsibility                                                                                                       |
| -------------------- | --------------- | -------------------------------------------------------------------------------------------------------------------- |
| BA / Business Client | Kay Pitre       | Defines the project requirements, donation fields, reports, and visual colour. Reviews and approves the application. |
| Developer            | Jose Villanueva | Builds the application and maintains this repository.                                                                |
| Project Manager / QA | Steven Nguyen   | Tracks progress and verifies the application against the requirements.                                               |

## Description

Bottle Drive Log is a web application for recording donations received during a fundraising bottle drive. It keeps a history of each donation and calculates the refund amount based on the number and type of containers received and the deposit rate for each type.

## What one record represents

One record represents one donation received on one date. It includes the depositor’s name, the donation date, the number of small and large containers, and the deposit rate for each container type. The application calculates and stores the refund for each type and the total refund for that donation.

## Fields captured

One database table: `bottle_donation`.

| Field | Type | Description |
|---|---|---|
| id | int | Unique row identifier. |
| createdDateTime | datetime | Set by the system when the row is saved. |
| depositorName | String | Name of the person who brought in the containers. |
| donationDate | date | Date of the donation (yyyy-MM-dd). |
| smallContainerCount | int | Number of containers under 500 mL. |
| largeContainerCount | int | Number of containers 500 mL and over. |
| smallContainerRate | decimal | Deposit paid per small container, such as 0.10. |
| largeContainerRate | decimal | Deposit paid per large container, such as 0.25. |
| smallRefund | decimal | Calculated refund for small containers. |
| largeRefund | decimal | Calculated refund for large containers. |
| totalRefund | decimal | Total calculated refund for the donation. |
| notes | String | Optional free-text notes. |

## Calculations

smallRefund = smallContainerCount × smallContainerRate

largeRefund = largeContainerCount × largeContainerRate

totalRefund = smallRefund + largeRefund

All three amounts are rounded to two decimal places and stored with the donation record.

## Validation rules

- Depositor name is required.
- Both container counts must be zero or greater.
- At least one container count must be greater than zero.
- Both container rates must be greater than zero.

## Report requirements

- Total refund collected across a selected date range.
- Refund totals by depositor, so repeat contributors can be recognized.
  

## Version control

GitHub is used to store the project code and track work through Issues. The repository should be public so the instructor and project team can access it.

Repository: [Bottle Drive Log](https://github.com/jvillanuevarodriguez/cis2232_f26_project_villanueva_jose_bottledrive)

## Repository organization

| Deliverable | Description | Location |
|---|---|---|
| Assignment 1 | Console application with file storage. | [Assignment 1](Assignment/cis2232_a1_villanueva_jose/) |
| Assignment 2 | Business calculation with TDD and JUnit tests, including AI-generated tests. | [Assignment 2](Assignment/cis2232_f26_assignment2_bottledrive/) |
| Assignment 3 | Console and JOptionPane threads sharing donation data. | [Assignment 3](Assignment/cis2232_f26_assignment3_bottledrive/) |
| Sprint 1 | Initial version of the Bottle Drive web application. | [Sprint 1](Sprint/Sprint1/bottledrive/) |
| Project requirements | Topic document provided by the BA. | [Requirements](Project/Project_Topic_Document_Pitre_Kay.docx) |

## Sprint 1 — Initial web application

Sprint 1 has been completed and graded.

The deliverables included:
- The README with project information and required fields.
- The project colour specified by the BA.
- The application name and server port 8080.
- The createDatabase.sql script.

The Sprint 1 web application is stored in `Sprint/Sprint1/bottledrive`.
The BA's topic document is stored in `Project`.
Future sprint submissions will be organized under the `Sprint` folder.
