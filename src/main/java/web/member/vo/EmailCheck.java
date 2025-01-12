package web.member.vo;

public class EmailCheck {
	private static final String EMAIL_REGEX= "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+(\\.[a-zA-Z]{2,})?$";

	public static boolean isCheck(String email) {
		if (email == null || email.isBlank()) {
			return false;			
		}
		return email.matches(EMAIL_REGEX);
	}
}
