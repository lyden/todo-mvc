package org.davidpadbury.modernweb.cucumber.steps;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.davidpadbury.modernweb.cucumber.SpringDependentParticipant;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class CukeSteps extends SpringDependentParticipant {
    @Autowired
    private WebDriver driver;

    @Autowired
    @Qualifier("webapp.url")
    private String webAppUrl;

    @Given("^I am on the main todos page$")
    public void visitMainTodosPage() throws Throwable {
        driver.get(webAppUrl);
    }

    @When("^I enter \"([^\"]*)\" into the new todo box$")
    public void enterTextIntoTodoBox(String text) throws Throwable {
        final WebElement todo = driver.findElement(By.cssSelector("#new-todo"));

        todo.sendKeys(text);
    }

    @When("^press enter in the todo box$")
    public void pressEnter() throws Throwable {
        final WebElement todo = driver.findElement(By.cssSelector("#new-todo"));

        todo.sendKeys(Keys.RETURN);
    }

    @Then("^a new todo is added with \"([^\"]*)\"$")
    public void verifyNewTodoAddedWithText(String text) throws Throwable {
        final List<WebElement> todos = driver.findElements(By.cssSelector("ul#todo-list li"));
        final Iterable<String> labels = Iterables.transform(todos, new TodoLabelSelector());

        assertThat(labels, hasItem(text));
    }

    private final static class TodoLabelSelector implements Function<WebElement, String> {
        @Override
        public String apply(final WebElement input) {
            final WebElement todoLabel = input.findElement(By.cssSelector("label"));
            final String todoText = todoLabel.getText();

            return todoText;
        }
    }
}
