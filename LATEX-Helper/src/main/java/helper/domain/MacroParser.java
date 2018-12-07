package helper.domain;

import java.util.ArrayList;

/**
 * Splits up a string into parts to mimic the structure of the raw text and adds
 * necessary LaTEX notations. Also makes it possible for microparsers to process
 * individual parts without affecting the overall structure of the text.
 * @author Aleksi
 */
public interface MacroParser extends LTXParser<ArrayList> {
    
}
