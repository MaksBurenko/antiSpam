package antiSpam;

public class Main {

    public static void main(String[] args) {
    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for(int i=0; i < analyzers.length; i++) {
            Label l = Label;
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

        abstract String[] getKeywords();
        abstract Label getLabel();

        public Label processText(String text){
            for (String keyword : this.getKeywords()) {
                if (!(keyword.contains(text))) {
                    return this.getLabel();
                }
            }
            return Main.Label.OK;
        }
    }

    static class SpamAnalyzer extends KeywordAnalyzer {
        protected String [] keywords;
        protected Label Label;
        SpamAnalyzer (String [] keywords,Label Label) {
            this.keywords = keywords;
            this.Label = Label;
        }
        @Override
        public String[] getKeywords() {
            return this.keywords;
        }
        @Override
        public Main.Label getLabel() {
            return Main.Label.SPAM;
        }
    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {

        String [] negative ={":(", "=(", ":|"};

        @Override
        public String[] getKeywords() {
            return this.negative;
        }
        @Override
        public Main.Label getLabel() {
            return Label.NEGATIVE_TEXT;
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
