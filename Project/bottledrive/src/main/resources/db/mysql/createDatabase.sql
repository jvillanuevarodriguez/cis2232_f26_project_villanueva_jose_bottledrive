CREATE DATABASE IF NOT EXISTS cis2232_bottledrive
    CHARACTER SET utf8mb4;

USE cis2232_bottledrive;

CREATE TABLE bottle_donation (
                                 id INT NOT NULL AUTO_INCREMENT
        COMMENT 'Unique donation identifier',

                                 createdDateTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
                                     COMMENT 'Set when the record is saved',

                                 depositorName VARCHAR(100) NOT NULL
                                     COMMENT 'Person who brought in the containers',

                                 donationDate DATE NOT NULL
                                     COMMENT 'Date of the donation',

                                 smallContainerCount INT UNSIGNED NOT NULL
        COMMENT 'Number of containers under 500 mL',

                                 largeContainerCount INT UNSIGNED NOT NULL
        COMMENT 'Number of containers 500 mL and over',

                                 smallContainerRate DECIMAL(7,2) NOT NULL
                                     COMMENT 'Deposit paid per small container',

                                 largeContainerRate DECIMAL(7,2) NOT NULL
                                     COMMENT 'Deposit paid per large container',

                                 smallRefund DECIMAL(12,2) NOT NULL
                                     COMMENT 'Calculated refund for small containers',

                                 largeRefund DECIMAL(12,2) NOT NULL
                                     COMMENT 'Calculated refund for large containers',

                                 totalRefund DECIMAL(12,2) NOT NULL
                                     COMMENT 'Calculated total refund',

                                 notes TEXT NULL
        COMMENT 'Optional notes',

                                 PRIMARY KEY (id)
) COMMENT = 'Donations received during a fundraising bottle drive';