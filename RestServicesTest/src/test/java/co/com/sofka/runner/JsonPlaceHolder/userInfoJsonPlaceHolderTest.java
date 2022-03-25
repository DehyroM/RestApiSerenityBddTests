package co.com.sofka.runner.JsonPlaceHolder;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/JsonPlaceHolder/userInfoJsonPlaceHolder.feature"},
        glue = {"co.com.sofka.stepdefinition.JsonPlaceHolder"}
)
public class userInfoJsonPlaceHolderTest {
}
