package KLibrary.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class providing methods for regular grammar (word validation).
 * Define a language via JSON format: <br>
 * {
 *      *     "terminals": [],
 *      *     "non-terminal_variables": [],
 *      *     "production_rules": []
 *      *
 * } <br>
 *
 * (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 * @version	1.3.0 | last edit: 25.11.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class LanguageParser {

    String[] terminals, nonTerminalsVariables;
    PRToken[][] productionRules;

    /**
     *
     *
     * @param pGrammarJSONStream    {@link InputStream} of JSON file
     * @see JSONObject
     * @see JSONTokener
     * */
    public LanguageParser(InputStream pGrammarJSONStream) throws JSONException, IllegalArgumentException {
        JSONObject lJSON= new JSONObject(new JSONTokener(pGrammarJSONStream));

        terminals = initArray(lJSON.getJSONArray("terminals"));
        nonTerminalsVariables = initArray(lJSON.getJSONArray("non-terminal_variables"));
        productionRules = getRules(lJSON.getJSONArray("production_rules"));
        if (productionRules == null || terminals == null || nonTerminalsVariables == null) throw new IllegalArgumentException();
    }

    /**
     *
     *
     * @param pGrammarInJSONFormat  String representation of a JSON containing terminals, non-terminal variables and production rules
     * @see JSONObject
     * */
    public LanguageParser(String pGrammarInJSONFormat) throws IllegalArgumentException {
        JSONObject lJSON= new JSONObject(pGrammarInJSONFormat);

        terminals = initArray(lJSON.getJSONArray("terminals"));
        nonTerminalsVariables = initArray(lJSON.getJSONArray("non-terminal_variables"));
        productionRules = getRules(lJSON.getJSONArray("production_rules"));
        if (productionRules == null || terminals == null || nonTerminalsVariables == null) throw new IllegalArgumentException();
    }


    /**
     *
     *
     * */
    public List<String> scan(String pInput) {
        final List<String> lTerminalList = new ArrayList<>();
        final int lMaxLength = terminals[terminals.length-1].length();
        int lStart = 0;
        int lEnd = lMaxLength;
        while (lEnd != lStart) {
            if (lEnd <= pInput.length()) {
                final int lIndexInTerminals = getIndexInTerminals(pInput.substring(lStart,lEnd));
                if (lIndexInTerminals != -1) {
                    lTerminalList.add(terminals[lIndexInTerminals]);
                    lStart = lEnd;
                    lEnd += lMaxLength;
                    continue;
                }
            }
            --lEnd;
        }
        return lTerminalList;
    }

    public boolean parse(String pInput) {
        return parse(scan(pInput));
    }

    public boolean parse(List<String> pScannedInput) {
        if (pScannedInput == null) return false;


        return true;
    }

    public int getIndexInTerminals(String pTerminal) {
        for (int i = terminals.length-1; i >= 0; --i) {
            if (terminals[i].equals(pTerminal)) return i;
        }
        return -1;
    }

    public int isVariable(String pVar) {
        for (int i = nonTerminalsVariables.length-1; i >= 0; --i) {
            if (nonTerminalsVariables[i].equals(pVar)) return i;
        }
        return -1;
    }

    private String[] initArray(JSONArray pJSONArray) {
        return SortUtils.quickSort(pJSONArray.toList().toArray(new String[0]));
    }

    private void interpretRule(List<PRToken> pTokenList, String pRule) {

    }

    private List<PRToken>[] getRules(JSONArray pRules) {
        List<PRToken>[] lRulesList = new ArrayList<>[pRules.length()];
        for (int i = 0; i < pRules.length(); i++) {

       }
        return lRulesList;
    }

    private record PRToken(Type type, int indexInArray) {
        private enum Type {
            VARIABLE,
            TERMINAL
        }
    }

    public static void main(String[] args) throws IOException {
        LanguageParser sut = new LanguageParser("src/main/java/KLibrary/utils/grammar.json");
        List<String> strs = sut.scan("224422");
        if (strs == null) System.out.println("invalid");
        else
            for (String str : strs) System.out.println(str);
    }
}
