package dto;

public class TestCaseData {
    public static String getAddTestCaseRequestBody() {
        return "{\n" +
                " \"title\": \"This is a test case\",\n" +
                " \"type_id\": 1,\n" +
                " \"priority_id\": 3,\n" +
                " \"estimate\": \"3m\",\n" +
                " \"refs\": \"RF-1, RF-2\",\n" +
                " \"custom_steps_separated\": [\n" +
                " {\n" +
                " \"content\": \"Step 1\",\n" +
                " \"expected\": \"Expected Result 1\"\n" +
                " },\n" +
                " {\n" +
                " \"content\": \"Step 2\",\n" +
                " \"expected\": \"Expected Result 2\"\n" +
                " },\n" +
                " {\n" +
                " \"shared_step_id\": 3\n" +
                " }\n" +
                " ]\n" +
                "}";
    }

    public static String getUpdateTestCase() {
        return "{ \"priority_id\": 1, \"estimate\": \"5m\" }";
    }
}
