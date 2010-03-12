package windows.executable.ds;

public class component_a { 
  private final static String mz_1 = "MZ";
  public final String getMz_1() { return mz_1;}

  private final static String[] mz_2 = {"MZ","MZ"};
  public final String[] getMz_2() { return mz_2;}

  private final static short hex_1 = 0x42;
  public final short getHex_1() { return hex_1;}

  private final static short[] hex_2 = {0x43,0x43,0x43};
  public final short[] getHex_2() { return hex_2;}

  private after_header_struct ahs_1;
  public after_header_struct getAhs_1() { return ahs_1;}
  public void setAhs_1(after_header_struct attr) { this.ahs_1 = attr;}

  private after_header_struct[] ahs_2 = new after_header_struct[4];
  public after_header_struct[] getAhs_2() { return ahs_2;}
  public void setAhs_2(after_header_struct[] attr) { this.ahs_2 = attr;}

  private byte byte_1;
  public byte getByte_1() { return byte_1;}
  public void setByte_1(byte attr) { this.byte_1 = attr;}

  private byte[] byte_2 = new byte[5];
  public byte[] getByte_2() { return byte_2;}
  public void setByte_2(byte[] attr) { this.byte_2 = attr;}

};
