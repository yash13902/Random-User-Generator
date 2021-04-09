package com.example.randomusergenerator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/*public class Information {

    @SerializedName("info")
    @Expose
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

class Info{

    @SerializedName("seed")
    @Expose
    private String seed;
    @SerializedName("results")
    @Expose
    private int results;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("version")
    @Expose
    private String version;


    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}*/

public class Information{
    public List<Result> results;
}

class Name{
    public String title;
    public String first;
    public String last;
}

class Street{
    public int number;
    public String name;
}


class Location{
    public Street street;
    public String city;
    public String state;
    public String country;
    public int postcode;
}

class Login {
    public String username;
    public String password;
}

class Dob{
    public Date date;
    public int age;
}


class Id{
    public String name;
    public Object value;
}

class Result{
    public String gender;
    public Name name;
    public Location location;
    public String email;
    public Login login;
    public Dob dob;
    public String phone;
    public String cell;
    public Id id;
    public String nat;
}