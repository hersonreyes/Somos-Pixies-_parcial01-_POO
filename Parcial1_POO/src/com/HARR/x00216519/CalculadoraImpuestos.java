package com.HARR.x00216519;

public final class CalculadoraImpuestos {
    private static double totalRenta = 0;
    private static double totalISSS = 0;
    private static double totalAFP = 0;

    private CalculadoraImpuestos() {
    }

    public static double calcularPago(Empleado empleado) {
        double restante = empleado.getSalario();
        if (empleado instanceof PlazaFija) {
            totalAFP = 0.0625 * empleado.getSalario();
            totalISSS = 0.03 * empleado.getSalario();
            restante = empleado.getSalario() - totalAFP - totalISSS;
            if (restante >= 0.01 && restante < 472) {
                totalRenta = 0;
            } else if (restante >= 472 && restante < 895.24) {
                totalRenta = 0.1 * (restante - 472) + 17.67;
            } else if (restante >= 895.24 && restante < 2038.10) {
                totalRenta = 0.2 * (restante - 895.24) + 60;
            } else if (restante >= 2038.10) {
                totalRenta = 0.3 * (restante - 2038.10) + 288.57;
            }
        }else if (empleado instanceof ServicioProfesional){
            totalRenta = 0.1*empleado.getSalario();
        }
            return restante-totalRenta;
        }

    public static String mostrarTotales(Empleado empleado){
        double restante = empleado.getSalario();
        if (empleado instanceof PlazaFija) {
            totalAFP = 0.0625 * empleado.getSalario();
            totalISSS = 0.03 * empleado.getSalario();
            restante = empleado.getSalario() - totalAFP - totalISSS;
            if (restante >= 0.01 && restante < 472) {
                totalRenta = 0;
            } else if (restante >= 472 && restante < 895.24) {
                totalRenta = 0.1 * (restante - 472) + 17.67;
            } else if (restante >= 895.24 && restante < 2038.10) {
                totalRenta = 0.2 * (restante - 895.24) + 60;
            } else if (restante >= 2038.10) {
                totalRenta = 0.3 * (restante - 2038.10) + 288.57;
            }
        }else if (empleado instanceof ServicioProfesional){
            totalRenta = 0.1*empleado.getSalario();
        }
        return "Total ISSS: $"+totalISSS+"\nTotal Renta: $"+totalRenta+"\nTotal AFP: $"+totalAFP+"\nTotal de descuento realizado: "+ (totalISSS+totalRenta+totalAFP);

    }

    }
