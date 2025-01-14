package dto;

import lombok.Data;

@Data
public class ReportsData {
    private String reportName = "Activity Summary";
    private String reportDescription = """
            ||| :Header 1 | :Header 2 | :Header 3 | :Header 4
            || Row 1 .. | R1H2 | R1H3 | R1H4
            || Row 2 .. | R2H2 | R2H3 | R2H4""";
    private String groupBy = "Month";
    private String timeFrame = "This month";
    private String filterForTheTestCases = "Assigned To";
    private String dropdownOption = "Vasia Pupkin";
    private String columnChoose = "Created On";
    private String casesToDisplay = "250";
    private String accessValue = "1";
    private String scheduleOption = "Every week";
    private String dayOption = "Friday";
    private String hourOption = "09:00";
}
