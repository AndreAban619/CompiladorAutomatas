
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
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Compilador extends javax.swing.JFrame {

    private String title; //titulo compilador
    private Directory directorio;//directorio para guardar
    private ArrayList<Token> tokens; //donde se guardan nuestros tokens
    private ArrayList<ErrorLSSL> errors; ///errores de consola
    private Timer timerKeyReleased;
    private boolean codeHasBeenCompiled = false;
    int tamañote = 0;
    String textoOptimizado;
    String[] Lexema = new String[2000];// se guarda el lexema del token
    String[] TOKEN = new String[2000];// Aqui se guarda el token
    int[] LINEA = new int[2000];//aqui la linea
    int[] COLUMNA = new int[2000];//aqui en que columna esta
    private List<String> listaLineasSinPrimerasTres;
    ///Cadena que copia el lexema con su linea
    String Tokenpos[][] = new String[2000][2000];
    //obtiene el valor de arriab
    String CopyTokenpos[][] = new String[2000][2000];
    //  String[] GENERAL = new String[2000];
    String[] ERROR = new String[2000]; //guarda los errores
    String[] ENTERO = new String[2000]; ///los enteros
    String[] CADENA = new String[2000];
    String[] REAL = new String[2000];
     String[] INFOX = new String[2000]; //nos sirve para copiar los lexemas en tokenspara comparar las reglas
    int cont1 = 0, contaerror = 0, linfin = 0; //contadores
    String Lexem, TOKE;//cadenas para comparar
    int LINE, COLUMN; //cadenas para revisar linea y columnas

    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblerrores = new javax.swing.JTable();
        btnCompilar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Triplito = new javax.swing.JTable();
        btnNuevo1 = new javax.swing.JButton();
        btnNuevo2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setMaximumSize(new java.awt.Dimension(99999, 99999));

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
            tblTokens.getColumnModel().getColumn(1).setHeaderValue("Lexema  ");
        }

        tblerrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token error", "Lexema             Descripcion            Linea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblerrores.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblerrores);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        jLabel1.setText("Tabla de simbolos");

        jLabel2.setText("Tabla de errores");

        jLabel3.setText("Tabla de triplos");

        Triplito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Triplito.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(Triplito);
        if (Triplito.getColumnModel().getColumnCount() > 0) {
            Triplito.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        btnNuevo1.setText("Optimizar");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnNuevo2.setText("Reemplazar");
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(btnCompilar)
                                .addGap(26, 26, 26)
                                .addComponent(btnNuevo1)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCompilar)
                                    .addComponent(btnNuevo1)
                                    .addComponent(btnNuevo2))
                                .addGap(82, 82, 82))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rootPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))))
                        .addGap(40, 40, 40))))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile(); // el boton compilar ejecuta la funcion compile
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {  ///guardar como

        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) { ///guardar

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New(); //crear un directorio
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) { //abrir un directorio

        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed

       //SwingUtilities.invokeLater(() -> new Optimizacion(listaLineasSinPrimerasTres));
       System.out.println("esta optimizado");
       
       
        SwingUtilities.invokeLater(() -> {
            Optimizacion optimizacionInstance = new Optimizacion(listaLineasSinPrimerasTres);

            // Obtener el texto optimizado y usarlo según sea necesario
             textoOptimizado = optimizacionInstance.optimizarCodigo(listaLineasSinPrimerasTres);
            //System.out.println("Texto optimizadooo:\n" + textoOptimizado);
        });
       // Optimizacion optimizacionInstance = new Optimizacion(listaLineasSinPrimerasTres);

 // System.out.println("Texto optimizado desde otra clase: " + textoOptimizado);
       
        
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        System.out.println("Texto remplazo:\n" );
        jtpCode.setText(textoOptimizado);
    }//GEN-LAST:event_btnNuevo2ActionPerformed

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
            //System.out.println(Lexem); //  el lexem guarda las variable a analizar

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
            TOKE = token.getLexicalComp();//lo tomamos de lexer y lo guardamos en una variable
            TOKEN[cont1] = TOKE;//ese variable se la pasamos a un arreglo con el mismo nombre 
            Lexem = token.getLexeme();
            Lexema[cont1] = Lexem;
            LINE = token.getLine();
            LINEA[cont1] = LINE;
            COLUMN = token.getColumn();
            COLUMNA[cont1] = COLUMN;
  
             if(TOKEN[cont1]== "Nodefinido")  //Guarda las variables que no están definidas y las elimina
             {
               ERROR[contaerror]=Lexema[cont1]+"          Indefinida la variable          "+ LINEA[cont1];
               contaerror++;
             }
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
                //System.out.println("aknakna "+CopyTokenpos[nume][nume2]);
                nume2++;
            } else {
                nume2 = 0;
                numlin++;
                nume++;
                i = i - 1;
            }
        }
        ////////Cambiar el tipo de declaración
        ////////////////////////////////////////////// Primera declaracion le pone su tipo a las ya declaradas
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
                       // System.out.println( "DAT:"+TOKEN[j]+ Lexema[j]+ LINEA[j]+ COLUMNA[j] +j);
                        //System.out.println( "daton: "+Tokenpos[2][i]);//PRIMERA DECLARACION 
                    }
                }
            }
        }
        /////////////////////////////////////
        //////////////////////////////////////////////////////////////INCOMPATIBILIDAD DE TIPOS
   //////////////////Rellenar Tabla de simbolos /////////////////////////////////////////////////////
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
            if (TOKEN[j]=="IDENTIFICADOR")  //indefinida la variable cuando se usa un identificador y este no está declarado
            {
                ERROR[contaerror]=Lexema[j]+"          Indefinida la variable          "+ LINEA[j];
               contaerror++;
            }
            if(!(TOKEN[j].equals("ent_ ") || TOKEN[j].equals("rea_ ")|| TOKEN[j].equals("cad_ ")|| TOKEN[j].equals("cad_")|| TOKEN[j].equals("ent_")|| TOKEN[j].equals("rea_"))) //si no es un tipo de dato no se muestra en la tabla
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
///////////////////////// separa en tres arreglos losa datos de entero, cadena y real

        int o = 0; //Contadores de cada arreglos de tipo de datos
        int o2 = 0;
        int o3 = 0;
        for (int i = 0; TOKEN[i] != null; i++) {
            if (TOKEN[i].equals("ent_ ")||TOKEN[i].equals("ent_") ) {   //compara el valor que trae el token i guardado en la cadena compararval
                //System.out.println("toke: "+TOKEN[i]); 
                // System.out.println("lex: "+Lexema[i]);   
                ENTERO[o] = Lexema[i];//todos los enteros
                o++;
            }
        }
        for (int i = 0; TOKEN[i] != null; i++) {
            if (TOKEN[i].equals("cad_ ")||TOKEN[i].equals("cad_")) {
                //System.out.println("toke: "+TOKEN[i]); 
               //  System.out.println("lex: "+Lexema[i]);  //todas las cadenas   
                CADENA[o2] = Lexema[i];
                o2++;
            }
        }
        for (int i = 0; TOKEN[i] != null; i++) {
            if (TOKEN[i].equals("rea_ ")||TOKEN[i].equals("rea_")) {
                //System.out.println("toke: "+TOKEN[i]);
                //System.out.println("real: "+Lexema[i]); 
                REAL[o3] = Lexema[i]; //todos los reales
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
                    String multi="*";
                    
         for (int i = 0; i < linfin; i++) //AQUUI QUITO El NULLLL
        {
             INFOX[i]=" ";
        }
         
        for (int i = 3; i < linfin; i++) //Recorre las lineas despues de la declaracion a la ultima, guarda linea por linea
        {   
            for (int j = 0; Tokenpos[i][j] != null; j++) {
             
            //System.out.println("holaaas: "+Tokenpos[i][j]);   //aqui esta la cadena completa que entra
               String compararToken1=Tokenpos[i][j];
       ///////////////////////////aqui busca las variables declaradas y crea una tabla de tokens/////////////////////////////////        
                for(int k=0;ENTERO[k]!=null;k++)
                {
                    String compararToken=ENTERO[k]; //recibe el valor de los enteros que existem en el programa
                   // System.out.println("holaaas: "+compararToken);

                    if(compararToken1.equals(compararToken))
                    {
                        //System.out.println("holaaas: "+compararToken1+" "+compararToken);
                        //System.out.println("holaaas: "+Tokenpos[i][j]);
                        Tokenpos[i][j]="ENTERO";
                    }
                }
                
                //////////////////////////////////
                for(int k=0;CADENA[k]!=null;k++)
                {
                    String compararToken=CADENA[k];
                    if(compararToken1.equals(compararToken))
                    {
                        Tokenpos[i][j]="CADENA";
                    }
                }
                ////////////////////////////////////
                  for(int k=0;REAL[k]!=null;k++)
                {
                    String compararToken=REAL[k];
                    if(compararToken1.equals(compararToken))
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
                                              if(compararToken1.equals(multi))
                    {
                        //System.out.println("holaaas: "+hola+" "+hola1);
                        Tokenpos[i][j]="MULTI";
                    } 
     //////////////////////////////////
           INFOX[conta]= INFOX[conta] +" "+Tokenpos[i][j];  //SE CONCATENA Y GUARDA LINEA POR LINEA
         //System.out.println("aqui:" + INFOX[conta]);  //contiene la tabla traducida 
            }
             conta++;           
        }
        //////////////////////////////////
        //////////////////Declaracion de errores de las reglas de asignación
        
          ////Reglas de asignacion  
        String cadena2[] = {
        "  CADENA OPASIGNACION ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO",
        "  CADENA OPASIGNACION REAL",    
        };
                String real2[] = {   
        "  REAL OPASIGNACION CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA",
        };
         String entero2[] = {
        "  ENTERO OPASIGNACION REAL PUNCOMA",
        "  ENTERO OPASIGNACION CADENA PUNCOMA", 
        "  ENTERO OPASIGNACION REAL",
        "  ENTERO OPASIGNACION CADENA",
                  };
       /////////////////Reglas Arimeticas////////////////////// 
        String cadena[] = {
        "  CADENA OPASIGNACION REAL SUMA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA SUMA REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL SUMA CADENA",
        "  CADENA OPASIGNACION CADENA SUMA REAL",
        
        "  CADENA OPASIGNACION ENTERO SUMA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA SUMA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO SUMA CADENA",
        "  CADENA OPASIGNACION CADENA SUMA ENTERO",
        
        "  CADENA OPASIGNACION ENTERO SUMA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL SUMA REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL SUMA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO SUMA REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO SUMA ENTERO",
        "  CADENA OPASIGNACION REAL SUMA REAL",
        "  CADENA OPASIGNACION REAL SUMA ENTERO",
        "  CADENA OPASIGNACION ENTERO SUMA REAL",
        //////Multiplicacion//////
        "  CADENA OPASIGNACION CADENA MULTI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA MULTI CADENA",
         "  CADENA OPASIGNACION ENTERO MULTI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA MULTI ENTERO",
         "  CADENA OPASIGNACION ENTERO MULTI REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL MULTI ENTERO",
        "  CADENA OPASIGNACION ENTERO MULTI ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL MULTI REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL MULTI ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO MULTI REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO MULTI ENTERO",
        "  CADENA OPASIGNACION REAL MULTI REAL",
        "  CADENA OPASIGNACION REAL MULTI ENTERO",
        "  CADENA OPASIGNACION ENTERO MULTI REAL",
/////////////////////////////////
        "  CADENA OPASIGNACION REAL RESTA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA RESTA REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL RESTA CADENA",
        "  CADENA OPASIGNACION CADENA RESTA REAL",
        
        "  CADENA OPASIGNACION ENTERO RESTA CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA RESTA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO RESTA CADENA",
        "  CADENA OPASIGNACION CADENA RESTA ENTERO",
        
        "  CADENA OPASIGNACION ENTERO RESTA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL RESTA REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL RESTA ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO RESTA REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO RESTA ENTERO",
        "  CADENA OPASIGNACION REAL RESTA REAL",
        "  CADENA OPASIGNACION REAL RESTA ENTERO",
        "  CADENA OPASIGNACION ENTERO RESTA REAL",
     ///////////////////////////////// 
        "  CADENA OPASIGNACION CADENA DIVI CADENA PUNCOMA",
        "  CADENA OPASIGNACION REAL DIVI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA DIVI REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL DIVI CADENA",
        "  CADENA OPASIGNACION CADENA DIVI REAL",
        
        "  CADENA OPASIGNACION ENTERO DIVI CADENA PUNCOMA",
        "  CADENA OPASIGNACION CADENA DIVI ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO DIVI CADENA",
        "  CADENA OPASIGNACION CADENA DIVI ENTERO",
        
        "  CADENA OPASIGNACION ENTERO DIVI ENTERO PUNCOMA",
        "  CADENA OPASIGNACION REAL DIVI REAL PUNCOMA",
        "  CADENA OPASIGNACION REAL DIVI ENTERO PUNCOMA",
        "  CADENA OPASIGNACION ENTERO DIVI REAL PUNCOMA",
        "  CADENA OPASIGNACION ENTERO DIVI ENTERO",
        "  CADENA OPASIGNACION REAL DIVI REAL",
        "  CADENA OPASIGNACION REAL DIVI ENTERO",
        "  CADENA OPASIGNACION ENTERO DIVI REAL",
        };
        /////////////////////////////
                String real[] = {
        "  REAL OPASIGNACION CADENA SUMA REAL PUNCOMA",
        "  REAL OPASIGNACION REAL SUMA CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA SUMA CADENA PUNCOMA",
        "  REAL OPASIGNACION ENTERO SUMA CADENA PUNCOMA",
        "  REAL OPASIGNACION ENTERO SUMA CADENA",
        "  REAL OPASIGNACION CADENA SUMA ENTERO PUNCOMA",
        "  REAL OPASIGNACION CADENA SUMA ENTERO",
        "  REAL OPASIGNACION CADENA SUMA CADENA",
        "  REAL OPASIGNACION CADENA SUMA REAL",
        "  REAL OPASIGNACION REAL SUMA CADENA",
        /////////////////multiplicacion/////////////
        "  REAL OPASIGNACION CADENA MULTI CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA MULTI CADENA",
        //////////////////////////////////////////
        "  REAL OPASIGNACION CADENA RESTA REAL PUNCOMA",
        "  REAL OPASIGNACION REAL RESTA CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA RESTA CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA RESTA CADENA",
        "  REAL OPASIGNACION CADENA RESTA REAL",
        "  REAL OPASIGNACION REAL RESTA CADENA",
        "  REAL OPASIGNACION ENTERO RESTA CADENA PUNCOMA",
        "  REAL OPASIGNACION ENTERO RESTA CADENA",
        "  REAL OPASIGNACION CADENA RESTA ENTERO PUNCOMA",
        "  REAL OPASIGNACION CADENA RESTA ENTERO",
        
        "  REAL OPASIGNACION CADENA DIVI REAL PUNCOMA",
        "  REAL OPASIGNACION REAL DIVI CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA DIVI CADENA PUNCOMA",
        "  REAL OPASIGNACION CADENA DIVI CADENA",
        "  REAL OPASIGNACION CADENA DIVI REAL",
        "  REAL OPASIGNACION REAL DIVI CADENA",
        "  REAL OPASIGNACION ENTERO DIVI CADENA PUNCOMA",
        "  REAL OPASIGNACION ENTERO DIVI CADENA",
        "  REAL OPASIGNACION CADENA DIVI ENTERO PUNCOMA",
        "  REAL OPASIGNACION CADENA DIVI ENTERO",

        };
         ////////////////////////////////////////////////
       String entero[] = {
        "  ENTERO OPASIGNACION REAL SUMA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA SUMA REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL SUMA CADENA",
        "  ENTERO OPASIGNACION CADENA SUMA REAL",
        
        "  ENTERO OPASIGNACION ENTERO SUMA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA SUMA ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO SUMA CADENA",
        "  ENTERO OPASIGNACION CADENA SUMA ENTERO",
        
        "  ENTERO OPASIGNACION CADENA SUMA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION REAL SUMA REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL SUMA ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO SUMA REAL PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO SUMA CADENA",
        "  ENTERO OPASIGNACION REAL SUMA REAL",
        "  ENTERO OPASIGNACION REAL SUMA ENTERO",
        "  ENTERO OPASIGNACION ENTERO SUMA REAL",
        /////////////////////multiplicacion/////////////////////////
        
                
        "  ENTERO OPASIGNACION CADENA MULTI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION REAL MULTI REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL MULTI ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO MULTI REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL MULTI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA MULTI REAL PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO MULTI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA MULTI ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION REAL MULTI CADENA",
        "  ENTERO OPASIGNACION CADENA MULTI REAL",
        "  ENTERO OPASIGNACION ENTERO MULTI CADENA",
        "  ENTERO OPASIGNACION CADENA MULTI ENTERO",
        "  ENTERO OPASIGNACION ENTERO MULTI CADENA",
        "  ENTERO OPASIGNACION REAL MULTI REAL",
        "  ENTERO OPASIGNACION REAL MULTI ENTERO",
        "  ENTERO OPASIGNACION ENTERO MULTI REAL",
        
        
  
/////////////////////////////////
        "  ENTERO OPASIGNACION REAL RESTA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA RESTA REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL RESTA CADENA",
        "  ENTERO OPASIGNACION CADENA RESTA REAL",
        
        "  ENTERO OPASIGNACION ENTERO RESTA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA RESTA ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO RESTA CADENA",
        "  ENTERO OPASIGNACION CADENA RESTA ENTERO",
        
        "  ENTERO OPASIGNACION CADENA RESTA CADENA PUNCOMA",
        "  ENTERO OPASIGNACION REAL RESTA REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL RESTA ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO RESTA REAL PUNCOMA",
        "  ENTERO OPASIGNACION CADENA RESTA CADENA",
        "  ENTERO OPASIGNACION REAL RESTA REAL",
        "  ENTERO OPASIGNACION REAL RESTA ENTERO",
        "  ENTERO OPASIGNACION ENTERO RESTA REAL",
     ///////////////////////////////// 
        "  ENTERO OPASIGNACION CADENA DIVI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION REAL DIVI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA DIVI REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL DIVI CADENA",
        "  ENTERO OPASIGNACION CADENA DIVI REAL",
        
        "  ENTERO OPASIGNACION ENTERO DIVI CADENA PUNCOMA",
        "  ENTERO OPASIGNACION CADENA DIVI ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO DIVI CADENA",
        "  ENTERO OPASIGNACION CADENA DIVI ENTERO",
        
        "  ENTERO OPASIGNACION ENTERO DIVI ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION REAL DIVI REAL PUNCOMA",
        "  ENTERO OPASIGNACION REAL DIVI ENTERO PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO DIVI REAL PUNCOMA",
        "  ENTERO OPASIGNACION ENTERO DIVI ENTERO",
        "  ENTERO OPASIGNACION REAL DIVI REAL",
        "  ENTERO OPASIGNACION REAL DIVI ENTERO",
        "  ENTERO OPASIGNACION ENTERO DIVI REAL",
        };
        //////////CADENA ASIGNACIÓN/////////
           for (int j = 0; j<cadena2.length; j++) {
           
               String Regla=cadena2[j]; //esta variable tiene las reglas y las compara una a una
              // System.out.println("aquisito "+Regla);
               
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
               // System.out.println("lista: "+lista+"   Regla: "+Regla);
                 if(Regla.equals(lista))
                        {
                            int osi=i+4;
                               
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo cad_     " + " " +osi;
                            contaerror++;
                        }
           }
           
    }
         //////////////Real ASIGNACION/////////////////////  
            ///////////////////
           for (int j = 0; j<real2.length; j++) {
           
               String Regla=real2[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
                 //System.out.println("ito "+lista+"   vbdkdbj  "+Regla);
                 if(Regla.equals(lista))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo rea_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         //////////////ENTERO ASIGNACION///////////////////// 
         ///////////////////
           for (int j = 0; j<entero2.length; j++) {
           
               String Regla=entero2[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(Regla.equals(lista))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo ent_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 
         ///////*********ARIMETICA CADENA******************************************************/////
            ///////////////////
           for (int j = 0; j<cadena.length; j++) {
           
               String Regla=cadena[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(Regla.equals(lista))
                        {
                               //.out.println("aquisitoxfffsvr ");
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo cad_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         ////////////////ARIMETICA REAL///////////////////  
            ///////////////////
           for (int j = 0; j<real.length; j++) {
           
               String Regla=real[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(Regla.equals(lista))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo rea_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////ARIMETICA ENTERO////////////////////////// 
         ///////////////////
           for (int j = 0; j<entero.length; j++) {
           
               String Regla=entero[j];
              // System.out.println("aquisito "+erronio[j]);
               for (int i = 0; INFOX[i] != null; i++) {
                String lista= INFOX[i];
                   //System.out.println("ito "+inc1+"   vbdkdbj  "+inco);
                 if(Regla.equals(lista))
                        {
                              
                               int osi=i+4;
                            ERROR[contaerror]="Incompatibilidad de tipos de tipo ent_     " + " " +osi;
                            contaerror++;
                        }
           }
    }
         /////////////////////////////////// 
       ////////////////////////Tabla de errores
        for (int i = 0; i < contaerror; i++) {
            int contando = i + 1;
     
            Object[] data2 = new Object[]{"ErrorSem" + contando, ERROR[i]};
            //  System.out.println("olaaa"+ERROR[i]);
            Functions.addRowDataInTable(tblerrores, data2);
        }
        //////TRIPLOSSSSSS/ TABLA////////////////////    
        
        String texto = jtpCode.getText();
       // Divide el texto en líneas usando el carácter de nueva línea como separador
       String[] lineas = texto.split("\n");
      // Crear un nuevo arreglo para almacenar las líneas sin las primeras 3 líneas
        String[] lineasSinPrimerasTres = new String[lineas.length - 3];
        // Copiar las líneas sin las primeras 3 líneas
           for (int i = 3; i < lineas.length; i++) {
        // Elimina los puntos y coma (";") de cada línea antes de copiarla
        String linea = lineas[i].replace(";", "");
            lineasSinPrimerasTres[i - 3] = linea;
            }
           ////////////////
         listaLineasSinPrimerasTres = Arrays.asList(lineas);
        // Imprimir la lista
       
           /////////////////
        // Imprimir las líneas sin las primeras 3 líneas y sin los puntos y coma
       /*for (String linea : lineasSinPrimerasTres) {
         System.out.println(linea);
        }*/
        //aquiiii/////
       GeneradordeTriplos converter = new GeneradordeTriplos();
       //Optimizacion converter2 = new Optimizacion();
        //converter2.optimizarCodigo(lineasSinPrimerasTres);
          /*   String[] lineasDeEntrada = {
            "ISC_002 = 414 + 123.4884",
            "ISC_001 = ISC_001 * ISC_009",
            "ISC_009555  = ISC_009",
            "ISC_001 = ISC_002 - ISC_006",
            "ISC_001 = ISC_002 - ISC_006",
            "ISC_009 = ISC_008 + ISC_007",
            "ISC_009 = ISC_002 / ISC_009",
            "ISC_009 = \"ISC_002\" / ISC_009"
        };*/
       String regresotriplo = converter.EstructuraTriplos(lineasSinPrimerasTres);
        String[] arreglotriplos = regresotriplo.split("\n");
for (String arreglotri : arreglotriplos) {
    String[] columnas = arreglotri.split("\t"); // Divide la línea en columnas
    Functions.addRowDataInTable(Triplito, columnas);
}
       //System.out.println("Triplos:\n" + regresotriplo);

        // Imprimir el código optimizado
       // System.out.println("Código optimizado:\n" + codigoOptimizado);
          ///////////////////////
      //tripolabel.setText("<html>" + regresotriplo.replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;") + "</html>");
      // Functions.addRowDataInTable(Tablatriplo, Tablatriplos);
         // Agregar los datos de la lista al modelo de tabla
        // Object[] Tablatriplos = new Object[]{ regresotriplo}; 
        ////////INVOCACION PARA EL METODO//////////////////
        
        
        ////////////////////////////////////////////////////////////
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
        Functions.clearDataInTable(Triplito);
        jtaOutputConsole.setText("");
        
        codeHasBeenCompiled = false;
          // Reinicia las variables de tipo String
    for (int i = 0; i < Lexema.length; i++) {
        Lexema[i] = null;
        TOKEN[i] = null;
        ERROR[i] = null;
        ENTERO[i] = null;
        CADENA[i] = null;
        REAL[i] = null;
        INFOX[i] = null;
    }

    // Reinicia las variables de tipo int
    for (int i = 0; i < TOKEN.length; i++) {
        LINEA[i] = 0;
        COLUMNA[i] = 0;
    }

    // Reinicia las variables de tipo String[][]
    for (int i = 0; i < Tokenpos.length; i++) {
        for (int j = 0; j < Tokenpos[i].length; j++) {
            Tokenpos[i][j] = null;
            CopyTokenpos[i][j] = null;
        }
    }

    // Reinicia otras variables según sea necesario
    tokens = new ArrayList<>();
    errors = new ArrayList<>();
    codeHasBeenCompiled = false;
    tamañote = 0;
    cont1 = 0;
    contaerror = 0;
    linfin = 0;
    Lexem = null;
    TOKE = null;
    LINE = 0;
    COLUMN = 0;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
    private javax.swing.JTable Triplito;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTable tblerrores;
    // End of variables declaration//GEN-END:variables
}
