package Controlador;

public class Terminal {

   public static void imprimeMensaje
                               (String mensaje) {
      System.out.println(mensaje + " ");
      System.out.flush();
   }

   public static void imprimeMensaje() {
      System.out.println();
      System.out.flush();
   }

   public static String leeCadena() {
      int caracter;
      String cadena = "";
      boolean fin = false;
      while (!fin) {
         try {
            caracter = System.in.read();
            if (caracter < 0 || 
                (char)caracter == '\n')
               fin = true;
            else
               cadena += (char)caracter; 
         } catch(java.io.IOException e) {  
               fin = true;
         }
      }
      return cadena;
   }

   public static String leeCadena
                           (String mensaje) {
      imprimeMensaje(mensaje);
      return leeCadena();
   }

   public static int leeEntero(String mensaje) {
      while(true) {
         imprimeMensaje(mensaje);
         try {
            return Integer.valueOf(
                  leeCadena().trim()).intValue();
         } catch(NumberFormatException e) {
            System.out.println
               ("ERROR: Vuelve a intentarlo.");
         }
      }
   }

}


/******** Fin de Terminal.java ***************/
