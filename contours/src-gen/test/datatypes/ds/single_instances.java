package test.datatypes.ds;

public class single_instances { 
  private final static String single_intro = "SINGLE";
  public final String getSingle_intro() { return single_intro;}

  private byte single_byte;
  public byte getSingle_byte() { return single_byte;}
  public void setSingle_byte(byte attr) { this.single_byte = attr;}

  private final static short single_hex = 0x41;
  public final short getSingle_hex() { return single_hex;}

  private comp_b single_component;
  public comp_b getSingle_component() { return single_component;}
  public void setSingle_component(comp_b attr) { this.single_component = attr;}

  private Integer single_integer;
  public Integer getSingle_integer() { return single_integer;}
  public void setSingle_integer(Integer attr) { this.single_integer = attr;}

  private Long single_long;
  public Long getSingle_long() { return single_long;}
  public void setSingle_long(Long attr) { this.single_long = attr;}

  private String single_bcd;
  public String getSingle_bcd() { return single_bcd;}
  public void setSingle_bcd(String attr) { this.single_bcd = attr;}

};
