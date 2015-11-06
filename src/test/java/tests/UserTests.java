package tests;
import user.User;
import junit.framework.TestCase;


public class UserTests extends TestCase{
	private User user;
	
	protected void setUp() throws Exception {
		user = new User();
	}

	public void testSetAndGetUsername() {
		String testUsername = "aUsername";
		assertNull(user.getUsername());
		user.setUsername(testUsername);
		assertEquals(testUsername, user.getUsername());
	}
	
	public void testAndGetPassword() {
		String testPassword = "aPassword";
		assertNull(user.getPassword());
		user.setPassword(testPassword);
		assertEquals(testPassword, user.getPassword());
	}
}
