package wrappers;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TableWrapper {

    private final String addColumnSelector,
            addRowSelector,
            removeRowXpath,
            childForm,
            formControl;

    public TableWrapper(String addColumnSelector, String addRowSelector, String removeRowXpath, String childForm, String formControl) {
        this.addColumnSelector = addColumnSelector;
        this.addRowSelector = addRowSelector;
        this.removeRowXpath = removeRowXpath;
        this.childForm = childForm;
        this.formControl = formControl;
    }

    public void addColumn() {
        log.info("Adding a column");
        $(addColumnSelector).click();
    }

    public void addRow() {
        log.info("Adding a row");
        $(addRowSelector).click();
    }

    public void removeRow() {
        log.info("Removing a row");
        $x(removeRowXpath).hover().click();
    }

    public void selectOptionInColumn(int columnIndex, String optionText) {
        log.info("Selecting option '{}' in column {}", optionText, columnIndex);
        $(childForm + columnIndex + formControl).selectOption(optionText);
    }
}

