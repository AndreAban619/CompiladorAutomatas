import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
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
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Digito}|{Letra})*
/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

/*EMPEZAMOS*/
/* Identificadormio */
("Ini-"{Identificador}"@.") {/*Ignorar*/}
("Ini-"{Numero}"@.") {/*Ignorar*/}
("Ini-"{Numero}{Identificador}"@.") {/*Ignorar*/}
({Numero}"1010") {/*Ignorar*/}
({Numero}"1010."{Numero}) {/*Ignorar*/}

/*Tipos de datos*/
E/E#|ARY#|DCM# {return textColor(yychar, yylength(),new Color(128,0,128));  }

"E/E#" {return textColor(yychar, yylength(),new Color(128,0,128));  }
"ARY#" {return textColor(yychar, yylength(),new Color(128,0,128));  }
"DCM#" {return textColor(yychar, yylength(),new Color(128,0,128));  }


/*Operadores de agrupacion*/
"(" {return textColor(yychar, yylength(), new Color(218, 229, 55)); }
")" {return textColor(yychar, yylength(),new Color(218, 229, 55));  }
"{" {return textColor(yychar, yylength(),new Color(218, 229, 55));  }
"}" {return textColor(yychar, yylength(),new Color(218, 229, 55));  }
/*SIGNOS DE PUNTUACION*/
"," {return textColor(yychar, yylength(),new Color(55, 229, 96));  }
";" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }

/*operadores*/
 "+"  {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
 "-" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
 "*" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
 "/" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
"&&"  {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
"||" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
 ">" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
 "==" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
"!=" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
">=" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
"<=" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }
";;" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }


/*OPERADOR DE ASIGNACION*/
"=" {return textColor(yychar, yylength(),new Color(55, 229, 96)); }

/*FUNCIONFOR*/
"for1010" {return textColor(yychar, yylength(), new Color(17, 243, 232));}
/*OPERADORES LOGICOS*/
"&"|"|" {/*Ignorar*/}
/*FINAL*/
"FIN" {return textColor(yychar, yylength(), new Color(55, 229, 96)); }
/*ERRORES*/
{Numero} {/*Ignorar*/}
{Identificador} {/*Ignorar*/}
({Numero}"."{Numero}) {/*Ignorar*/}
. { /* Ignorar */ }

