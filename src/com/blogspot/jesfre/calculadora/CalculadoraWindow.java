package com.blogspot.jesfre.calculadora;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CalculadoraWindow.java
 *
 * Created on 17/05/2009, 06:43:11 PM
 */



import java.math.BigDecimal;

/**
 *
 * @author Jorge Ruiz
 */
public class CalculadoraWindow extends javax.swing.JFrame {
    private BigDecimal acumulador;
    private StringBuffer operandoString;
    private BigDecimal operando;
    private String resultado;
    private int operador;
    private boolean evaluado;
    private boolean oprimidoNumeral;
    private boolean oprimidoOperador;

    // OPERADORES
    private final int OP_EVALUAR           = 0;
    private final int OP_SUMA              = 1;
    private final int OP_RESTA             = 2;
    private final int OP_DIVISION          = 3;
    private final int OP_MULTIPLICACION    = 4;
    private final int OP_SENO              = 5;
    private final int OP_COSENO            = 6;
    private final int OP_TANGENTE          = 7;
    private final int OP_X_ELEVADO_Y       = 8;
    private final int OP_X_ELEVADO_3       = 9;
    private final int OP_X_ELEVADO_2       = 10;
    private final int OP_LOG               = 11;
    private final int OP_NEGACION          = 12;
    private final int OP_PI                = 13;
    private final int OP_UNO_ENTRE_X     = 14;
    private final int OP_CAMBIO_DE_SIGNO   = 15;

    /** Creates new form CalculadoraWindow */
    public CalculadoraWindow() {
        initComponents();
        inicializar();
    }

    private void inicializar() {
        txtResultados.setText("0.");
        acumulador = BigDecimal.ZERO;
        operandoString = new StringBuffer("0");
        operando = BigDecimal.ZERO;
        resultado = "";
        operador = -1;
        evaluado = true;
        oprimidoNumeral = true;
        oprimidoOperador = false;
    }

    private void recibirDato(Object dato) {
        if(!oprimidoNumeral) {
            oprimidoNumeral = true;
            operandoString.replace(0, operandoString.length(), "0");
        }
        if(((dato.getClass().isInstance(new Integer(0)) && ((Integer)dato != 0 || operandoString.charAt(0) != '0'))
                || (dato.getClass().isInstance("") && operandoString.indexOf(".") < 0 && dato.toString().equals(".")))) {
            if(operandoString.charAt(0)=='0')
                operandoString.deleteCharAt(0);
            operandoString.append(dato);
            txtResultados.setText(operandoString.toString());
            oprimidoNumeral = true;
        }
        if(oprimidoOperador) {
            evaluado = false;
        }
    }

    private void resultado() {
        evaluar(this.operador);
        this.operador = -1;
        evaluado = true;
        oprimidoNumeral = false;
        //acumulador = BigDecimal.ZERO;
    }

