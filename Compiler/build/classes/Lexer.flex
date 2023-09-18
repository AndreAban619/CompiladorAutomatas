import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}
/* Identificador */
Letra = [A-Za-z_ÑñÁÉÍÓÚáéíóúÜü]
String = "\"" [^\"]* "\""
Palabra = {Letra}+
Digito = [0-9]
Numero = [0-9][0-9]*
Identificador = ({Digito}{Digito}{Digito})
Ident= "ISC_"{Identificador}
Identnum= "4"{Numero}"4"
Identdec= {Numero}".4"{Numero}"4"


%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/*EMPEZAMOS*/

/* Identificadormio */
{String} {return token(yytext(), "cad_", yyline, yycolumn);}
{Ident} {return token(yytext(), "IDENTIFICADOR", yyline, yycolumn);}
{Identnum} {return token(yytext(), "ent_", yyline, yycolumn);}
{Identdec} {return token(yytext(), "rea_", yyline, yycolumn);}


/*Tipos de datos*/
"ent_ " {return token(yytext(), "ENTERO", yyline, yycolumn);}
"cad_ " {return token(yytext(), "CADENA", yyline, yycolumn);}
"rea_ " {return token(yytext(), "REAL", yyline, yycolumn);}




/*Operadores de agrupacion*/
"(" {return token(yytext(), "PARENTESISA", yyline, yycolumn);}
")" {return token(yytext(), "PARENTESISC", yyline, yycolumn);}
"{" {return token(yytext(), "LLAVEA", yyline, yycolumn);}
"}" {return token(yytext(), "LLAVEC", yyline, yycolumn);}

/*SIGNOS DE PUNTUACION*/
"," {return token(yytext(), "COMA", yyline, yycolumn);}
";" {return token(yytext(), "PUNCOMA", yyline, yycolumn);}

/*operadores*/
 "+"  {return token(yytext(), "SUMA", yyline, yycolumn);}
 "-" {return token(yytext(), "RESTA", yyline, yycolumn);}
 "*" {return token(yytext(), "MULTI", yyline, yycolumn);}
 "/"  {return token(yytext(), "DIVI", yyline, yycolumn);}
"&&"  {return token(yytext(), "YYY", yyline, yycolumn);}
"||" {return token(yytext(), "TAMBIEN", yyline, yycolumn);}
 ">"  {return token(yytext(), "MAYOR", yyline, yycolumn);}
 "==" {return token(yytext(), "IGUALQUE", yyline, yycolumn);}
"!=" {return token(yytext(), "DIFERENTE", yyline, yycolumn);}
">=" {return token(yytext(), "MAYORIGU", yyline, yycolumn);}
"<=" {return token(yytext(), "MENORIGU", yyline, yycolumn);}
";;" {return token(yytext(), "DOSPUN", yyline, yycolumn);}
  

/*OPERADOR DE ASIGNACION*/
"=" {return token(yytext(), "OPASIGNACION", yyline, yycolumn);}

/*FUNCION if*/
"4if4" {return token(yytext(), "if", yyline, yycolumn);}
"4then4" {return token(yytext(), "then", yyline, yycolumn);}
"4else4" {return token(yytext(), "else", yyline, yycolumn);}

/*OPERADORES LOGICOS*/
"&"|"|" {return token(yytext(), "OPLOGICO", yyline, yycolumn);}

/*FINAL*/
/*"FIN" {return token(yytext(), "FINAL", yyline, yycolumn);}*/

/*ERRORES*/
{Numero} {return token(yytext(), "Nodefinido", yyline, yycolumn);}
({Numero}"."{Numero}) {return token(yytext(), "Nodefinido", yyline, yycolumn);}
({Letra} | {Digito})* {return token(yytext(), "Nodefinido", yyline, yycolumn);}
{Palabra} {return token(yytext(), "Nodefinido", yyline, yycolumn);}

/*retorna los valores en char*/

. { return token(yytext(), " ", yyline, yycolumn); }  