package persianutils.text;

import static java.lang.Character.isDigit;

public class OnNumContextSwitchTokenizer extends OnContextSwitchTokenizer {
    @Override
    boolean isContextSwitch(char l, char r) {
        return logicalXor(isDigit(l), isDigit(r));
    }

    private boolean logicalXor(boolean lb, boolean rb) {
        return !lb && rb || !rb && lb;
    }
}
