package antiSpam;

public class Main {

    public static void main(String[] args) {
    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {

        for(int i=0; i < analyzers.length; i++) {
            Label label = analyzers[i].processText(text);
                if (label != (Label.OK)) {
                    return label;
                }
        }
        return Label.OK;
    }

    interface TextAnalyzer {
        Label processText(String text);
    }

    abstract static class KeywordAnalyzer implements TextAnalyzer {

        abstract protected String[] getKeywords();
        abstract protected Label getLabel();

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
         private final String [] keywords;
         Label Label;

   public SpamAnalyzer (String [] keywords) {
            this.keywords = keywords;
            this.Label = Main.Label.SPAM;
        }
        @Override
        public String[] getKeywords() {
            return this.keywords;
        }
        @Override
        public Main.Label getLabel() {
            return this.Label;
        }
    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {

        String [] negative ={":(", "=(", ":|"};
        Label Label;

        public NegativeTextAnalyzer (String [] negative) {
            this.negative = negative;
            this.Label = Main.Label.NEGATIVE_TEXT;
        }

        @Override
        public String[] getKeywords() {
            return this.negative;
        }
        @Override
        public Main.Label getLabel() {
            return this.Label;
        }
    }

    static class TooLongTextAnalyzer implements TextAnalyzer {

        private int maxLength;

        public TooLongTextAnalyzer(int maxLength){

            this.maxLength = maxLength;
        }
        @Override
        public Label processText(String text) {
            if (text.length() > this.maxLength) {
                return Label.TOO_LONG;
            }else{
                return Label.OK;
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
