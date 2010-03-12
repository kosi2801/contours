package test.datatypes.ds;

public class dynamic_instances { 
  private final static String dynamic_intro = "DYNAMIC";
  public final String getDynamic_intro() { return dynamic_intro;}

  private final static String dynamic_string = "mz";
  public final String getDynamic_string() { return dynamic_string;}

  private final static short dynamic_hex = 0x53;
  public final short getDynamic_hex() { return dynamic_hex;}

  private byte[] dynamic_byte;
  public byte[] getDynamic_byte() { return dynamic_byte;}
  public void setDynamic_byte(byte[] attr) { this.dynamic_byte = attr;}

  private comp_b[] dynamic_component;
  public comp_b[] getDynamic_component() { return dynamic_component;}
  public void setDynamic_component(comp_b[] attr) { this.dynamic_component = attr;}

  private Integer[] dynamic_integer;
  public Integer[] getDynamic_integer() { return dynamic_integer;}
  public void setDynamic_integer(Integer[] attr) { this.dynamic_integer = attr;}

  private Long[] dynamic_long;
  public Long[] getDynamic_long() { return dynamic_long;}
  public void setDynamic_long(Long[] attr) { this.dynamic_long = attr;}

  private String dynamic_bcd;
  public String getDynamic_bcd() { return dynamic_bcd;}
  public void setDynamic_bcd(String attr) { this.dynamic_bcd = attr;}

};
