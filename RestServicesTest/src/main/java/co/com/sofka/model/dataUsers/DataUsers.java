package co.com.sofka.model.dataUsers;

import co.com.sofka.model.ReqRes.JobModel;
import com.github.javafaker.Faker;

public class DataUsers {

    public static JobModel dataUsers(){

        Faker faker = new Faker();
        String name = faker.name().firstName();
        String job = faker.job().field();

        JobModel data = new JobModel();
        data.setUserName(name);
        data.setJobTitle(job);

        return data;
    }

}
