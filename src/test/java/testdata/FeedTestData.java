package testdata;

public class FeedTestData {

    private final String testCaseId;
    private final String content;
    private final String expected;

    public FeedTestData(String testCaseId, String content, String expected) {
        this.testCaseId = testCaseId;
        this.content = content;
        this.expected = expected;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getContent() {
        return content;
    }

    public String getExpected() {
        return expected;
    }

    @Override
    public String toString() {
        return testCaseId + " | " + content + " | " + expected;
    }
}