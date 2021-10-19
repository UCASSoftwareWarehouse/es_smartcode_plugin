package ucas.yzc;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

import java.util.HashMap;
import java.util.Map;

public class SCTokenizerFactory extends AbstractTokenizerFactory {
    private final CodeType codeType;

    public SCTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, settings, name);
        String codeTypeStr = settings.get("code_type");
        System.out.println("smartcode get code_type setting = " + codeTypeStr);
        Map<String, CodeType> validCodeTypeStrings = new HashMap<>() {{
            put(CodeType.Golang.getCodeTypeStr(), CodeType.Golang);
            put(CodeType.Java.getCodeTypeStr(), CodeType.Java);
            put(CodeType.Python.getCodeTypeStr(), CodeType.Python);
        }};
        codeType = validCodeTypeStrings.get(codeTypeStr);
    }

    @Override
    public Tokenizer create() {
        if (codeType == null) {
            System.out.println("smartcode no corresponding code type found, using standard tokenizer instead.");
            return new StandardTokenizer();
        }
        System.out.println("smartcode using codeType" + codeType);
        return new SCTokenizer(codeType);
    }
}
