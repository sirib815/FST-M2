package GitHubProject;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class Activities {
	// Declare request specification
	RequestSpecification requestSpec;
	// Declare response specification
	ResponseSpecification responseSpec;
	// Global properties
	String sshKey;
	int sshKeyId;
	@BeforeClass
	public void setUp() {
		// Create request specification
		requestSpec = new RequestSpecBuilder()
				// Set content type
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "token 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")
				// Set base URL
				.setBaseUri("https://api.github.com")
				// Build request specification
				.build();
		sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDVk06MC9OzbyWhS7YTALC08eI+YpzZH0gu9kZLF81Xz08W2AbIk3RwHMlDyURk+Pnc0WWa30OGYFjumETGiaYO78RB8GXz7dzFgZ+PMkrVLw3y69/SbeClLKTrK8ic7IjsSAUiSDjsidEoUNWTL7pFrpL6EL+OyCFX/HzxlyLeRe/K8WT33ST1kEtvpC1+tO1VNil60IdceBNR535AagIYRgo+fBTe3b9iP1s3UIg0QQ4NmfJkmmZlJggluqc+Y/J5x6eltABROEoZF6KkvVhBN0ZWQEDTvC8mpCLhz1vyPi4vFzyryzVMyAGU1U5NmtW5OsdiR96fa5pNDX/0OWKP";
	}
	@Test(priority = 1)
	// Test case using a DataProvider
	public void addKeys() {
		String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + sshKey + "\" }";
		Response response = given().spec(requestSpec) // Use requestSpec
				.body(reqBody) // Send request body
				.when().post("/user/keys"); // Send POST request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		sshKeyId = response.then().extract().path("id");
		// Assertions
		response.then().statusCode(201);
	}
	@Test(priority = 2)
	// Test case using a DataProvider
	public void getKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.when().get("/user/keys"); // Send GET Request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		// Assertions
		response.then().statusCode(200);
	}
	@Test(priority = 3)
	// Test case using a DataProvider
	public void deleteKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}"); // Send GET Request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		// Assertions
		response.then().statusCode(204);
	}
}
