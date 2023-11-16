CREATE TABLE Specialty (
  SpecialtyNumber INT NOT NULL AUTO_INCREMENT,
  SpecialtyName VARCHAR(255) NOT NULL,
  PRIMARY KEY (SpecialtyNumber)
);

CREATE TABLE Doctor (
  DoctorID INT NOT NULL,
  Name VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  SpecialtyNumber INT NOT NULL,
  Supervisor INT,
  FOREIGN KEY (SpecialtyNumber) REFERENCES Specialty(SpecialtyNumber)
);

CREATE TABLE Patient (
  PatientID INT NOT NULL PRIMARY KEY,
  DoctorID INT NOT NULL,
  Name VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Address VARCHAR(255) NOT NULL,
  AddedDate DATE NOT NULL,
  FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

CREATE TABLE Appointment (
  AppointmentID INT NOT NULL PRIMARY KEY,
  PatientID INT NOT NULL,
  DoctorID INT NOT NULL,
  DateTime DATETIME NOT NULL,
  TreatmentNote VARCHAR(255) NOT NULL,
  FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
  FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);

CREATE TABLE Allergy (
  AllergyID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  AllergyName VARCHAR(255) NOT NULL,
  PRIMARY KEY (AllergyID)
);

CREATE TABLE PatientAllergy (
  PatientAllergyID INT NOT NULL PRIMARY KEY,
  PatientID INT NOT NULL,
  AllergyID INT NOT NULL,
  FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
  FOREIGN KEY (AllergyID) REFERENCES Allergy(AllergyID)
);

CREATE TABLE Medicine (
  MedicineID INT NOT NULL AUTO_INCREMENT ,
  MedicineName VARCHAR(255) NOT NULL,
  Dosage VARCHAR(255) NOT NULL,
  PRIMARY KEY (MedicineID)
);

CREATE TABLE PatientMedicine (
  PatientMedicineID INT NOT NULL,
  PatientID INT NOT NULL,
  MedicineID INT NOT NULL,
  FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
  FOREIGN KEY (MedicineID) REFERENCES Medicine(MedicineID)
);

ALTER TABLE Specialty 
change COLUMN SpecialtyNumber TYPE VARCHAR(5);

-- Populate the Specialty relation
INSERT INTO Specialty (SpecialtyNumber, SpecialtyName)
VALUES
(S1, 'Dermatology'),
(S2, 'Psychiatry'),
(S3, 'Oncology'),
(S4, 'Cardiology'),
(S5, 'Urology'),
(S6, 'Pediatrics');

-- Populate the Doctor relation
INSERT INTO Doctor (Name, Phone, SpecialtyNumber, Supervisor)
VALUES
('Dr. Karen', '555-1212', 6, NULL),
('Dr. John', '555-2934', 2, 1),
('Dr. Robert', '555-6723', 6, 1),
('Dr. David', '555-1745', 4, 1),
('Dr. Mary', '555-6565', 5, 1),
('Dr. Linda', '555-4889', 1, 1),
('Dr. Susan', '555-4581', 3, 1),
('Dr. Zeynep', '555-7891', 4, 1),
('Dr. Mat', '555-7791', 1, 1);

-- Populate the Patient relation
INSERT INTO Patient (DoctorID, Name, Phone, Email, Address, AddedDate)
VALUES
(1, 'Patient Dana', '444-1212', 'p1@email.com', '123 Home St.', '2023-02-01'),
(2, 'Patient Harry', '444-2934', 'p2@email.com', '3435 Main St.', '2023-07-13'),




