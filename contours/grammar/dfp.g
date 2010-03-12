/****
 SableCC Grammar for DynamicFileParser
 *****/

Package at.fhj.swd07.contours;

/***********
 Helper Tokens are "Shortcuts" for later usage in the grammar.
 Language will be case-insensitive, so map each char. 
 ***********/  
Helpers 
  a = 'a' | 'A' ;
  b = 'b' | 'B' ;
  c = 'c' | 'C' ;
  d = 'd' | 'D' ;
  e = 'e' | 'E' ;
  f = 'f' | 'F' ;
  g = 'g' | 'G' ;
  h = 'h' | 'H' ;
  i = 'i' | 'I' ;
  k = 'k' | 'K' ;
  l = 'l' | 'L' ;
  m = 'm' | 'M' ;
  n = 'n' | 'N' ;
  o = 'o' | 'O' ;
  p = 'p' | 'P' ;
  r = 'r' | 'R' ;
  s = 's' | 'S' ;
  t = 't' | 'T' ;
  u = 'u' | 'U' ;
  v = 'v' | 'V' ;
  w = 'w' | 'W' ;
  x = 'x' | 'X' ;
  y = 'y' | 'Y' ;
  z = 'z' | 'Z' ;
  cr = 13 ; // carriage return
  lf = 10 ; // line feed
  eol = (cr | cr lf) ; 
  tab = 9 ;  // tab char
  ascii_char = [32 .. 127] ;
  blank = ' ' ;
  any_char = [0x0 .. 0xfffff];
  digit = ['0'..'9'] ;
  hex_digit = [['0'..'9'] + [['a' .. 'f'] + ['A' .. 'F']]] ;
  single_quote = '"' ;
  letter = [['a'..'z'] + ['A'..'Z']] ;
  not_quote = [ascii_char - single_quote] ;
  double_quote = '"' '"' ;
  hash = '#' ; 

/***********
 Tokens are the reserved words for the grammar.
 ***********/  
Tokens
  /* reserved words */
  definition_identifier = i d e n t i f i e r ; /* identifies the parse-ruleset */ 
  start = s t a r t ; /* defines which token is the start of the parsing */
  output_path = o u t p u t '_' p a t h ; /* defines where the package folder for the created classes will be */ 
  
  /* elements of a file */
  eof = e o f ; /* meta-identifier for the end-of-file flag */
  byte = b y t e ;
  integer = i n t ;
  long = l o n g ;
  bcd = b c d ;

  /* Symbol separators */
  dot = '.' ;
  comma = ',' ;
  colon = ':' ;
  l_paren = '(' ;
  r_paren = ')' ;
  l_brace = '{' ;
  r_brace = '}' ;
  dotdot = '..' ;
  l_bracket = '[' ;
  r_bracket = ']' ;
  semicolon = ';' ;
  definition_separator = '::=' ; 
    
  /* Symbols and numbers */
  identifier = letter ( '_' | letter | digit )* ;
  package_identifier = letter ( '_' | letter | digit )* ('.' letter ( '_' | letter | digit )* )* ; /* same as "identifier (dot identifier)*" but works */  
  character_literal = single_quote (double_quote | not_quote)+ single_quote ;
  integer_literal = digit+ ;
  hex_literal = '0x' hex_digit+ ;
  hex_byte = '0x' hex_digit hex_digit ;

  /* comments and blanks */
  comment = hash [any_char - [cr + lf]]* eol ; 
  blanks = tab | cr | lf | blank | cr lf ;

/***********
 Tokens which are of no use for the parsing
 ***********/  
Ignored Tokens
  comment, blanks;

/***********
 Rules which define the syntax of the file-description
 ***********/  
Productions
	format_definition =
		format_header
		component_definitions;
		
	format_header =
		P.definition_package_identifier P.output_path? start_component; 
	
	definition_package_identifier =
		T.definition_identifier l_paren package_identifier r_paren semicolon;
	
	output_path =
		T.output_path l_paren character_literal r_paren semicolon;
		
	start_component = 
		start l_paren identifier r_paren semicolon; 
		 	
	component_definitions =
		component_definition+;
	
	component_definition = 
		{id} identifier definition_separator component_definition_list semicolon;
		
	component_definition_list =
		{component_definition_element} component_definition_element |
		{component_definition_list} component_definition_list comma component_definition_element;
		
	component_definition_element =
		{component} component_element_type component_element_multiplier_or_context_name? component_element_name? |
		{eof} eof;
		
	component_element_multiplier_or_context_name =
		{multiplier} component_element_multiplier |
		{context_name} component_element_context_name;
		
	component_element_type =
		{static_text} character_literal |
		{static_hex} hex_literal |
		{type} element_type |
		{component} identifier;
		
	element_type =
		{byte} byte |
		{integer} integer |
		{long} long |
		{bcd} bcd;
		
	component_element_multiplier =
		{static_multiplier} l_paren integer_literal r_paren |
		{context_multiplier} l_paren identifier r_paren ;
		
	component_element_name =
		l_brace identifier r_brace;

	component_element_context_name =
		l_bracket identifier r_bracket;
	