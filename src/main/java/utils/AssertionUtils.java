//package utils;
//
//import org.testng.Assert;
//
//public final class AssertionUtils {
//	private AssertionUtils() {
//	}
//
//	public static void assertTrue(boolean condition, String message) {
//		Assert.assertTrue(condition, message);
//	}
//
//	public static void assertFalse(boolean condition, String message) {
//		Assert.assertFalse(condition, message);
//	}
//
//	public static void assertEquals(Object actual, Object expected, String message) {
//		Assert.assertEquals(actual, expected, message);
//	}
//
//	public static void assertNotEquals(Object actual, Object expected, String message) {
//		Assert.assertNotEquals(actual, expected, message);
//	}
//
//	public static void assertContains(String actual, String expected, String message) {
//		Assert.assertTrue(actual != null && actual.contains(expected), message);
//	}
//}