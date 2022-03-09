package antiSpam;

public class Main {

    public static void main(String[] args) {
    }

    interface TextAnalyzer {
        Label processText(String text);
    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        return Label.OK;
    }

    public class SpamAnalyzer implements TextAnalyzer {
    }

    public class NegativeTextAnalyzer implements TextAnalyzer {
    }

    public class TooLongTextAnalyzer implements TextAnalyzer {
    }

    enum Label {
        SPAM,
        NEGATIVE_TEXT,
        TOO_LONG,
        OK
    }
}
