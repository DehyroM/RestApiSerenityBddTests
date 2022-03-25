package co.com.sofka.model.userdata;

import co.com.sofka.model.ReqRes.JobModel;
import com.github.javafaker.Faker;

public class UserData {

    private static Faker faker;
    private static String name;
    private static String job;
    private static JobModel data;

    public static JobModel userData(){

        faker = new Faker();
        name = faker.name().firstName();
        job = faker.job().field();
        data = new JobModel();
        data.setUserName(name);
        data.setJobTitle(job);

        return data;
    }
}
