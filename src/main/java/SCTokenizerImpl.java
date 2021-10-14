import golexer.GoLexer;
import org.antlr.v4.runtime.*;
import pythonlexer.Python3Lexer;

import java.io.IOException;
import java.io.Reader;

public class SCTokenizerImpl {
    private Reader reader;

    private CharStream charStream;

    private Lexer lexer;

    private CodeType codeType;

    public SCTokenizerImpl(Reader reader, CodeType codeType) {
        init(reader, codeType);
    }

    private void init(Reader reader, CodeType codeType) {
        this.reader = reader;
        this.codeType = codeType;
        this.charStream = null;
        this.lexer = null;
    }

    private void initLexer() throws IOException {
        this.charStream = CharStreams.fromReader(reader);
        switch (codeType) {
            case Python:
                this.lexer = new Python3Lexer(this.charStream); return;
            case Golang:
                this.lexer = new GoLexer(this.charStream); return;
            case Java:
                this.lexer = new GoLexer(this.charStream); return;
        }
    }

    public Token getNextToken() throws IOException {
        if(this.charStream == null || this.lexer == null) {
            initLexer();
        }
        return this.lexer.nextToken();
    }

    public void reset(Reader reader) {
        init(reader, codeType);
    }
}
