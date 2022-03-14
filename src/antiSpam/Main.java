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

        String [] keywords;
        Label Label;

        KeywordAnalyzer (String [] keywords, Label Label) {
            this.keywords = keywords;
            this.Label = Label;
        }
        String[] getKeywords(){
            return keywords;
        }
        Label getLabel(){
            return Label;
        }

    }

    static class SpamAnalyzer extends KeywordAnalyzer {

        SpamAnalyzer(String[] keywords, Main.Label Label) {
            super(keywords, Label);
        }

        @Override
        public Label processText(String text) {
            for (int i = 0; i < keywords.length; i++) {
                if (keywords[i].contains(text)) {
                } else {
                    return Main.Label.SPAM;
                }
            }
            return Main.Label.OK;
        }
    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {
        NegativeTextAnalyzer(String[] keywords, Main.Label Label) {
            super(keywords, Label);
        }

        @Override
        public Label processText(String text) {
            String negative1 = ":(";
            String negative2 = "=(";
            String negative3 = ":|";
            if ((text.contains(negative1)) || (text.contains(negative2)) || (text.contains(negative3))) {
                return Main.Label.NEGATIVE_TEXT;
            }else{
                return Main.Label.OK;
            }
        }

    }

    static class TooLongTextAnalyzer implements TextAnalyzer {
        TooLongTextAnalyzer() {
            super(int maxLength);
        }
        public Label processText(String text) {
            if (text.length() > maxLength) {
                return Main.Label.TOO_LONG;
            }else{
                return Main.Label.OK;
            }
        }
    }

    enum Label {
        SPAM,
        NEGATIVE_TEXT,
        TOO_LONG,
        OK
    }
}
