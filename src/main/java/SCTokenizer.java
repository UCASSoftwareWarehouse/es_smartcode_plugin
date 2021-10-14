import java.io.IOException;


import org.antlr.v4.runtime.Token;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public final class SCTokenizer extends Tokenizer {

    private SCTokenizerImpl scanner;

    private final CodeType codeType;

    private int skippedPositions;

    private final static int maxTokenLength = 1000000;

    public SCTokenizer(CodeType codeType) {
        super();
        this.codeType = codeType;
        init();
    }

    private void init() {
        this.scanner = new SCTokenizerImpl(input, codeType);
    }

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    private final PositionIncrementAttribute posIncrAtt = addAttribute(PositionIncrementAttribute.class);
    @Override
    public final boolean incrementToken() throws IOException {
        clearAttributes();
        skippedPositions = 0;

        while(true) {
            Token token = scanner.getNextToken();

            if (token.getType() == Token.EOF) {
                return false;
            }

            String tokenText = token.getText();
            if (tokenText.length() <= maxTokenLength) {
                final int start = token.getStartIndex();
                posIncrAtt.setPositionIncrement(skippedPositions+1);
                termAtt.append(tokenText);
                offsetAtt.setOffset(correctOffset(start), correctOffset(start+termAtt.length()));
                return true;
            } else
                skippedPositions++;
        }
    }

    @Override
    public final void end() throws IOException {
        super.end();
        posIncrAtt.setPositionIncrement(posIncrAtt.getPositionIncrement()+skippedPositions);
    }

    @Override
    public void close() throws IOException {
        super.close();
        scanner.reset(input);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        scanner.reset(input);
        skippedPositions = 0;
    }
}
