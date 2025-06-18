package DocumentInterfaces;

public interface WordDocument {
    void open();
    void save();
    void close();
    void formatText();
    String getWordVersion();
}
