package com.HARR.x00216519;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> planilla = new ArrayList<>();

    Scanner in = new Scanner (System.in);

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getPlanilla() {
        return planilla;
    }

    public void addEmpleado(Empleado esg){
        planilla.add(esg);
    }

    public void quitEmpleado (String nombre){
        Empleado aux = null;

        for(Empleado e : planilla){
            if(e.getNombre().equalsIgnoreCase(nombre))
                aux = e;
        }
        if(aux != null) {
            planilla.remove(aux);
        }
    }

}
