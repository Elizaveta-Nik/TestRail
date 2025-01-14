package dto;

import lombok.Data;

@Data
public class ProjectData {
    private String user = "Lead",
            access = "No Access",
            defectUrl = "https://github.com/your-repo/issues/%id%",
            referencesUrl = "https://github.com/your-repo/issues/%id%",
            userVariable1 = "test",
            userVariable2 = "project test",
            userVariable3 = "project",
            userVariable4 = "String",
            userVariable5 = "test",
            webhookUrl = "https://github.com/your-repo/issues/%id%",
            webhookMethod = "GET",
            webhookContentType = "application/json",
            webhookHeaders = "content-encoding: br",
            webhookBody = "{\n" +
                    "    \"comment\": {\n" +
                    "        \"id\": 12345,\n" +
                    "        \"author\": \"John Doe\",\n" +
                    "        \"content\": \"This is a new comment\",\n" +
                    "        \"created_at\": \"2024-12-27T13:45:00Z\"\n" +
                    "    },\n" +
                    "    \"project\": {\n" +
                    "        \"id\": 67890,\n" +
                    "        \" name\": \"Project Name\"\n" +
                    "    }\n" +
                    "}\n",
            webhookEvent = "plan_updated";
}
