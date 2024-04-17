package dsd.cohort.application.Utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Utility {
  public String consolidateString(List<String> list) {

    StringBuilder sb = new StringBuilder();
    for (String s : list) {
        sb.append(s);
    }
    return sb.toString();
}

public List<String> tokenizeString(String str) {
    
    // slice string into a list of string tokens that are 255 characters or less
    List<String> tokens = new ArrayList<>();
    int index = 0;
    while (index < str.length()) {
        int endIndex = Math.min(index + 255, str.length());
        tokens.add(str.substring(index, endIndex));
        index = endIndex;
    }

    return tokens;
}
}
