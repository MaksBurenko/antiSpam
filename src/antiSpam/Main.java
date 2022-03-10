package antiSpam;

public class Main {

    public static void main(String[] args) {
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        return Label.OK;
    }

    interface TextAnalyzer {
        Label processText(String text);
    }

    public abstract static class KeywordAnalyzer {
        abstract String getKeywords();
        Label getLabel(Label Label){
            return Label;
        };
    }

    public class NegativeTextAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
    }

    public abstract class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
        public Label processText(String text){
            int i = text.indexOf(getKeywords());
            if (i==-1){
                return Label.OK;
            } else{
                return Label.NEGATIVE_TEXT;
            }
        };
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
