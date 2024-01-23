/* ----------------------------------------------------------------------
  - Date: 20th.Dec.23                                                   -
  - Auth: We'am Othman                                                  -
  - Desc: Base Page is shared across all pages for initializing driver, -
  -       elements or methods                                           -
  ----------------------------------------------------------------------- */

package com.qacart.todo.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver; // to be shared across the whole package

  /* -------------------------------------------------------------------- *
   * This constructor takes the driver from the test class and initialize *
   * the elements within this driver this ==> refers to the page inherits *
   * from this class (login page, to-do page...), and these pages get the *
   * driver from the test classes.test class gives the driver to the page *
   * class that gives the driver to the base page                         *
   * -------------------------------------------------------------------- */

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this); //This static method is to initialize all the elements within certain page
    }
}
