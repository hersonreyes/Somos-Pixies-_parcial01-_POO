package com.HARR.x00216519;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean continuar=true;
        do{
        try {
            //el sueldo minimo de la empresa es $300
            Empresa e = new Empresa("Almacenes Paola");
            int op = 0;
            if(op==0) continuar=false;
            do {
                System.out.println("1) Anadir empleado\n2) Despedir empleado\n3) Ver lista de empleados\n" +
                        "4) Calcular sueldo\n5) Mostrar totales\n0) salir");
                op = scan.nextInt();
                scan.nextLine();
                switch (op) {
                    case 1: {
                        String nombre;
                        String puesto;
                        double salario;
                        int mesesContrato, extension;

                        try {
                            int opdoc = 0;
                            System.out.println("Digite una opcion\n1) Servicio Profesional\n2) Plaza fija");
                            int op3 = scan.nextInt();
                            scan.nextLine();
                            if (op3 == 1) {
                                System.out.println("Digite el nombre del empleado");
                                nombre = scan.nextLine();
                                System.out.println("Digite el puesto del empleado");
                                puesto = scan.nextLine();
                                System.out.println("Digite el salario");
                                salario = scan.nextDouble();
                                scan.nextLine();
                                if(salario<300)
                                    throw new IncorrectNumbers("El sueldo minimo son $300");
                                System.out.println("Digite los meses de contrato");
                                mesesContrato = scan.nextByte();
                                if(mesesContrato<=0) throw new  IncorrectNumbers("Los meses de contrato " +
                                        "tienen que ser mayor a 0");
                                scan.nextLine();
                                e.addEmpleado(new ServicioProfesional(nombre, puesto, salario, mesesContrato));
                                ArrayList<Empleado> emp = e.getPlanilla();
                                for (Empleado x :
                                        emp) {
                                    if (x.nombre == nombre) {
                                        do {
                                            x.addDocumento();
                                            System.out.println("Digite 0 para dejar de anadir documentos ");
                                            opdoc = scan.nextByte();
                                            scan.nextLine();
                                        } while (opdoc != 0);
                                    }
                                }
                            } else if (op3 == 2) {
                                System.out.println("Digite el nombre del empleado");
                                nombre = scan.nextLine();
                                System.out.println("Digite el puesto del empleado");
                                puesto = scan.nextLine();
                                System.out.println("Digite el salario");
                                salario = scan.nextDouble();
                                if(salario<300)
                                    throw new IncorrectNumbers("El sueldo minimo son $300");
                                scan.nextLine();
                                ArrayList<Empleado> emp = e.getPlanilla();
                                System.out.println("Digite la extension del empleado");
                                extension = scan.nextInt();
                                scan.nextLine();
                                for (Empleado empleado : emp)
                                    if (extension == empleado.getExtension())
                                        throw new ExistingNumber("Numero de extension ya existe");
                                e.addEmpleado(new PlazaFija(nombre, puesto, salario, extension));
                                for (Empleado x :
                                        emp) {
                                    if (x.nombre == nombre) {
                                        do {
                                            x.addDocumento();
                                            System.out.println("Digite 0 para dejar de anadir documentos ");
                                            opdoc = scan.nextByte();
                                            scan.nextLine();
                                        } while (opdoc != 0);
                                    }
                                }
                            } else throw new IncorrectOption("Opcion no valida");
                        } catch (InputMismatchException ex) {
                            System.out.println("Usted esta ingresando datos de manera incorrecta");
                            scan.nextLine();
                        } catch (IncorrectOption ex) {
                            System.out.println(ex.getMessage());
                        } catch (ExistingName existingName) {
                            System.out.println(existingName.getMessage());
                        } catch (ExistingNumber existingNumber) {
                            System.out.println(existingNumber.getMessage());
                        } catch (IncorrectNumbers incorrectNumbers) {
                            System.out.println(incorrectNumbers.getMessage());
                        }
                        break;
                    }

                    case 2: {
                        System.out.println("Ingrese el nombre del empleado a eliminar");
                        String nombre = scan.nextLine();
                        e.quitEmpleado(nombre);
                        break;
                    }

                    case 3: {
                        ArrayList<Empleado> emp = e.getPlanilla();
                        for (Empleado x :
                                emp) {
                            System.out.println("Nombre: " + x.nombre);
                            System.out.println("Salario: " + x.salario);
                            for (Documento doc : x.getDoc()) {
                                System.out.println("Nombre del Documento: " + doc.getNombre());
                                System.out.println("Numero de Documento: " + doc.getNumero());
                            }
                            if (x instanceof ServicioProfesional) System.out.println("Servicio Profesional\n");
                            else if (x instanceof PlazaFija) System.out.println("Plaza Fija\n");
                        }
                        break;

                    }

                    case 4: {
                        try {
                            ArrayList<Empleado> emp = e.getPlanilla();
                            System.out.println("Ingrese el nombre del empleado que desea saber su sueldo liquido");
                            String nombre = scan.nextLine();
                            Empleado aux = null;

                            for (Empleado empleado : emp) {
                                if (empleado.getNombre().equalsIgnoreCase(nombre)){
                                    aux=empleado;
                                    System.out.println(CalculadoraImpuestos.calcularPago(empleado));}
                            }
                            if (aux == null) {
                                throw new NotExistingName("Nombre no existe");
                            }
                        } catch (NotExistingName ex) {
                            System.out.println(ex.getMessage());
                        }
                        ;
                        break;
                    }

                    case 5:{
                        try {
                            ArrayList<Empleado> emp = e.getPlanilla();
                            System.out.println("Ingrese el nombre del empleado para conocer sus descuentos de ISS,AFP Y RENTA");
                            String nombre = scan.nextLine();
                            Empleado aux = null;

                            for (Empleado empleado : emp) {
                                if (empleado.getNombre().equalsIgnoreCase(nombre)){
                                    aux=empleado;
                                    System.out.println(CalculadoraImpuestos.mostrarTotales(empleado));}
                            }
                            if (aux == null) {
                                throw new NotExistingName("Nombre no existe");
                            }
                        } catch (NotExistingName ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                }}

            } while (op != 0);

        } catch (InputMismatchException e) {
            System.out.println("Solo numeros porfavor");
            scan.nextLine();
            continuar=true;
        }
    }while(continuar);
}}