/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * .
 * Read the definitions of the blocks
 */
public class BlocksDefinitionReader {

    /**
     * .
     * Read the definitions and return them to the factory
     *
     * @param reader r
     * @return the definitions
     * @throws Exception if load resources fail
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws Exception {
        BlocksFromSymbolsFactory bFactory = new BlocksFromSymbolsFactory();
        BufferedReader is = null;
            try {
            is = new BufferedReader(reader);
            String line;
            Map<String, String> defaults = new TreeMap<String, String>();
            Map<String, String> blocks = new TreeMap<String, String>();
            Map<String, String> spacers = new TreeMap<String, String>();
            while ((line = is.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#") || line.equals("")) {
                    continue;
                }
                if (line.startsWith("default")) {
                    String params = line.substring("default".length());
                    defaults = splitParams(params);
                    continue;
                }
                String startWord = line.substring(0, "bdef".length());
                startWord = startWord.trim();
                String params;
                String symbol;
                switch (startWord) {
                case "bdef":
                    params = line.substring(startWord.length());
                    params = params.trim();
                    blocks = splitParams(params);
                    Map<String, String> tempMap = new TreeMap<String, String>();
                    tempMap.putAll(defaults);
                    tempMap.putAll(blocks);
                    //Look for the key "symbol" and its value (which is the symbol)
                    if (tempMap.containsKey("symbol")) {
                        symbol = tempMap.get("symbol");
                        if (symbol.length() != 1) {
                            throw new RuntimeException("error" + symbol);
                        }
                        tempMap.remove("symbol");
                        //Create the blockCreator which will go to BlocksFromSymbolFactory
                        BlockCreator b = new DefaultBlock();
                        Decorators d = new Decorators();
                        //Sign parameters to check if block has them or not
                        boolean height = false;
                        boolean width = false;
                        boolean fill = false;
                        boolean health = false;
                        boolean fillk = false;
                        int max = -1;
                        List<Integer> l = new ArrayList<Integer>();
                        for (Map.Entry<String, String> entry : tempMap.entrySet()) {
                            b = d.create(b, entry.getKey(), entry.getValue());
                            //Sign as true the current parameter
                            if (entry.getKey().equals("fill")) {
                                fill = true;
                            } else {
                                if (entry.getKey().startsWith("fill-")) {
                                    fillk = true;
                                    String num = entry.getKey().substring("fill-".length());
                                    int x = Integer.parseInt(num);
                                    l.add(x);
                                    if (max < x) {
                                        max = x;
                                    }
                                }
                            }
                            switch (entry.getKey()) {
                            case "width":
                                width = true;
                                break;
                            case "hit_points":
                                health = true;
                                break;
                            case "height":
                                height = true;
                                break;
                            case "stroke":
                                break;
                                default:
                                    break;
                            }
                          }
                        if (!fill) {
                            if (!fillk) {
                                throw new RuntimeException("Error in line: " + line + " no fill param");
                            }
                            Collections.sort(l);
                            for (int i = 1; i <= max; i++) {
                                if (!l.contains(i)) {
                                throw new RuntimeException("Error in line: " + line + " not contains basic fill param");
                                }
                            }
                            fill = true;
                        }
                        //Check that block has all parameters
                        if ((!fill) || (!health) || (!height) || (!width)) {
                            throw new RuntimeException("Error in line: " + line + "\n Status:"
                                    + " width: " + width + " height: " + height
                                    + " hit_points: " + health + " fill: " + fill);
                        }
                        bFactory.addBlockCreator(symbol, b);
                    } else {
                        throw new RuntimeException("No Symbol in line:" + line);
                    }
                    params = null;
                    symbol = null;
                    break;
                case "sdef":
                    params = line.substring(startWord.length());
                    params = params.trim();
                    spacers = splitParams(params);
                    if (spacers.containsKey("symbol")) {
                        symbol = spacers.get("symbol");
                        if (symbol.length() != 1) {
                            throw new RuntimeException("Symbol must be 1 length. Illegal symbol::" + symbol);
                        }
                        spacers.remove("symbol");
                        if (spacers.containsKey("width")) {
                            int width = Integer.parseInt(spacers.get("width"));
                            bFactory.addSpaceWidth(symbol, width);
                        } else {
                            throw new RuntimeException("No Width in line:" + line);
                        }
                    }  else {
                        throw new RuntimeException("No Symbol in line:" + line);
                    }
                    params = null;
                    symbol = null;
                    break;
                default:
                    throw new RuntimeException("Illegal word in:" + line);
                }
            }
            } finally {
                if (is != null) {
                    try {
                    is.close();
                    } catch (IOException e) {
                    System.out.println("Failed close the file !");
                        }
                         }
                         }
         return bFactory;
   }

    /**
     * .
     * Spliting the params
     *
     * @param line line
     * @return a map the contains key and a value
     */
    public static Map<String, String> splitParams(String line) {
        line = line.trim();
        Map<String, String> after = new TreeMap<String, String>();
        String[] params = line.split(" ");
        for (int i = 0; i < params.length; i++) {
            String[] keysAndValues = params[i].split(":");
            String key = keysAndValues[0].trim();
            String value = keysAndValues[1].trim();
            after.put(key, value);
        }
        return after;

    }
}