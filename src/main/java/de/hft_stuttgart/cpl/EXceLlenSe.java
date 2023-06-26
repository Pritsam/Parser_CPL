/* Generated By:JavaCC: Do not edit this line. EXceLlenSe.java */
package de.hft_stuttgart.cpl;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.Instant;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
public class EXceLlenSe implements EXceLlenSeConstants {

    private static Storage dataStorage = new Storage();
    private static Deque<String> queue = new LinkedList<String>();

    private static EXceLlenSe parser = new EXceLlenSe(System.in);

    public static void main(String args[]) throws ParseException {
        File file = new File("src/main/java/de/hft_stuttgart/cpl/input.txt");
        fillCommandQueueFromFile(file);
        System.out.println("*** Given Parsing Commands: " + queue + " ****");
        System.out.println("*** Executing Commands from file " + file.getName() + " ***");

        String givenCommand;
        while ((givenCommand = queue.poll()) != null) {
            System.out.println("*** Parsing  \u005c"" + givenCommand + "\u005c" ****");
            InputStream is = new ByteArrayInputStream( givenCommand.getBytes() );
            parser.ReInit(is);
            parser.ParseOneLine();
            System.out.println();

            System.out.println("*** Displaying the list of variables ***");
            dataStorage.printAllVariables();
            System.out.println();
        }

        System.out.println("*** Finished " + file.getName() + " ***");
        System.out.println();
        System.out.println("*** Displaying the list of variables ***");
        dataStorage.printAllVariables();
    }

