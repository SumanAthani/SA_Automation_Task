package com.sa.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sa.generic.GenericMethods;
import com.sa.pageobjects.LoginPage;

public class LoginPageTest extends GenericMethods{
		
		//New User - creates user account
		@Test(priority=1,groups= {"Smoke"})
		public void createUserAcc() throws InterruptedException {	
			LoginPage loginPage=new LoginPage(driver);
			String genderOpt=GenericMethods.getPpt("Configuration\\Config.properties", "GENDER_OPT");
			String tempVorName=GenericMethods.getPpt("Configuration\\Config.properties", "VOR_NAME");
			String nachName=GenericMethods.getPpt("Configuration\\Config.properties", "NACH_NAME");
			String tempEmail=GenericMethods.getPpt("Configuration\\Config.properties", "REG_EMAIL");
			String pwd=GenericMethods.getPpt("Configuration\\Config.properties", "REG_PWD");
			String expTag=GenericMethods.getPpt("Configuration\\Config.properties", "EXP_TAG");
			String expMonat=GenericMethods.getPpt("Configuration\\Config.properties", "EXP_MONAT");
			String expJahr=GenericMethods.getPpt("Configuration\\Config.properties", "EXP_JAHR");
			String ranText=GenericMethods.genRandomText();
			String email="auto"+ranText+tempEmail;
			String vorName=ranText+tempVorName;
			loginPage.createUserAcct(genderOpt, vorName, nachName, email, pwd,expTag,expMonat,expJahr);
		}
		
		//Login with Valid Credentials - Due to captcha field(stays in login page)
		@Test(priority=2,groups= {"Smoke"})
		public void loginWithValidCred() throws InterruptedException {
			String email=GenericMethods.getPpt("Configuration\\Config.properties", "VALID_EMAIL");
			String pwd=GenericMethods.getPpt("Configuration\\Config.properties", "VALID_PWD");
			LoginPage loginPage=new LoginPage(driver);
			loginPage.loginWithCustAcct(email, pwd);
			loginPage.valUserIsLoggedInSuccessfully();
		}
		
		//Login with Invalid Credentials
		@Test(priority=3,groups= {"Regression"})
		public void loginWithInvalidCred() throws InterruptedException {
			String email=GenericMethods.getPpt("Configuration\\Config.properties", "INVALID_EMAIL");
			String pwd=GenericMethods.getPpt("Configuration\\Config.properties", "INVALID_PWD");
			LoginPage loginPage=new LoginPage(driver);
			loginPage.loginWithCustAcct(email, pwd);
			loginPage.valUserIsNotLoggedIn();
		}
}
