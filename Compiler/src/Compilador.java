
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd, identProd1, identProd2;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    int tamañote = 0;

    String[] Lexema = new String[2000];// se guarda con el tamaño de los tokens
    String[] TOKEN = new String[2000];
    int[] LINEA = new int[2000];
    int[] COLUMNA = new int[2000];

    ///Cadena que copia el lexema con su linea
    String Tokenpos[][] = new String[2000][2000];
    //obtiene el valor de arriab
    String CopyTokenpos[][] = new String[2000][2000];
    //  String[] GENERAL = new String[2000];
    ArrayList<String> list = new ArrayList<String>();
    String[] ERROR = new String[2000];
    String[] ENTERO = new String[2000];
    String[] CADENA = new String[2000];
    String[] REAL = new String[2000];
     String[] INFOX = new String[2000];
     String[] INFOX2 = new String[2000];
     String[] IDENTI1 = new String[2000];
     String[] IDENTI2 = new String[2000];
     String[] IDENTI3 = new String[2000];
    String[] dato = new String[3];
    int cont1 = 0, contaerror = 0, linfin = 0, alecsito=0, comaa=0,puncomaa=0,iguaal=0,su=0, aron2=0;
    String Lexem, TOKE;
    int LINE, COLUMN;

    
    //// función que llama la funcion para la creación de la ventana
    public Compilador() {
        initComponents(); //inicializamo los componentes
        init();   //inicializamos la ventana
    }

    
    //función para inicializar ventana
    private void init() {
        title = "Compilador Equipo. 4";//nombre del compilador
        setLocationRelativeTo(null);//centrar ventana
        setTitle(title); //enviarle el titulo
        directorio = new Directory(this, jtpCode, title, ".andre");//guarda nuestros documentos con esta extensión.
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();//pregunta si se guardan o descartan los cambios
                System.exit(0); //salir
            }
        });

        
        Functions.setLineNumberOnJTextComponent(jtpCode);//en el editor se muestran los numeros de linea
        Functions.insertAsteriskInName(this, jtpCode, () -> {//pone un asterisco cada que editemos 
            timerKeyReleased.restart();//se llama al metodo
        });
        
        
        // se inicializan las arrays
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identProd1 = new ArrayList<>();
        identProd2 = new ArrayList<>();
        identificadores = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblerrores = new javax.swing.JTable();
        btnCompilar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnGuardarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAbrir)
                        .addComponent(btnNuevo)
                        .addComponent(btnGuardar)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Lexema  "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);
        if (tblTokens.getColumnModel().getColumnCount() > 0) {
            tblTokens.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        tblerrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token error", "Lexema         Descripcion       Linea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblerrores.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblerrores);
        if (tblerrores.getColumnModel().getColumnCount() > 0) {
            tblerrores.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        jLabel1.setText("Tabla de simbolos");

        jLabel2.setText("Tabla de errores");

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                                .addComponent(btnCompilar)
                                .addGap(122, 122, 122)))
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 8, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompilar))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New(); //crear un directorio
        clearFields();//LIMPIAR
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) { //abrir un directorio
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) { ///guardar
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {  ///guardar como
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile(); // el boton compilar ejecuta la funcion compile
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void compile() {

        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer los lexemas

        //int cont= 0;
        Lexer lexer;
        try {

            File codigo = new File("code.encrypter");//archivo entrada
            FileOutputStream output = new FileOutputStream(codigo);//archivo salida
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();//retorna tokens hasta null en el panel de entrada

                if (token == null) {
                    break;
                }
                // Lexema[cont]=tokens.toString();
                tokens.add(token);
                //   cont++;
            }
            // System.out.println("ffff"+tokens.toString());
            Lexem = tokens.toString(); // copio los datos a mi cadena para analizar
            System.out.println(Lexem); //  el lexem guarda las variable a analizar

        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }

    }
    ////////////////////////
    private void syntacticAnalysis() {
    }

    private void semanticAnalysis() {
    }
    private void fillTableTokens() { ///////LLenando la tabla de simbolos
        tamañote = tokens.size();
        tokens.forEach(token -> {
            TOKE = token.getLexicalComp();
            TOKEN[cont1] = TOKE;
            Lexem = token.getLexeme();
            Lexema[cont1] = Lexem;
            LINE = token.getLine();
            LINEA[cont1] = LINE;
            COLUMN = token.getColumn();
            COLUMNA[cont1] = COLUMN;
       
             if(TOKEN[cont1]== "Nodefinido")  //Guarda las variables que no están definidas y las elimina
             {
                                 
               ERROR[contaerror]= Lexema[cont1]+"           Indefinida la variable              " + " " + LINEA[cont1];
               contaerror++;
               //Lexema[cont1]=" ";//dejarlo en ¿lexema? por que no aparece en la tabla de simbolos
               //System.out.print(ERROR[contaerror]);
             }
           
            // Object[] data = new Object[]{TOKE,Lexem, "[" + LINE + ", " + COLUMN + "]"};
            //Functions.addRowDataInTable(tblTokens, data);
            cont1++;
        });
        //////ANALIZAR LAS CADENAS
        //ANALIZAR EL TIPO DE VARIABLE
        //AQUI GUARDO LOS DATOS LINEA POR LINEA
        int numlin = 1;// numero de linea a comparar
        int nume = 0;//lugar de dato a guardar, X
        int nume2 = 0;//Y
        for (int i = 0; i < tamañote; i++) {

            if (LINEA[i] == numlin) {
                Tokenpos[nume][nume2] = Lexema[i];///se guarda el valor del lexema en la posicion y linea
               CopyTokenpos[nume][nume2]=Tokenpos[nume][nume2] ;
               //System.out.print("Dato guardado: "+ Tokenpos[nume][nume2]);
                //System.out.print(" x: "+nume+"y: "+nume2+" NUMLIN: "+numlin+" i: "+i);
                //System.out.println(" ");
                nume2++;
            } else {
                nume2 = 0;
                numlin++;
                nume++;
                i = i - 1;
            }
        }
        ////////Cambiar el tipo de declaración
        ////////////////////////////////////////////// Primera declaracion
        for (int i = 0; Tokenpos[0][i] != null; i++) {
            for (int j = 0; TOKEN[j] != null; j++) {
                if (TOKEN[j] == "IDENTIFICADOR") {
                    if (Lexema[j] == Tokenpos[0][i]) {
                        TOKEN[j] = Tokenpos[0][0];
                         //System.out.println( "DAT: "+TOKEN[j]+ Lexema[j]+ LINEA[j]+ COLUMNA[j]);
                        //System.out.println( "daton: "+Tokenpos[0][i]);//PRIMERA DECLARACION 
                    }
                }
            }
        }
        ////////////////////////////////////////////
        
        //////////////////////////////////////////////      
        ////////////////////////////////////////////// Segunda declaracion
       
        for (int i = 0; Tokenpos[1][i] != null; i++) {
            for (int j = 0; TOKEN[j] != null; j++) {
                if (TOKEN[j] == "IDENTIFICADOR") {
                    if (Lexema[j] == Tokenpos[1][i]) {
                        TOKEN[j] = Tokenpos[1][0];
                        //System.out.println( "DAT: "+TOKEN[j]+ Lexema[j]+ LINEA[j]+ COLUMNA[j]);
                          //System.out.println( "daton: "+Tokenpos[1][i]);//PRIMERA DECLARACION 
                    }
                }
            }
        }
        //////////////////////////////////////////////   
        ////////////////////////////////////////////// Tercera declaracion
        for (int i = 0; Tokenpos[2][i] != null; i++) {
            for (int j = 0; TOKEN[j] != null; j++) {
                if (TOKEN[j] == "IDENTIFICADOR") {
                    if (Lexema[j] == Tokenpos[2][i]) {
                        TOKEN[j] = Tokenpos[2][0];
                        //System.out.println( "DAT: "+TOKEN[j]+ Lexema[j]+ LINEA[j]+ COLUMNA[j] +j);
                        //System.out.println( "daton: "+Tokenpos[2][i]);//PRIMERA DECLARACION 
                    }
                }
            }
        }
   ///////////////////////////////////////////////////////////////////////
   // Usar ArrayList para mantener elementos únicos y sincronizados
        ArrayList<String> lexemaList = new ArrayList<>();
        ArrayList<String> tokenList = new ArrayList<>();

        for (int i = 0; i < Lexema.length; i++) {
            // Verificar si el Lexema ya existe en la lista
            if (!lexemaList.contains(Lexema[i])) {
                lexemaList.add(Lexema[i]);
                tokenList.add(TOKEN[i]);
            }
        }

        // Copiar los datos sin duplicados directamente a Lexema[] y TOKEN[]
        for (int i = 0; i < lexemaList.size(); i++) {
             Lexema[i] = lexemaList.get(i);
            TOKEN[i] = tokenList.get(i);
            }
        //Rellenar tabla de tokens
        for (int j = 0; TOKEN[j] != null; j++) {
            if(TOKEN[j]!= "ent_"|TOKEN[j]!= "rea_")
            {
                TOKEN[j]= " ";
            }
                Object[] data = new Object[]{TOKEN[j], Lexema[j]};
                Functions.addRowDataInTable(tblTokens, data);

            }
   ///////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////INCOMPATIBILIDAD DE TIPOS
        for (int i = 0; i < tamañote; i++) {
            linfin = LINEA[i];//Guarda la linea maxima
        }
////////////////////////////eliminar vaaribles repetidas
///////////////////////// guarda enteros cadena y reales todos en un arreglo estos  están despues de  las declaraciones
        String enterito = "ent_";
        String cadenita = "cad_";
        String realito = "rea_";

        int o = 0;
        int o2 = 0;
        int o3 = 0;
        for (int i = 0; TOKEN[i] != null; i++) {


            ////si el valor del lexema es entero lo guarda en una cadena perteneciente a los enteros y asi en todas
            String compararval = TOKEN[i];
            if (compararval.equals(enterito)) {   //compara el valor que trae el token i guardado en la cadena compararval
                 System.out.println("toke: "+TOKEN[i]); 
                 System.out.println("Entero: "+Lexema[i]);   
                ENTERO[o] = Lexema[i];
                o++;
                // System.out.println(i+"hhh"+o);  

            }
        }
        for (int i = 0; TOKEN[i] != null; i++) {

            String compararval = TOKEN[i];
            if (compararval.equals(cadenita)) {
                //
                //System.out.println("cadenita: "+Lexema[i]);  
                CADENA[o2] = Lexema[i];
                o2++;
                // System.out.println("toke: "+CADENA[i]);
            }
        }
        for (int i = 0; TOKEN[i] != null; i++) {

            String compararval = TOKEN[i];
            if (compararval.equals(realito)) {
                //System.out.println("toke: "+TOKEN[i]);
                //System.out.println("real: "+Lexema[i]); 
                REAL[o3] = Lexema[i];
                o3++;
            }
        }

        /////////////////////////////rellenar tabla si se repite no va a la tabla
            int conta=0;
            String coma=",";
            String IF="4if4";
            String THEN="4then4";
            String ELSE= "4else4";        
            String puncoma=";";
            String suma="+";
            String OPA="(";
             String OPC=")";
              String CORA="{";
              String CORC="}";
              String IGUA="=";
               String MAIGUA=">=";
                String MEIGUA="<=";
                 String YY="&&";
                  String OR="||";
                  String menos="-";
                   String OR2="==";
                    String OR3="!=";
                    String DIVI="/";
                    
         for (int i = 0; i < linfin; i++) //AQUUI QUITO El NULLLL
        {
             INFOX[i]=" ";
        }
         
        for (int i = 3; i < linfin; i++) //Recorre las lineas despues de la declaracion a la ultima, guarda linea por linea
        {   
            for (int j = 0; Tokenpos[i][j] != null; j++) {
             
            //System.out.println("holaaas: "+Tokenpos[i][j]);
               String compararToken1=Tokenpos[i][j];
                for(int k=0;ENTERO[k]!=null;k++)
                {
                    String compararToken=ENTERO[k];
                    //System.out.println("holaaas: "+compararToken);
                     ////////////Si se repite no va a tabla
                    for(int h=0;Lexema[h]!=null;h++)
                    {
                      String toki=TOKEN[h];
                           String dato="ent_";
                           
                        String holita=Lexema[h];   //en lexema están todos los simbolos
                        if(toki.equals(dato)==false)
                     {
                          if(compararToken.equals(holita))
                    {
                     
                        Lexema[h]="ESTOCOLMO";
                     } 
                    }
 
                    }
                    ////////////////////
                   
                    if(compararToken1.equals(compararToken))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="ENTERO";
                    }
                }
                
                //////////////////////////////////
                for(int k=0;CADENA[k]!=null;k++)
                {
                    String hola=CADENA[k];
                       ////////////Si se repite no va a tabla
                    for(int h=0;Lexema[h]!=null;h++)
                    {
                      String toki=TOKEN[h];
                           String dato="cad_";
                        String holita=Lexema[h];
                        if(toki.equals(dato)==false)
                     {
                          if(hola.equals(holita))
                    {
                     
                        Lexema[h]="ESTOCOLMO";
                     } 
                    }
                      
                    }
                    ////////////////////
                    if(compararToken1.equals(hola))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="CADENA";
                    }
                }
                ////////////////////////////////////
                  for(int k=0;REAL[k]!=null;k++)
                {
                    String hola=REAL[k];
                   ////////////Si se repite no va a tabla
                    for(int h=0;Lexema[h]!=null;h++)
                    {
                      String toki=TOKEN[h];
                           String dato="rea_";
                        String holita=Lexema[h];
                        if(toki.equals(dato)==false)
                     {
                          if(hola.equals(holita))
                    {
                     
                        Lexema[h]="ESTOCOLMO";
                     } 
                    }
                      
                    }
                    ////////////////////
                    if(compararToken1.equals(hola))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="REAL";
                    }
                }
                  /////////////////////////////////////////////////
                    if(compararToken1.equals(coma))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="COMA";
                    }
                    if(compararToken1.equals(DIVI))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="DIVI";
                    }
                      if(compararToken1.equals(puncoma))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="PUNCOMA";
                    }
                            if(compararToken1.equals(suma))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="SUMA";
                    }
                                     if(compararToken1.equals(menos))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="RESTA";
                    }
                                  if(compararToken1.equals(OPA))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="PARENTESISA";
                    }
                                        if(compararToken1.equals(OPC))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="PARENTESISC";
                    }
                   if(compararToken1.equals(CORA))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="LLAVEA";
                    }
                    if(compararToken1.equals(CORC))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="LLAVEC";
                    }
                     if(compararToken1.equals(IGUA))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="OPASIGNACION";
                    }
                      if(compararToken1.equals(MAIGUA))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="MAYORIGU";
                    } if(compararToken1.equals(MEIGUA))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="MENORIGU";
                    }
                   
                      if(compararToken1.equals(YY))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="YYY";
                    }
                       if(compararToken1.equals(OR))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="TAMBIEN";
                    }
                      
                        if(compararToken1.equals(OR2))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="IGUALQUE";
                    }
                              if(compararToken1.equals(OR3))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="DIFERENTE";
                    }
                                         if(compararToken1.equals(IF))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="if";
                    }
                                         
                               if(compararToken1.equals(THEN))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="then";
                    }    
                               if(compararToken1.equals(ELSE))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="else";
                    } 
     //////////////////////////////////
           INFOX[conta]= INFOX[conta] +" "+Tokenpos[i][j];  //SE CONCATENA Y GUARDA LINEA POR LINEA
           System.out.println("aqui:   " + INFOX[conta]);  //contiene la tabla traducida 
            }
             conta++;           
        }
        //////////////////////////////////
        //////////////////Declaracion de Reglas
        
          ////Reglas de asignacion  
        String cadena2[] = {
        "  CADENA OPASIGNACION ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL PUNCOMA",
       // "  CADENA OPASIGNACION IDENTIFICADORNUM PUNCOMA",        
        };
                String real2[] = {   
        "  REAL OPASIGNACION CADENA PUNCOMA",
        };
         String entero2[] = {
        "  ENTERO OPASIGNACION REAL PUNCOMA",
        "  ENTERO OPASIGNACION CADENA PUNCOMA",        
                  };
       /////////////////Reglas Arimeticas////////////////////// 
        String cadena[] = {
        "  CADENA OPASIGNACION REAL SUMA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA SUMA REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO SUMA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA SUMA ENTERO PUNCOMA",
        //"  CADENA OPASIGNACION CADENA SUMA IDENTIFICADORNUM PUNCOMA",
        //"  CADENA OPASIGNACION IDENTIFICADORNUM SUMA CADENA PUNCOMA",
        
        "  CADENA OPASIGNACION REAL RESTA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA RESTA REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO RESTA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA RESTA ENTERO PUNCOMA",
            
        "  CADENA OPASIGNACION REAL DIVI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA DIVI REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO DIVI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA DIVI ENTERO PUNCOMA",
        };
                String real[] = {
        "  REAL OPASIGNACION CADENA SUMA REAL PUNCOMA",
        "  REAL OPASIGNACION REAL SUMA CADENA PUNCOMA",
        
        "  REAL OPASIGNACION CADENA RESTA REAL PUNCOMA",
        "  REAL OPASIGNACION REAL RESTA CADENA PUNCOMA",
        
        "  REAL OPASIGNACION CADENA DIVI REAL PUNCOMA",
        "  REAL OPASIGNACION REAL DIVI CADENA PUNCOMA",
        
 
      
        };
       String entero[] = {
        "  ENTERO OPASIGNACION ENTERO SUMA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA SUMA ENTERO PUNCOMA",
        
        "  ENTERO OPASIGNACION ENTERO RESTA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA RESTA ENTERO PUNCOMA",
        
        "  ENTERO OPASIGNACION ENTERO DIVI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA DIVI ENTERO PUNCOMA",
        };
        ///////////////////
           for (int j = 0; j<cadena2.length; j++) {
           
               String inco=cadena2[j]; //guarda las reglas en una variable para despues comparar
               System.out.println("aquisito "+inco);
               
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                System.out.println("ito"+inc+"   vbdkdb"+inco);
                 if(inco.equals(inc))
                        {
                               //.out.println("aquisitoxfffsvr ");
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo cad_     " + " " +osi;
                            contaerror++;
                        }
           }
           
    }
         ///////////////////////////////////  
            ///////////////////
           for (int j = 0; j<real2.length; j++) {
           
               String inco=real2[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(inco.equals(inc))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo rea_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 
         ///////////////////
           for (int j = 0; j<entero2.length; j++) {
           
               String inco=entero2[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(inco.equals(inc))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo ent_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 
         ///////***************************************************************/////
            ///////////////////
           for (int j = 0; j<cadena.length; j++) {
           
               String inco=cadena[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(inco.equals(inc))
                        {
                               //.out.println("aquisitoxfffsvr ");
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo cad_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         ///////////////////////////////////  
            ///////////////////
           for (int j = 0; j<real.length; j++) {
           
               String inco=real[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(inco.equals(inc))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo rea_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 
         ///////////////////
           for (int j = 0; j<entero.length; j++) {
           
               String inco=entero[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String inc= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(inco.equals(inc))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo ent_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 


    ////////////VERIFICAR/////////////////////////////////////////Error de asignación
   
    
    int cont1=0,cont2=0,cont3=0;
    for (int j = 0; TOKEN[j] != null; j++) {
    // System.out.println("Acoo: "+TOKEN[j]);
          String compa=TOKEN[j];     
           String compa1="IDENTIFICADORDEC";  
            String compa2="IDENTIFICADOR";  
             String compa3="IDENTIFICADORNUM";  
        if(compa.equals(compa1))
        {
            IDENTI1[cont1]= Lexema[j];
            TOKEN[j]="IGNORAR";
            cont1++;
        }
         if(compa.equals(compa2))
        {
             IDENTI2[cont2]= Lexema[j];
            TOKEN[j]="IGNORAR";
            cont2++;
        }
          if(compa.equals(compa3))
        {
             IDENTI3[cont3]= Lexema[j];
            TOKEN[j]="IGNORAR";
            cont3++;
        }
            }        
   
        /////////////////////////////////////////////////////////////////////
        for (int i = 0; i < linfin; i++) 
        {
            if(INFOX[i]!=null)
            {
                if(INFOX[i]!=" "){
          //  System.out.println("LINEA: "+INFOX[i]);
                }
            }
        } 
 //System.out.println("///////////////");
        ////////////////////////Tabla de errores
        for (int i = 0; i < contaerror; i++) {
            int contando = i + 1;
     
            Object[] data2 = new Object[]{"ErrorSem" + contando, ERROR[i]};
            //  System.out.println("olaaa"+ERROR[i]);
            Functions.addRowDataInTable(tblerrores, data2);
        }
        ///////////////////////////    
    }

    /////////////////////////////////////
    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación contiene errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        Functions.clearDataInTable(tblerrores);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*Cambiar la ventana*/
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTable tblerrores;
    // End of variables declaration//GEN-END:variables
}
