package com.sa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage{
//Declaration
	
	@FindBy(xpath="//a[.='Jetzt kostenlos registrieren']")
	private WebElement jetztKostenlosRegistrierenBtn;
	
	@FindBy(xpath="//h2[.='Kostenlos registrieren']")
	private WebElement kostenlosRegistrierenHeader;
	
	@FindBy(xpath="//span[.='Frau*']")
	private WebElement frauRadioBtn;
	
	@FindBy(xpath="//span[.='Herr*']")
	private WebElement herrRadioBtn;
	
	@FindBy(xpath="//input[@name=\"firstName\"]")
	private WebElement vorNameIp;
	
	@FindBy(xpath="//input[@name=\"lastName\"]")
	private WebElement nachNameIp;
	
	@FindBy(xpath="//input[@name=\"email\"]")
	private WebElement emailIp;
	
	@FindBy(xpath="//h2[.='Kostenlos registrieren']/../..//input[@name=\"password\"]")
	private WebElement regPwdIp;
	
	@FindBy(xpath="//span[.='Geburtstag']")
	private WebElement geburtstagLbl;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Tag']")
	private WebElement geburtstagTagDrpDwn;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Monat']")
	private WebElement geburtstagMonatDrpDwn;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Jahr']")
	private WebElement geburtstagJahrDrpDwn;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Tag']//span")
	private List<WebElement> geburtstagTagOpts;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Monat']//span")
	private List<WebElement> geburtstagMonatOpts;
	
	@FindBy(xpath="//span[.='Geburtstag']/../..//div[@aria-label='Geburtsdatum - Jahr']//span")
	private List<WebElement> geburtstagJahrOpts;
	
	@FindBy(xpath="(//h2[.='Kostenlos registrieren']/../..//input[@type=\"checkbox\"])[1]")
	private WebElement vorteilenGutscheinenChkBox;
	
	@FindBy(xpath="((//h2[.='Kostenlos registrieren'])/../..//span[@class=\"m-InputCheckbox__label u-font-weight--normal u-no-margin a-h4\"])[2]")
	private WebElement AbgDatenschutzbestimmungenChkBox;
	
	@FindBy(xpath="//button[.='Kundenkonto erstellen']")
	private WebElement kundenkontoErstellenBtn;
	
	@FindBy(xpath="//h2[.='Mit Kundenkonto einloggen']")
	private WebElement mitKundenkontoEinLoggenLbl;
	
	@FindBy(xpath="//h2[.='Mit Kundenkonto einloggen']/..//input[@type=\"email\"]")
	private WebElement einloggenEmailIp;
	
	@FindBy(xpath="//h2[.='Mit Kundenkonto einloggen']/..//input[@type=\"password\"]")
	private WebElement einloggenPwdIp;
	
	@FindBy(xpath="//button[.='Anmelden']")
	private WebElement anmeldenBtn;
	
	@FindBy(xpath="//button[.='Hier klicken']")
	private WebElement hierKlickenBtn;
	
	@FindBy(xpath="//span[contains(.,'Kundennummer')]")
	private WebElement kundennummerLbl;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilization
	public void createUserAcct(String genderOpt,String vorName,String nachName,String email,String pwd,String expTag,String expMonat,String expJahr) throws InterruptedException{
		jetztKostenlosRegistrierenBtn.click();
		kostenlosRegistrierenHeader.isDisplayed();
		frauRadioBtn.click();
		vorNameIp.sendKeys(vorName);
		nachNameIp.sendKeys(nachName);
		emailIp.sendKeys(email);
		regPwdIp.sendKeys(pwd);
		Thread.sleep(5000);
		selectDateOfBirth(expTag,expMonat,expJahr);
		Thread.sleep(1200);
		AbgDatenschutzbestimmungenChkBox.click();			
		kundenkontoErstellenBtn.click();
		Thread.sleep(1000);
		valUserIsLoggedInSuccessfully();
	}	
	
	public void selectDateOfBirth(String expTag,String expMonat,String expJahr) {
		geburtstagTagDrpDwn.click();
		for(int i=0;i<geburtstagTagOpts.size();i++) {
			String actOpt=geburtstagTagOpts.get(i).getText();
				if(actOpt.equals(expTag)) {
					geburtstagTagOpts.get(i).click();
				}
		}		
		geburtstagMonatDrpDwn.click();
		System.out.println("Month options:"+geburtstagMonatOpts.size());
		for(int i=0;i<geburtstagMonatOpts.size();i++) {			
			String actOpt=geburtstagMonatOpts.get(i).getText();
				if(actOpt.equals(expMonat)) {
					geburtstagMonatOpts.get(i).click();
				}
		}
		geburtstagJahrDrpDwn.click();
		for(int i=0;i<geburtstagJahrOpts.size();i++) {
			String actOpt=geburtstagJahrOpts.get(i).getText();
				if(actOpt.equals(expJahr)) {
					geburtstagJahrOpts.get(i).click();
				}
		}		
	}
	
	public void loginWithCustAcct(String email,String pwd) throws InterruptedException {
		Thread.sleep(2000);
		einloggenEmailIp.sendKeys(email);
		einloggenPwdIp.sendKeys(pwd);
		Thread.sleep(2000);
		hierKlickenBtn.click();
		Thread.sleep(1000);
		anmeldenBtn.click();		
		Thread.sleep(2000);
	}
	
	public void valUserIsLoggedInSuccessfully() {
		Assert.assertTrue(kundennummerLbl.isDisplayed(),"User is logged in successfully" );
	}
	
	public void valUserIsNotLoggedIn() {
		Assert.assertTrue(jetztKostenlosRegistrierenBtn.isDisplayed(),"User is not logged in");
	}
}

