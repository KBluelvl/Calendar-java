import java.util.Scanner;

/**
 *
 * @author g58137
 */
public class calendrier {
/**
 * assigne un numéro au mois 
 * @param mois
 * @return le nom du mois
 */
    public static String nomMois(int mois) {
        if(mois>12){
            throw new IllegalArgumentException("le mois est compris entre 1 et 12. ");
        }
        String nomDuMois = "";
        nomDuMois = switch (mois) {
            case 1 -> "Janvier";
            case 2 -> "Février";
            case 3 -> "Mars";
            case 4 -> "Avril";
            case 5 -> "Mai";
            case 6 -> "Juin";
            case 7 -> "Juillet";
            case 8 -> "Août";
            case 9 -> "Septembre";
            case 10 -> "Octobre";
            case 11 -> "Novembre";
            case 12 -> "Decembre";
            default -> "";
        };
        return nomDuMois;
    }
/**
 * affiche le titre du calendrier qui montre le mois et l'année
 * @param mois
 * @param année 
 */
    public static void afficherTitre(int mois, int année) {
         if(mois>12){
            throw new IllegalArgumentException("Le mois est compris entre 1 et 12. ");
        }
        System.out.println("==========================");
        System.out.println("        " + nomMois(mois) + " " + année);
        System.out.println("==========================");
    }
/**
 * affiche l'entête du calendrier
 */
    public static void afficherEntête() {
        System.out.println("Lu  Ma  Me  Je  Ve  Sa  Di");

    }
/**
 * gère l'afichage des jours et du décalage en début de mois (nombre d'espaces )
 * @param décalage
 * @param nombreJours 
 */
    public static void afficherMois(int décalage, int nombreJours) {
        if(décalage >6 || nombreJours>31){
            throw new IllegalArgumentException ("Le décalage est compris entre 0 et 6 et le nombre de jours entre 1 et 31. ");
        }
        for (int i = 1; i <= décalage; i++) {
            System.out.print("    ");
        }
        for (int jour = 1; jour <= nombreJours; jour++) {
            if (jour < 10) {
                System.out.print("0" + jour + "  ");

            } else {
                System.out.print(jour + "  ");
            }
            if ((décalage + jour) % 7 == 0) {
                System.out.println("");
            }
        }
    }
     /**
     * calcule si une année est bissextile
     * @param année
     * @return si oui ou non elle est bissextile
     */
    public static boolean estBissextile(int année){
      boolean estBissextile = false;
      if(année%4==0 && année%100 !=0){
         estBissextile = true; 
      }
      else if(année % 400 ==0){
          estBissextile = true;
      }
      return estBissextile;
    }
    /**
     * calcule le nombre de jours dans chaque mois 
     * @param mois
     * @param année
     * @return le nombre de jours du mois donné
     */
    public static int nombreJours(int mois, int année){
        if(mois>12){
            throw new IllegalArgumentException("Le mois est compris entre 1 et 12");
        }
        int nbJours = 0;
           switch(mois){
               case 1 -> nbJours = 31;
               case 2 -> {
                   if(estBissextile(année)){
                       nbJours = 29;
                   }
                   else{
                       nbJours = 28;
                   }
            }
               case 3 -> nbJours = 31;
               case 4 -> nbJours = 30;
               case 5 -> nbJours = 31;
               case 6 -> nbJours = 30;
               case 7 -> nbJours = 31;
               case 8 -> nbJours = 31;
               case 9 -> nbJours = 30;
               case 10 -> nbJours = 31;
               case 11 -> nbJours = 30;
               case 12 -> nbJours = 31;
           } 
        return nbJours;
    }
    /**
     * calcule le numéro du jour
     * @param jour
     * @param mois
     * @param année
     * @return le numéro du jour
     */
    public static int numéroJour(int jour,int mois,int année){
        if(jour>nombreJours(mois,année)){
            throw new  IllegalArgumentException("la date est correcte, c’est-à-dire que"+ 
                    "le mois est compris entre 1 et 12 et le jour est compris entre 1 et le nombre de jours dans le mois.");
        }
        int nb = (jour+(((mois+1)*13)/5)+(année%100)+((année%100)/4)+((année/100)/4)+(5*(année/100))+5)%7;
        return nb;
    }
/**
 * la méthode principale : qui demande l'année et le mois et affiche le calendrier
 * @param args 
 */
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Veuillez écrire le mois. ");
        int mois = clavier.nextInt();
        System.out.println("Veuillez écrire l'année. ");
        int année = clavier.nextInt();
        afficherTitre(mois, année);
        afficherEntête();
        int décalage = numéroJour(1,mois,année);
        afficherMois(décalage, nombreJours(mois, année));
    }
}
