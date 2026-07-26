package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

/**
 * ============================================================================
 * Class Name : EmployeeListPage
 *
 * Purpose:
 * Represents the Employee List page under the PIM module.
 *
 * Responsibilities:
 * 1. Store Employee List page locators.
 * 2. Provide Employee search actions.
 * 3. Verify Employee search results.
 * ============================================================================
 */
public class EmployeeListPage extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(EmployeeListPage.class);

    /**
     * Constructor
     *
     * Initializes Employee List page web elements.
     */
    
    /**
     * Employee Name search textbox.
     */
    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
    private WebElement employeeNameTextBox;

    /**
     * Search button.
     */
    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    /**
     * Employee result table.
     */
    @FindBy(xpath = "//div[@class='oxd-table-body']")
    private WebElement employeeTable;
    
    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
    private WebElement employeeResult;
     
    
    
    public EmployeeListPage() {

        super();

    }
    /**
     * Searches employee by employee name.
     *
     * Need:
     * Performs employee search using the Employee Name filter.
     *
     * Without:
     * Employee search cannot be performed from the Employee List page.
     *
     * @param employeeName Employee name to search.
     */
    public void searchEmployee(String employeeName) {

        logger.info("Searching employee : {}", employeeName);
        
        type(employeeNameTextBox, employeeName);

        click(searchButton);

    }
    
    /**
     * Verifies employee records are displayed.
     *
     * Need:
     * Confirms search returned employee records.
     *
     * Without:
     * Test cannot verify whether the employee search was successful.
     *
     * @return true if employee records are displayed.
     */
    public boolean isEmployeePresent() {

        logger.info("Verifying employee search result.");

        return isDisplayed(employeeTable);

    }
    
    
   

}