package ucas.yzc;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

public class SCTokenizerFactories {

    public static class SCGolangTokenizerFactory extends AbstractTokenizerFactory {
        public SCGolangTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
            super(indexSettings, settings, name);
        }
        @Override
        public Tokenizer create() {
            return new SCTokenizer(CodeType.Golang);
        }
    }

    public static class SCJavaTokenizerFactory extends AbstractTokenizerFactory {
        public SCJavaTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
            super(indexSettings, settings, name);
        }
        @Override
        public Tokenizer create() {
            return new SCTokenizer(CodeType.Java);
        }
    }

    public static class SCPythonTokenizerFactory extends AbstractTokenizerFactory {
        public SCPythonTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
            super(indexSettings, settings, name);
        }
        @Override
        public Tokenizer create() {
            return new SCTokenizer(CodeType.Python);
        }
    }
}
