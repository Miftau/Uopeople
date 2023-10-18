CREATE TABLE APPOINTMENT (
APPOINTMENT_ID INTEGER NOT NULL UNIQUE PRIMARY KEY,
PATIENT_ID INT NOT NULL,
FULL_NAME VARCHAR(31),
PHONE INTEGER,
EMAIL VARCHAR(39),
ADDRESS VARCHAR(150),
ADDED_DATE DATE,
DOCTOR_ID INT NOT NULL,
FOREIGN KEY (DOCTOR_ID) REFERENCES DOCTOR(DOCTOR_ID),
FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)

);