package utils;

import dto.ProjectData;
import dto.WebhookData;

public class TestDataFactory {

    public static ProjectData createProjectData() {
        return new ProjectData();
    }

    public static WebhookData createWebhookData(ProjectData projectData) {
        return new WebhookData(
                projectData.getWebhookUrl(),
                projectData.getWebhookUrl(),
                projectData.getWebhookMethod(),
                projectData.getWebhookContentType(),
                projectData.getWebhookHeaders(),
                projectData.getWebhookBody(),
                "test",
                projectData.getWebhookEvent()
        );
    }
}
