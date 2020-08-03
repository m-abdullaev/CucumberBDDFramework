package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import poms.CitizenshipPOM;
import poms.MenuPOM;

public class _03_CitizenshipSteps {
    private CitizenshipPOM citizenship;

    @Given("^I navigate to citizenship screen$")
    public void i_navigate_to_country_screen()  {
        citizenship =new CitizenshipPOM();
        MenuPOM menu = new MenuPOM();
        menu.waitAndClick(menu.setupMenuLocator);
        menu.waitAndClick(menu.parametersMenuLocator);
        menu.waitAndClick(menu.citizenshipMenuLocator);

    }

    @And("^\"([^\"]*)\" citizenship doesn't exist$")
    public void countryDoesnTExist(String countryName) {
        citizenship.waitAndSendKeys(citizenship.nameSearchLocator, countryName);
        citizenship.waitAndClick(citizenship.searchButtonLocator);
        citizenship.waitForProgressBar();

        citizenship.deleteAllElementsFromTable();
    }

    @Then("^citizenship is successfully created$")
    public void country_is_successfully_created()  {
        String actual = citizenship.waitForNewAndGetText(citizenship.alertDialogLocator);
        Assert.assertEquals(actual, "Citizenship successfully created");
    }

    @When("^I create \"([^\"]*)\" citizenship$")
    public void i_create_country(String countryName) {
        citizenship.waitAndClick(citizenship.createButtonLocator);
        citizenship.waitAndSendKeys(citizenship.nameInputLocator, countryName);
        citizenship.waitAndClick(citizenship.saveButtonLocator);
    }
}
