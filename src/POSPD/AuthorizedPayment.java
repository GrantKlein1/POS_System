package POSPD;
/**
 * represents the authorization of a credit or check payment type
 */
public class AuthorizedPayment extends Payment
{

	/**
	 * the code that verifies the payment method was valid
	 */
	private String authorizationCode;

	public String getAuthorizationCode()
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}

	/**
	 * checks if the payment is valid by checking if the authorization code matches
	 * @return returns true if the payment is valid, false if it is not
	 */
	public Boolean isAuthorized()
	{
		return true;				//definitely wrong
	}

	/**
	 * checks if the payment type counts as cash
	 * @return returns true if the payment type counts as cash, false if it cannot
	 */
	public Boolean countsAsCash()
	{
		return false;				//also wrong
	}

}