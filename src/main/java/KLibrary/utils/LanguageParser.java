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
    List<List<PRToken>>[] productionRules;

    /**
     *
     *
     * @param pGrammarJSONStream    {@link InputStream} of JSON file
     * @see JSONObject
     * @see JSONTokener
     * */
    public LanguageParser(InputStream pGrammarJSONStream) throws JSONException, IllegalArgumentException {
        this(new JSONObject(new JSONTokener(pGrammarJSONStream)));
    }

    /**
     *
     *
     * @param pGrammarInJSONFormat  String representation of a JSON containing terminals, non-terminal variables and production rules
     * @see JSONObject
     * */
    public LanguageParser(String pGrammarInJSONFormat) throws IllegalArgumentException {
        this(new JSONObject(pGrammarInJSONFormat));
    }

    public LanguageParser(JSONObject pGrammarJSON) {
        terminals = initArray(pGrammarJSON.getJSONArray("terminals"));
        nonTerminalsVariables = initArray(pGrammarJSON.getJSONArray("non-terminal_variables"));
        productionRules = getRules(pGrammarJSON.getJSONArray("production_rules"));

        if (terminals == null || nonTerminalsVariables == null ||productionRules.length == 0) throw new IllegalArgumentException();
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
                final int lIndexInTerminals = getIndex(pInput.substring(lStart,lEnd), terminals);
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

    public boolean parse(List<String> pScannedInput) {
        if (pScannedInput == null) return false;
        return validate(0, 0, pScannedInput) != -1;
    }

    public boolean parse(String pInput) {
        return parse(scan(pInput));
    }

    private int validate(int pIndexInRules, int pIndexInInput, List<String> pInput) {
        boolean lFound = false;
        for (List<PRToken> option : productionRules[pIndexInRules]) {
            if (lFound) break;
            for (int i = 0; i < option.size(); ++i) {
                if (option.get(i).type == PRToken.Type.VARIABLE && validate(option.get(i).indexInArray, pIndexInInput, pInput) == -1) {
                    lFound = false;
                    break;
                }
                else if (!terminals[option.get(i).indexInArray].equals(pInput.get(pIndexInInput)))
                    return -1;
                else
                    lFound = true;
                ++pIndexInInput;
            }
        }
        return lFound ? pIndexInInput : -1;
    }

    public int getIndex(String pElement, String[] pArr) {
        for (int i = pArr.length-1; i >= 0; --i) {
            if (pArr[i].equals(pElement)) return i;
        }
        return -1;
    }

    private String[] initArray(JSONArray pJSONArray) {
        return SortUtils.quickSort(pJSONArray.toList().toArray(new String[0]));
    }

    // Liste<Token> in einer Liste von optionen in einem array von Rules
    private List<List<PRToken>>[] getRules(JSONArray pRules) {
        List<List<PRToken>>[] lRulesList = new ArrayList[pRules.length()];
        for (int i = 0; i < pRules.length(); i++) {
            String[] lRuleOptions = pRules.getString(i).split("\\|");
            lRulesList[i] = new ArrayList<>();

            for (String lRuleOption : lRuleOptions) {
                List<PRToken> lTokenList = new ArrayList<>();
                lRulesList[i].add(lTokenList);

                int lMaxLength = terminals[terminals.length - 1].length();
                if (lMaxLength < nonTerminalsVariables[nonTerminalsVariables.length - 1].length())
                    lMaxLength = nonTerminalsVariables[nonTerminalsVariables.length - 1].length();
                int lStart = 0;
                int lEnd = lMaxLength;
                while (lEnd != lStart) {
                    if (lEnd <= lRuleOption.length()) {
                        final int lIndexInTerminals = getIndex(lRuleOption.substring(lStart, lEnd), terminals);
                        if (lIndexInTerminals != -1) {
                            lTokenList.add(new PRToken(PRToken.Type.TERMINAL, lIndexInTerminals));
                            lStart = lEnd;
                            lEnd += lMaxLength;
                            continue;
                        }
                        final int lIndexInNonTerminalVars = getIndex(lRuleOption.substring(lStart, lEnd), nonTerminalsVariables);
                        if (lIndexInNonTerminalVars != -1) {
                            lTokenList.add(new PRToken(PRToken.Type.VARIABLE, lIndexInNonTerminalVars));
                            lStart = lEnd;
                            lEnd += lMaxLength;
                            continue;
                        }
                    }
                    --lEnd;
                }
            }

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
        LanguageParser sut = new LanguageParser(new FileInputStream("src/main/java/KLibrary/utils/grammar.json"));
        List<String> strs = sut.scan("22442244");
        if (strs == null) System.out.println("invalid");
        else
            for (String str : strs) System.out.println(str);

        System.out.println("\n"+sut.parse(strs));
    }
}
