package co.com.sofka.runner.rest;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/rest/userInfoJsonPlaceHolder.feature"},
        glue = {"co.com.sofka.stepdefinition.rest"}
)
public class userInfoJsonPlaceHolderTest {
}
