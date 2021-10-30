package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
    String employeeId;
    double salary;
    LocalDate effectiveDate;

    public String getEmployee() {
        return employeeId;
    }

    public void setEmployee(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