    public static void fillCommandQueueFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                queue.offerLast(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  final public void ParseOneLine() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FOR:
      ForLoop();
      break;
    default:
      jj_la1[0] = jj_gen;
      if (jj_2_1(3)) {
        CellAssignment();
      } else {
        InterpretExpression();
      }
    }
    jj_consume_token(0);
  }

  final public Object InterpretExpression() throws ParseException {
                                          Object value = null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
      case STRING:
      case TIMESTAMP:
      case LPAR:
      case COLUMN:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      if (jj_2_2(3)) {
        value = StringExpression();
                System.out.println("String Expression: " + value);
      } else if (jj_2_3(3)) {
        value = NumberExpression();
                System.out.println("Number Expression: " + value);
      } else if (jj_2_4(3)) {
        value = TimestampExpression();
                System.out.println("Timestamp Expression: " + value);
      } else if (jj_2_5(3)) {
        value = CellValue();
                System.out.println("Cell Value: " + value);
        value = VarValue();
                System.out.println("Variable Value: " + value);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
            System.out.println("Expression Output: " + value);
            {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public Object VarValue() throws ParseException {
                               Token t;
    t = jj_consume_token(VAR);
            {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public Object NumberExpression() throws ParseException {
                                       Object value;
    value = NumExpr();
         {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public Double NumExpr() throws ParseException {
                              double num1 = 0; double num2 = 0;
    num1 = term();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ADD:
      case SUB:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ADD:
        jj_consume_token(ADD);
        num2 = term();
                System.out.print("+ ");
                num1 += num2;
        break;
      case SUB:
        jj_consume_token(SUB);
        num2 = term();
                num1 -= num2;
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return num1;}
    throw new Error("Missing return statement in function");
  }

  final public double term() throws ParseException {
                    double num1; double num2;
    num1 = factor();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
      case MOD:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
        jj_consume_token(MUL);
        num2 = factor();
                System.out.print("* ");
                num1 *= num2;
        break;
      case DIV:
        jj_consume_token(DIV);
        num2 = factor();
                System.out.print("/ ");
                 num1 /= num2;
        break;
      case MOD:
        jj_consume_token(MOD);
        num2 = factor();
                System.out.print("% ");
                num1 %= num2;
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return num1;}
    throw new Error("Missing return statement in function");
  }

//    public boolean NumComp(): { double num1; double num2; }
//       {
//            num1 = term()
//            (
//                <GREATER> num2 = term()
//                {
//                    System.out.print("> ");
//                    return num1 > num2;
//                }
//                |
//                <LESSER> num2 = term()
//                {
//                    System.out.print("< ");
//                    return num1 < num2;
//                }
//            )*
//       }

//    public boolean ComparisonExpression(): { boolean value; }
//    {
//        (
//            LOOKAHEAD(2, ">" value = NumComp())
//            {
//                System.out.print("> ");
//                return value;
//            }
//            |
//            LOOKAHEAD(2, "<" value = NumComp())
//            {
//                System.out.print("< ");
//                return value;
//            }
//        )
//    }
  final public double factor() throws ParseException {
                      Token t; double a;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      t = jj_consume_token(NUMBER);
                System.out.printf("%s ", t.image);
                {if (true) return Double.parseDouble(t.toString());}
      break;
    case LPAR:
      jj_consume_token(LPAR);
      a = NumExpr();
      jj_consume_token(RPAR);
          {if (true) return a;}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String ReferenceExpression() throws ParseException {
                                          Token var = null; Object ref = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      var = jj_consume_token(VAR);
      break;
    case NUMBER:
    case LPAR:
      ref = NumberExpression();
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
            String erg = ref != null ? String.valueOf(ref) : String.valueOf(var.image);
            System.out.println("Reference Expression: " + erg);
            {if (true) return erg;}
    throw new Error("Missing return statement in function");
  }

  final public CellReferenceType CellReference() throws ParseException {
                                               Object columnToken; Object rowToken;
    jj_consume_token(COLUMN);
    columnToken = ReferenceExpression();
    jj_consume_token(ROW);
    rowToken = ReferenceExpression();
            String column = String.valueOf(columnToken);
            String row = String.valueOf(rowToken);
            {if (true) return new CellReferenceType(column, row);}
    throw new Error("Missing return statement in function");
  }

  final public Object CellValue() throws ParseException {
                                Value value = null;Object columnToken; Object rowToken;
    jj_consume_token(COLUMN);
    columnToken = ReferenceExpression();
    jj_consume_token(ROW);
    rowToken = ReferenceExpression();
            String column = String.valueOf(columnToken);
            String row = String.valueOf(rowToken);
            CellReferenceType ref = new CellReferenceType(column, row);
            if (ref.isParseAble()) {
                {if (true) return dataStorage.getValue(ref.getReference()).get().getValue();}
            } else {
                {if (true) return ref;}
            }
    throw new Error("Missing return statement in function");
  }

  final public void CellAssignment() throws ParseException {
                                   Token format = null; Object value; CellReferenceType reference;
    reference = CellReference();
    jj_consume_token(EQUALS);
    value = InterpretExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FORMATTED:
      jj_consume_token(FORMATTED);
      format = jj_consume_token(STRING);
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
            if (value instanceof CellReferenceType) {
                System.out.println("CellRefType: ref:" + reference + " val: " + value);
            } else {
                System.out.println("Value try parsing");
                if (reference.isParseAble()) {
                    System.out.println("Value can parsing");
                    if (format != null && !format.image.isEmpty()) {
                        String text = String.format(String.valueOf(value), format.image);
                        dataStorage.setValue(reference.getReference(), new Value(text));
                        System.out.println(reference.getReference() + "= value:" + text);
                    } else {
                        dataStorage.setValue(reference.getReference(), new Value(value));
                        System.out.println(reference.getReference() + "= value:" + value);
                    }
                }
            }
  }

  final public void ForLoop() throws ParseException {
                            Token var; Object start; Object end; Token doToken;
    jj_consume_token(FOR);
    var = jj_consume_token(VAR);
    jj_consume_token(FROM);
    start = NumberExpression();
    jj_consume_token(TO);
    end = NumberExpression();
    doToken = jj_consume_token(DO);
    CellAssignment();
    jj_consume_token(FOREND);
            double startValue = Double.parseDouble(String.valueOf(start));
            double endValue = Double.parseDouble(String.valueOf(end));

            StringBuilder commandBuilder = new StringBuilder();
            Token moveToken = doToken;
            while (!moveToken.image.equals("end")) {
                commandBuilder.append(moveToken.image).append(" ");
                moveToken = moveToken.next;
            }
            System.out.println("commandBuilder:" + commandBuilder);
            String command = commandBuilder.substring(2, commandBuilder.toString().length()-1);
            System.out.println("command:" + command);

            for (double i = endValue; i >= startValue; i--) {

                String replacedAssignment = command.replace(var.image, String.valueOf(i));
                queue.offerFirst(replacedAssignment);
                System.out.println("-- Add for command \u005c"" + replacedAssignment + "\u005c" --");

            }
  }

  final public Object StringExpression() throws ParseException {
                                       Token t; Token op; Token compare; StringBuilder sb = new StringBuilder(); Object value;
    t = jj_consume_token(STRING);
            // Extract the value from the matched token
            String textValue = t.image.substring(1, t.image.length() - 1).replace("''", "'");
            sb.append(textValue);
            value = sb.toString();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUALS:
    case NOT_EQUALS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQUALS:
        op = jj_consume_token(EQUALS);
        break;
      case NOT_EQUALS:
        op = jj_consume_token(NOT_EQUALS);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      compare = jj_consume_token(STRING);
                    String firstTextValue = t.image.substring(1, t.image.length() - 1).replace("''", "'");
                    String secondTextValue = compare.image.substring(1, t.image.length() - 1).replace("''", "'");

                    if (op.image.equals("=")) {
                        {if (true) return value = firstTextValue.equals(secondTextValue);}
                    } else if (op.image.equals("<>")) {
                        {if (true) return value = !firstTextValue.equals(secondTextValue);}
                    }
      break;
    default:
      jj_la1[11] = jj_gen;
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
          ;
          break;
        default:
          jj_la1[10] = jj_gen;
          break label_4;
        }
        jj_consume_token(AND);
        t = jj_consume_token(STRING);
                    // Extract the text value from the matched token
                    String additionalTextValue = t.image.substring(1, t.image.length() - 1).replace("''", "'");
                    sb.append(additionalTextValue);
                    // Convert the StringBuilder to a String
                    value = sb.toString();
      }
    }
            {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public Instant TimestampExpression() throws ParseException {
                                           Token t; Token t2; Instant i = Instant.MIN; int seconds = 0;
    t = jj_consume_token(TIMESTAMP);
                // Extracting the value of timestamp from the matched token
                String timestampValue = t.image;
                // Modifying the timestamp value to include "T" and "Z"
                String modifiedTimestampValue = timestampValue.replace("{", "").replace("}", "").replace(" ", "T") + "Z";
                try {
                    // Parse the modified timestamp into an Instant
                    i = Instant.parse(modifiedTimestampValue);
                    System.out.println(i);
                } catch (DateTimeParseException e) {
                    // Handle the exception if the timestamp is not in the expected format
                    {if (true) throw new ParseException("Invalid timestamp format: " + timestampValue);}
                }
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ADD:
      case SUB:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ADD:
        jj_consume_token(ADD);
        t2 = jj_consume_token(NUMBER);
                    seconds = Integer.parseInt(t2.image);
                    i = i.plusSeconds(seconds);
                    System.out.println("Timestamp new Plus " + i);
        break;
      case SUB:
        jj_consume_token(SUB);
        t2 = jj_consume_token(NUMBER);
                    seconds = Integer.parseInt(t2.image);
                    i = i.minusSeconds(seconds);
                    System.out.println("Timestamp new Minus " + i);
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                {if (true) return i;}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_3R_20() {
    if (jj_scan_token(ADD)) return true;
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3R_15() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_20()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) return true;
    }
    return false;
  }

  private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(8)) return true;
    }
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(20)) {
    jj_scanpos = xsp;
    if (jj_3R_22()) return true;
    }
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3R_31() {
    if (jj_scan_token(MOD)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_28() {
    if (jj_scan_token(LPAR)) return true;
    if (jj_3R_14()) return true;
    if (jj_scan_token(RPAR)) return true;
    return false;
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(STRING)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_scan_token(DIV)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_27() {
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) return true;
    }
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_9() {
    if (jj_scan_token(TIMESTAMP)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_15()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_29() {
    if (jj_scan_token(MUL)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_5() {
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_3R_23()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_24()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3R_26() {
    if (jj_scan_token(SUB)) return true;
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_scan_token(COLUMN)) return true;
    if (jj_3R_16()) return true;
    if (jj_scan_token(ROW)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_scan_token(AND)) return true;
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_25() {
    if (jj_scan_token(ADD)) return true;
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3R_22() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3R_19() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_25()) {
    jj_scanpos = xsp;
    if (jj_3R_26()) return true;
    }
    return false;
  }

  private boolean jj_3R_13() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_17()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_21() {
    if (jj_scan_token(SUB)) return true;
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_scan_token(COLUMN)) return true;
    if (jj_3R_16()) return true;
    if (jj_scan_token(ROW)) return true;
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_3R_18()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_19()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  /** Generated Token Manager. */
  public EXceLlenSeTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4000000,0x40280050,0x600,0x600,0x38000,0x38000,0x200010,0x300010,0x0,0x180,0x40000,0x180,0x600,0x600,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[5];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public EXceLlenSe(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public EXceLlenSe(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new EXceLlenSeTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public EXceLlenSe(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new EXceLlenSeTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public EXceLlenSe(EXceLlenSeTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(EXceLlenSeTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        exists = true;
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.add(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 34; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 5; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
