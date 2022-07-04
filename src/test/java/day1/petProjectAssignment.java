package day1;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class petProjectAssignment {
             
	
	@DataProvider(name="dataset")
	public Object[][] data(){
		Object[][] petData=new Object[2][8];
		petData[0][0]="41";
		petData[0][1]="coderMan";
		petData[0][2]="Siddharth";
		petData[0][3]="Kumar";
		petData[0][4]="coder@abc.com";
		petData[0][5]="abcxyz123";
		petData[0][6]="6784532985";
		petData[0][7]="1";
		petData[1][0]="50";
		petData[1][1]="amitthecoder";
		petData[1][2]="Amit";
		petData[1][3]="Luthra";
		petData[1][4]="amit@xyz.com";
		petData[1][5]="7543896754";
		petData[1][6]="5678453467";
		petData[1][7]="0";
		petData[2][0]="60";
		petData[2][1]="freakyguy";
		petData[2][2]="Prashant";
		petData[2][3]="Kumar";
		petData[2][4]="akhs@hksg.com";
		petData[2][5]="923466789";
		petData[2][6]="1754389540";
		petData[2][7]="1";
		petData[3][0]="70";
		petData[3][1]="sankalpthecoder";
		petData[3][2]="Sankalp";
		petData[3][3]="Kumar";
		petData[3][4]="sankalp@abc.com";
		petData[3][5]="023456349";
		petData[3][6]="9037560890";
		petData[3][7]="1";
	
		return petData;
	}
	
	//Create the user using the data provider
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=1,dataProvider="dataset")
	public void createUser(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		JSONObject obj =new JSONObject();
		
		obj.put("id",id);
		obj.put("username",username);
		obj.put("firstName",firstname);
		obj.put("lastName",lastname);
		obj.put("email",email);
		obj.put("password",password);
		obj.put("phone",phone);
		obj.put("userstatus",userstatus);
		
		
		given().contentType(ContentType.JSON)
		       .body(obj.toJSONString()).
		       when().post("/user")
		       .then().statusCode(200).log().all();
		
	}
	
	//Get user by user name
	@Test(enabled=true,priority=2)
	public void getUser(){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2/"; 
		
		given().get("user/sankalpthecoder").
		then()
		.statusCode(200).log().all();
		
		
		given().get("/user/amitthecoder").
		then()
		.statusCode(200).log().all();
	}
	
	//Account login using the dataProvider (dataset)
	@Test(enabled=true,priority=3,dataProvider="dataset")
	public void LoginUser(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus){
       RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
	
	given().queryParam("username", username)
	.queryParam("password", password).
	when()
	  .get("/user/login").
	then()
	   .statusCode(200)
	   .log().all();
		
		
	}
	
	//Update the user using the put method
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=4)
	public void UpdateUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 
		 JSONObject obj=new JSONObject();
			
		 obj.put("id","41");
			obj.put("username","coderMan");
			obj.put("firstName","Siddharth");
			obj.put("lastName","Kumar");
			obj.put("email","newjourney@abc.com");
			obj.put("password","86390773");
			obj.put("phone","7753974568");
			obj.put("userstatus","0");
			
			given().contentType(ContentType.JSON)
			   .body(obj.toJSONString())
			.when()
			   .put("/user/coderMan")
			   .then()
			   .statusCode(200).log().all();
	}
	
	//Delete the user 
	@Test(enabled=true,priority=5)
	public void deleteUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2/"; 
		 
		 given().delete("user/freakyguy")
		 .then()
		 .statusCode(200)
		 .log().all();
		 
	}
}
