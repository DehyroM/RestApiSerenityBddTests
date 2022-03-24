package co.com.sofka.model.ReqRes;

import com.github.javafaker.Faker;

public class JobModel {

    Faker faker = new Faker();
    String name = faker.name().firstName();
    String job = faker.job().field();

    private String userName;
    private String jobTitle;

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        this.userName = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle() {
        this.jobTitle = job;
    }

}

