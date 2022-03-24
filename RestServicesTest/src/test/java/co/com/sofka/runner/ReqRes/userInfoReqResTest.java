package co.com.sofka.runner.ReqRes;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/ReqRes/userInfoReqRes.feature"},
        glue = {"co.com.sofka.stepdefinition.ReqRes"}
)
public class userInfoReqResTest {
}
