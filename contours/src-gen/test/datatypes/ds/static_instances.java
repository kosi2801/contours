package test.datatypes.ds;

public class static_instances { 
  private final static String static_intro = "STATIC";
  public final String getStatic_intro() { return static_intro;}

  private final static String[] static_string = {"mz","mz"};
  public final String[] getStatic_string() { return static_string;}

  private final static short[] static_hex = {0x42,0x42};
  public final short[] getStatic_hex() { return static_hex;}

  private byte[] static_byte = new byte[2];
  public byte[] getStatic_byte() { return static_byte;}
  public void setStatic_byte(byte[] attr) { this.static_byte = attr;}

  private comp_b[] static_component = new comp_b[2];
  public comp_b[] getStatic_component() { return static_component;}
  public void setStatic_component(comp_b[] attr) { this.static_component = attr;}

  private Integer[] static_integer = new Integer[2];
  public Integer[] getStatic_integer() { return static_integer;}
  public void setStatic_integer(Integer[] attr) { this.static_integer = attr;}

  private Long[] static_long = new Long[2];
  public Long[] getStatic_long() { return static_long;}
  public void setStatic_long(Long[] attr) { this.static_long = attr;}

  private String static_bcd;
  public String getStatic_bcd() { return static_bcd;}
  public void setStatic_bcd(String attr) { this.static_bcd = attr;}

};
