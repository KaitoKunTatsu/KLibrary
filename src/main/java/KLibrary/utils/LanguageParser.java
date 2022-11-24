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

public class LanguageParser {

    String[] terminals, nonTerminalsVariables, productionRules;

    /**
     * {
     *     "terminals": [],
     *     "non-terminal_variables": [],
     *     "production_rules": []
     * }
     * */
    public LanguageParser(InputStream pGrammarJSONStream) throws JSONException {
        JSONObject lJSON= new JSONObject(new JSONTokener(pGrammarJSONStream));

        terminals = initArray(lJSON.getJSONArray("terminals"));
        nonTerminalsVariables = initArray(lJSON.getJSONArray("non-terminal_variables"));
        productionRules = initArray(lJSON.getJSONArray("production_rules"));
    }

    private String[] initArray(JSONArray pJSONArray) {
        return SortUtils.quickSort(pJSONArray.toList().toArray(new String[0]));
    }

    public LanguageParser(String pGrammarJSONPath) throws IOException{
        this(new FileInputStream(pGrammarJSONPath));
    }

    /**
     * Scanns a
     * */
    public List<String> scan(String pInput) {
        List<String> lTerminalList = new ArrayList<>();
        int lStart = 0;
        int lEnd = 1;
        while (lEnd <= pInput.length()) {
            String lCurrentSubstring = pInput.substring(lStart,lEnd);
            if (isTerminal(lCurrentSubstring) != -1) {
                lTerminalList.add(lCurrentSubstring);
                lStart = lEnd;
            }
            ++lEnd;
        }
        if (lEnd-lStart > 1) return null;

        return lTerminalList;
    }

    public boolean parse(String pInput) {
        return parse(scan(pInput));
    }

    public boolean parse(List<String> pScannedInput) {
        if (pScannedInput == null) return false;

        int lStart = 0;
        int lEnd = 1;
        while (lEnd < pScannedInput.size()) {

        }

        return true;
    }

    public int isTerminal(String pTerminal) {
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



    public static void main(String[] args) throws IOException {
        LanguageParser sut = new LanguageParser("src/main/java/KLibrary/utils/grammar.json");
        List<String> strs = sut.scan("1222");
        if (strs == null) System.out.println("invalid");
        else
            for (String str : strs) System.out.println(str);
    }
}
