package DocumentInterfaces;

public interface ExcelDocument {
    void open();
    void save();
    void close();
    void addFormula();
    String getExcelVersion();
}
