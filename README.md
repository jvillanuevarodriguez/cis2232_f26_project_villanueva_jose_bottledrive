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
