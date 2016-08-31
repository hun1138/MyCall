package com.phonebook.hun.mycall.Database.model;

import java.util.ArrayList;

/**
 * Created by hun on 16. 7. 21.
 */
public class PhoneNumberInfo {
    private String id = null;
    private String name = null;

    private ArrayList<String> phoneNumberList;
    private ArrayList<String> emailList;
    private ArrayList<String> addressList;
    private ArrayList<String> memoList;

    private String lastCall = null;

    public PhoneNumberInfo() {
        init();
    }

    public PhoneNumberInfo(String id, String name) {
        init();
        this.id = id;
        this.name = name;
    }

    private void init() {
        this.id = "";
        this.name = "";
        this.phoneNumberList = new ArrayList<String>();
        this.emailList = new ArrayList<String>();
        this.addressList = new ArrayList<String>();
        this.memoList = new ArrayList<String>();
        this.lastCall = "";
    }

    // id handler
    public String getID() {
        return this.id;
    }
    public void setID(String id) {
        this.id = id;
    }

    // name handler
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // phone number handler
    public String getPhoneNumberByIndex(int index) {
        String phoneNumber = "";
        if(0 <= index && index < phoneNumberList.size())
            phoneNumber = phoneNumberList.get(index);
        return phoneNumber;
    }
    public int getPhoneNumberCount() {
        return phoneNumberList.size();
    }
    public void addPhoneNumber(String phoneNumber) {
        phoneNumberList.add(phoneNumber);
    }

    // email handler
    public String getEmailByIndex(int index) {
        String email = "";
        if(0 <= index && index < emailList.size())
            email = emailList.get(index);
        return email;
    }
    public int getEmailCount() {
        return emailList.size();
    }
    public void addEmail(String email) {
        emailList.add(email);
    }

    // address handler
    public String getAddressByIndex(int index) {
        String address = "";
        if(0 <= index && index < addressList.size())
            address = addressList.get(index);
        return address;
    }
    public int getAddressCount() {
        return addressList.size();
    }
    public void addAddress(String address) {
        addressList.add(address);
    }

    // memo handler
    public String getMemoByIndex(int index) {
        String memo = "";
        if(0 <= index && index < memoList.size())
            memo = memoList.get(index);
        return memo;
    }
    public int getMemoCount() {
        return memoList.size();
    }
    public void addMemo(String memo) {
        memoList.add(memo);
    }

    // last call handler
    public String getLastCall() {
        return this.lastCall;
    }
    public void setLastCall(String lastCall) {
        this.lastCall = lastCall;
    }
}
