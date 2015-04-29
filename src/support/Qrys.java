package support;

public class Qrys {
	// for getting last auto increment id
	public static String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	public static String makeLikeParam(String param) {
		return "%" + param + "%";
	}
}