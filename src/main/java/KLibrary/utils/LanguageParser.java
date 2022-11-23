package KLibrary.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageParser {

    String[] terminals;
    String[] nonTerminals;
    String[][] productionRules;

    /**
     * {
     *     "terminals": [],
     *     "non-terminal_variables": [],
     *     "production_rules": []
     * }
     * */
    public LanguageParser(String pGrammarPropertiesPath) throws IOException {
        JSONObject json = new JSONObject(pGrammarPropertiesPath);
        terminals = SortUtils.quickSort((String[]) json.get("terminals"));
        nonTerminals = SortUtils.quickSort((String[]) json.get("non-terminals_variables"));
        productionRules = (String[][]) json.get("production_rules");
    }

    /**
     * Scanns a
     * */
    public List<Token> scan(String pWord) {
        List<Token> lTokenList = new ArrayList<>();

        return lTokenList;
    }

    public boolean parse() {
        return true;
    }

    public static void main(String[] args) {

    }

    private record Token() {}
}
