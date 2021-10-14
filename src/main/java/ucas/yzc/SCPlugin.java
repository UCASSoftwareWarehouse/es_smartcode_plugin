package ucas.yzc;

import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class SCPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
        extra.put("smartcode_golang_tokenizer", SCTokenizerFactories.SCGolangTokenizerFactory::new);
        extra.put("smartcode_java_tokenizer", SCTokenizerFactories.SCJavaTokenizerFactory::new);
        extra.put("smartcode_python_tokenizer", SCTokenizerFactories.SCPythonTokenizerFactory::new);
        return extra;
    }
}
