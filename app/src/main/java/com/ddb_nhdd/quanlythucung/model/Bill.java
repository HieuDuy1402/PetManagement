package com.ddb_nhdd.quanlythucung.model;

public class Bill {
    private int id_bill;
    private String bill_id;
    private String bill_kind;
    private String bill_person;
    private String bill_date;
    private String bill_total;
    private String bill_note;

    public Bill(String bill_id, String bill_kind, String bill_person, String bill_date, String bill_total, String bill_note) {
        this.bill_id = bill_id;
        this.bill_kind = bill_kind;
        this.bill_person = bill_person;
        this.bill_date = bill_date;
        this.bill_total = bill_total;
        this.bill_note = bill_note;
    }

    public Bill(int id_bill, String bill_id, String bill_kind, String bill_person, String bill_date, String bill_total, String bill_note) {
        this.id_bill = id_bill;
        this.bill_id = bill_id;
        this.bill_kind = bill_kind;
        this.bill_person = bill_person;
        this.bill_date = bill_date;
        this.bill_total = bill_total;
        this.bill_note = bill_note;
    }
    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_kind() {
        return bill_kind;
    }

    public void setBill_kind(String bill_kind) {
        this.bill_kind = bill_kind;
    }

    public String getBill_person() {
        return bill_person;
    }

    public void setBill_person(String bill_person) {
        this.bill_person = bill_person;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_total() {
        return bill_total;
    }

    public void setBill_total(String bill_total) {
        this.bill_total = bill_total;
    }

    public String getBill_note() {
        return bill_note;
    }

    public void setBill_note(String bill_note) {
        this.bill_note = bill_note;
    }
}
