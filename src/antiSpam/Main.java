package antiSpam;

public class Main {

    public static void main(String[] args) {
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        TextAnalyzer result;
        for(int i=0; i < analyzers.length; i++) {
            if(analyzers[i].equals(Label.OK)) {
                if(i==analyzers.length-1){
                    return Label.OK;
                    break;
                }
            } else {
                return analyzers[i];
                break;
            }
        }
    }

    interface TextAnalyzer {
        Label processText(String text);
    }

    abstract static class KeywordAnalyzer implements TextAnalyzer {

        public final String keywords;
        private final Label Label;

        public KeywordAnalyzer(String keywords, Label Label) {
            this.keywords = keywords;
            this.Label = Label;
        }
        public String getKeywords() {
            return keywords;
        }
        public Label getLabel() {
            return Label;
        }

    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {
        NegativeTextAnalyzer(String keywords, Main.Label Label) {
            super(keywords, Label);
        }
        public Label processText(String text) {
            String negative1 = ":(";
            String negative2 = "=(";
            String negative3 = ":|";
            if ((text.contains(negative1)) || (text.contains(negative2)) || (text.contains(negative3))) {
                return Main.Label.NEGATIVE_TEXT;
            }else{
                return Main.Label.OK;
            }
        };
    }

    static class SpamAnalyzer extends KeywordAnalyzer {
        SpamAnalyzer(String keywords, Main.Label Label) {
            super(keywords, Label);
        }
        public Label processText(String text) {
            if (text.contains(keywords)) {
                return Main.Label.SPAM;
            }else{
                return Main.Label.OK;
            }
        };
    }

    static class TooLongTextAnalyzer implements TextAnalyzer {
        int maxLength;
        public Label processText(String text) {
            if (text.length() > maxLength) {
                return Main.Label.TOO_LONG;
            }else{
                return Main.Label.OK;
            }
        };
    }

    enum Label {
        SPAM,
        NEGATIVE_TEXT,
        TOO_LONG,
        OK
    }
}
