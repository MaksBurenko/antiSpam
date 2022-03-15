package antiSpam;

public class Main {

    public static void main(String[] args) {
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for(int i=0; i < analyzers.length; i++) {

            if (!(l.equals(Label.OK))){
                return l;
            }
        }
        return Label.OK;
    }

    interface TextAnalyzer {
        Label processText(String text);
    }

    abstract static class KeywordAnalyzer implements TextAnalyzer {

        protected String [] keywords;
        protected Label Label;

        KeywordAnalyzer (String [] keywords, Label Label) {
            this.keywords = keywords;
            this.Label = Label;
        }
        KeywordAnalyzer () {
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
            for (String keyword : keywords) {
                if (keyword.contains(text)) {
                } else {
                    return Main.Label.SPAM;
                }
            }
            return Main.Label.OK;
        }
    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {
        NegativeTextAnalyzer() {
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

        private int maxLength;
        TooLongTextAnalyzer( int maxLength){
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            if (text.length() > this.maxLength) {
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