    private void evaluar(int operador) {
        operando = new BigDecimal(operandoString.toString());
        oprimidoNumeral = false;
        oprimidoOperador = true;
        double res = 0.0;
        if(evaluado) {
            //operandoString.replace(0, operandoString.length(), "0");
            //acumulador = BigDecimal.ZERO;
            acumulador = BigDecimal.valueOf(operando.doubleValue());
            this.operador = operador;
            evaluado = false;
            return;
        }
        switch(this.operador) {
            case OP_SUMA:
                res = InterfazNativa.sumar(acumulador.doubleValue(), operando.doubleValue());
                System.out.print("SUMA - opa: " + acumulador.doubleValue() + ", opb: " + operando.doubleValue());
                break;
            case OP_RESTA:
                res = InterfazNativa.restar(acumulador.doubleValue(), operando.doubleValue());
                System.out.print("RESTA - opa: " + acumulador.doubleValue() + ", opb: " + operando.doubleValue());
                break;
            case OP_DIVISION:
                res = InterfazNativa.dividir(acumulador.doubleValue(), operando.doubleValue());
                System.out.print("DIVIDIR - opa: " + acumulador.doubleValue() + ", opb: " + operando.doubleValue());
                break;
            case OP_MULTIPLICACION:
                res = InterfazNativa.multiplicar(acumulador.doubleValue(), operando.doubleValue());
                System.out.print("MULTIPLICAR - opa: " + acumulador.doubleValue() + ", opb: " + operando.doubleValue());
                break;
            case OP_SENO:
                res = InterfazNativa.seno(operando.doubleValue());
                System.out.print("SENO - opa: " + operando.doubleValue());
                break;
            case OP_COSENO:
                res = InterfazNativa.coseno(operando.doubleValue());
                System.out.print("COSENO - opa: " + operando.doubleValue());
                break;
            case OP_TANGENTE:
                res = InterfazNativa.tangente(operando.doubleValue());
                System.out.print("TANGENTE - opa: " + operando.doubleValue());
                break;
            case OP_X_ELEVADO_Y:
                res = InterfazNativa.x_y(acumulador.doubleValue(), operando.intValue());
                System.out.print("X^Y - opx: " + acumulador.doubleValue() + ", opy: " + operando.doubleValue());
                break;
            case OP_X_ELEVADO_3:
                res = InterfazNativa.x_3(operando.doubleValue());
                System.out.print("X^3 - opx: " + operando.doubleValue());
                break;
            case OP_X_ELEVADO_2:
                res = InterfazNativa.x_2(operando.doubleValue());
                System.out.print("X^2 - opx: " + operando.doubleValue());
                break;
            case OP_LOG:
                res = InterfazNativa.log(operando.doubleValue());
                System.out.print("SQRT - opa: " + operando.doubleValue());
                break;
            case OP_NEGACION:
                res = InterfazNativa.negar(operando.intValue());
                System.out.print("NEGACION - opa: " + operando.doubleValue());
                break;
            case OP_PI:
                res = InterfazNativa.pi();
                break;
            case OP_UNO_ENTRE_X:
                res = InterfazNativa.uno_x(operando.doubleValue());
                System.out.print("1/X - opa: " + operando.doubleValue());
                break;
            case OP_CAMBIO_DE_SIGNO:
                res = InterfazNativa.cambioSigno(operando.doubleValue());
                System.out.print("CAMBIO DE SIGNO - opa: " + operando.doubleValue());
                break;
        }
        System.out.println("\tres: " + res);
        //this.operador = -1;
        //acumulador = BigDecimal.ZERO;
        this.operador = operador;
        acumulador = BigDecimal.valueOf(res);
        evaluado = true;
        resultado = Double.toString(res);
        txtResultados.setText(resultado);
        operandoString.replace(0, operandoString.length(), resultado);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnDiv = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnMul = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnRes = new javax.swing.JButton();
        btnPunto = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnSigno = new javax.swing.JButton();
        btnSum = new javax.swing.JButton();
        btnEval = new javax.swing.JButton();
        btnPi = new javax.swing.JButton();
        btnTan = new javax.swing.JButton();
        btnCos = new javax.swing.JButton();
        btnSin = new javax.swing.JButton();
        btnXy = new javax.swing.JButton();
        btnX3 = new javax.swing.JButton();
        btnX2 = new javax.swing.JButton();
        btn1x = new javax.swing.JButton();
        btnNegacion = new javax.swing.JButton();
        btnLog = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        txtResultados = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ASM Calculator v0.5M");

        btnDiv.setText("/");
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivActionPerformed(evt);
            }
        });

        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btnMul.setText("*");
        btnMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMulActionPerformed(evt);
            }
        });

        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btnRes.setText("-");
        btnRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResActionPerformed(evt);
            }
        });

        btnPunto.setText(".");
        btnPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuntoActionPerformed(evt);
            }
        });

        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btnSigno.setText("+/-");
        btnSigno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignoActionPerformed(evt);
            }
        });

        btnSum.setText("+");
        btnSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumActionPerformed(evt);
            }
        });

        btnEval.setText("=");
        btnEval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvalActionPerformed(evt);
            }
        });

        btnPi.setText("pi");
        btnPi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPiActionPerformed(evt);
            }
        });

        btnTan.setText("tan");
        btnTan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTanActionPerformed(evt);
            }
        });

        btnCos.setText("cos");
        btnCos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCosActionPerformed(evt);
            }
        });

        btnSin.setText("sin");
        btnSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinActionPerformed(evt);
            }
        });

        btnXy.setText("x^y");
        btnXy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXyActionPerformed(evt);
            }
        });

        btnX3.setText("x^3");
        btnX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnX3ActionPerformed(evt);
            }
        });

        btnX2.setText("x^2");
        btnX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnX2ActionPerformed(evt);
            }
        });

        btn1x.setText("1/x");
        btn1x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1xActionPerformed(evt);
            }
        });

        btnNegacion.setText("n!");
        btnNegacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegacionActionPerformed(evt);
            }
        });

        btnLog.setText("sqrt");
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        btnC.setText("C");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnC, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnX2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXy))
                    .addComponent(btnX3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLog, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn1x, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPunto, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(btn7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn2)
                        .addGap(5, 5, 5)
                        .addComponent(btn3)
                        .addGap(5, 5, 5)
                        .addComponent(btnRes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn5)
                        .addGap(5, 5, 5)
                        .addComponent(btn6)
                        .addGap(5, 5, 5)
                        .addComponent(btnMul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn8)
                        .addGap(5, 5, 5)
                        .addComponent(btn9)
                        .addGap(5, 5, 5)
                        .addComponent(btnDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn0)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSigno, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnMul, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnRes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSigno, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnPi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn1x, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSum, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEval, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnC, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnXy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(46, 46, 46)
                                    .addComponent(btnX2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnX3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))))
        );

        txtResultados.setBackground(new java.awt.Color(255, 255, 255));
        txtResultados.setEditable(false);
        txtResultados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtResultados.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtResultados.setText("0.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        recibirDato(0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        recibirDato(1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        recibirDato(2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        recibirDato(3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        recibirDato(4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        recibirDato(5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        recibirDato(6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        recibirDato(7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        recibirDato(8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        recibirDato(9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btnPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuntoActionPerformed
        recibirDato(".");
    }//GEN-LAST:event_btnPuntoActionPerformed

    private void btnSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSinActionPerformed
        evaluado = false;
        this.operador = OP_SENO;
        evaluar(OP_SENO);
    }//GEN-LAST:event_btnSinActionPerformed

    private void btnCosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCosActionPerformed
        evaluado = false;
        this.operador = OP_COSENO;
        evaluar(OP_COSENO);
    }//GEN-LAST:event_btnCosActionPerformed

    private void btnTanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTanActionPerformed
        evaluado = false;
        this.operador = OP_TANGENTE;
        evaluar(OP_TANGENTE);
    }//GEN-LAST:event_btnTanActionPerformed

    private void btnXyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXyActionPerformed
        evaluar(OP_X_ELEVADO_Y);
    }//GEN-LAST:event_btnXyActionPerformed

    private void btnX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnX3ActionPerformed
        evaluado = false;
        this.operador = OP_X_ELEVADO_3;
        evaluar(OP_X_ELEVADO_3);
    }//GEN-LAST:event_btnX3ActionPerformed

    private void btnX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnX2ActionPerformed
        evaluado = false;
        this.operador = OP_X_ELEVADO_2;
        evaluar(OP_X_ELEVADO_2);
    }//GEN-LAST:event_btnX2ActionPerformed

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        evaluado = false;
        this.operador = OP_LOG;
        evaluar(OP_LOG);
    }//GEN-LAST:event_btnLogActionPerformed

    private void btnNegacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegacionActionPerformed
        evaluado = false;
        this.operador = OP_NEGACION;
        evaluar(OP_NEGACION);
    }//GEN-LAST:event_btnNegacionActionPerformed

    private void btnPiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPiActionPerformed
        evaluado = false;
        evaluar(OP_PI);
    }//GEN-LAST:event_btnPiActionPerformed

    private void btn1xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1xActionPerformed
        evaluado = false;
        this.operador = OP_UNO_ENTRE_X;
        evaluar(OP_UNO_ENTRE_X);
    }//GEN-LAST:event_btn1xActionPerformed

    private void btnSignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignoActionPerformed
        evaluado = false;
        evaluar(OP_CAMBIO_DE_SIGNO);
    }//GEN-LAST:event_btnSignoActionPerformed

    private void btnDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        evaluar(OP_DIVISION);
    }//GEN-LAST:event_btnDivActionPerformed

    private void btnMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulActionPerformed
        evaluar(OP_MULTIPLICACION);
    }//GEN-LAST:event_btnMulActionPerformed

    private void btnResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResActionPerformed
        evaluar(OP_RESTA);
    }//GEN-LAST:event_btnResActionPerformed

    private void btnSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumActionPerformed
        evaluar(OP_SUMA);
    }//GEN-LAST:event_btnSumActionPerformed

    private void btnEvalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvalActionPerformed
        resultado();
    }//GEN-LAST:event_btnEvalActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        inicializar();
    }//GEN-LAST:event_btnCActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn1x;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCos;
    private javax.swing.JButton btnDiv;
    private javax.swing.JButton btnEval;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnMul;
    private javax.swing.JButton btnNegacion;
    private javax.swing.JButton btnPi;
    private javax.swing.JButton btnPunto;
    private javax.swing.JButton btnRes;
    private javax.swing.JButton btnSigno;
    private javax.swing.JButton btnSin;
    private javax.swing.JButton btnSum;
    private javax.swing.JButton btnTan;
    private javax.swing.JButton btnX2;
    private javax.swing.JButton btnX3;
    private javax.swing.JButton btnXy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtResultados;
    // End of variables declaration//GEN-END:variables

}
