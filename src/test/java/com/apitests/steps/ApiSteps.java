package com.apitests.steps;

import com.apitests.config.Config;
import com.apitests.endpoints.ApiEndPoint;
import com.apitests.models.Login;
import com.apitests.models.Post;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ApiSteps {

    private Response response;
    private long id;
    private Post post;
    private String email;
    private String password = Config.password;;


    @Given("page id is {long}")
    public void givenPostId(long id)
    {
        this.id = id;
    }

    @Given("user try to get list of all users")
    public void getAllUsers()
    {
        response = ApiEndPoint.getAllUsers();
    }

    @Given("user try post with next data:")
    public void givenPost(DataTable dataTable) {
        post = convertDataTableToListOfPosts(dataTable).get(0);
    }
    @Given("user try to login with next email {string}")
    public void loginWithEmail (String email) {
        this.email = email;
        System.out.println(this.email);
    }

    @When("user try to get response")
    public void listOfAllUsers () {

    }


    @When("user try to get page by id")
    public void getPostById() {
        System.out.println(id);
        response = ApiEndPoint.getUserById(id);

    }
    @When("user try to login")
    public void login () {
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);
        response = ApiEndPoint.loginUser(login);
    }


    @When("user try to create new post")
    public void createNewPost() {
        response = ApiEndPoint.createPost(post);
    }
    @Then("user logged successfully")
    public void successfullyLogin() {
        response.then().statusCode(200);
    }
    @Then ("list of all users not null")
    public void listNotNull (){
        response.then().statusCode(200).assertThat().body(Matchers.notNullValue());
    }

    @Then("user receive status code {int}")
    public void compareStatusCodes(int statusCode) {
        response.then().statusCode(statusCode);

    }

    @Then("response don't equal zero")
    public void checkThatResponseNotNull() {
        response.then().body(Matchers.notNullValue());
    }
    @Then("response equal zero")
    public void checkThatResponseNull() {
        response.then().body(Matchers.nullValue());
    }
    @Given("validate login:")
    public void verifyLogin(DataTable dataTable) {
        System.out.println(dataTable);
    }

//
    private List<Post> convertDataTableToListOfPosts(DataTable table) {
        return table.asMaps(Object.class, Object.class).stream()
                .map(this::mapToPost)
                .collect(Collectors.toList());
    }
//
    private Post mapToPost(Map<Object, Object> map) {
        if (map.containsKey(Config.ID)) {
            return new Post(Long.parseLong((String) map.get(Config.USER_ID)),
                    Long.parseLong((String) map.get(Config.ID)),
                    (String) map.get(Config.TITLE), (String) map.get(Config.BODY));
        } else {
            return new Post(Long.parseLong((String) map.get(Config.USER_ID)), (String) map.get(Config.TITLE),
                    (String) map.get(Config.BODY));
        }
    }
    
}
