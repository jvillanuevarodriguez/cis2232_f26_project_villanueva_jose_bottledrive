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

INSERT INTO bottle_donation (
    depositorName, donationDate,
    smallContainerCount, largeContainerCount,
    smallContainerRate, largeContainerRate,
    smallRefund, largeRefund, totalRefund, notes
) VALUES
      ('John Smith', '2026-11-20', 100, 50, 0.10, 0.20,
       10.00, 10.00, 20.00, 'First donation of the season'),
      ('Mary Jones', '2026-11-21', 250, 100, 0.10, 0.20,
       25.00, 20.00, 45.00, 'Large family donation'),
      ('Robert Brown', '2026-11-22', 75, 25, 0.10, 0.20,
       7.50, 5.00, 12.50, 'Regular weekly donation'),
      ('Susan MacDonald', '2026-11-23', 500, 200, 0.10, 0.20,
       50.00, 40.00, 90.00, 'Community fundraising collection'),
      ('David Wilson', '2026-11-24', 40, 60, 0.10, 0.20,
       4.00, 12.00, 16.00, NULL);