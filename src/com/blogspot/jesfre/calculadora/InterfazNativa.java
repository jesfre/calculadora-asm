package com.blogspot.jesfre.calculadora;

/**
 *
 * @author Jorge Ruiz
 */
public class InterfazNativa {

    public static native double sumar(double opa, double opb);
    public static native double restar(double opa, double opb);
    public static native double dividir(double opa, double opb);
    public static native double multiplicar(double opa, double opb);
    public static native double cambioSigno(double opa);
    public static native double seno(double opa);
    public static native double coseno(double opa);
    public static native double tangente(double opa);
    public static native double x_y(double opx, int opy);
    public static native double x_3(double opx);
    public static native double x_2(double opx);
    public static native double log(double opa);
    public static native double negar(int opa);
    public static native double pi();
    public static native double uno_x(double opx);

    static {
        System.loadLibrary("calculadora");
    }

}
