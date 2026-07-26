package testdata;

//===========POJO==============================//
/*Strong separation between data and data source
Easy maintenance during framework growth
Clean object passing between layers
Supports TestNG DataProvider
Supports Cucumber integration
Supports future Excel/JSON/API/DB data sources without changing test flow*/

//Purpose: Data object / POJO
/*Responsibility:

Hold login test data
Provide getters
No knowledge of:
Excel
Selenium
TestNG/Cucumber
Environment*/

// NEW:
// Immutable POJO.
// Once created, login credentials cannot be modified.

public class LoginData {

	private final String username;

	private final String password;

	public LoginData(String username, String password) {

		this.username = username;
		this.password = password;

	}

	public String getUsername() {

		return username;

	}

	public String getPassword() {

		return password;

	}

}