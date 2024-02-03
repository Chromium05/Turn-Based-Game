import java.util.Scanner;

public class TurnBased {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name, race, Class;
        race = Class = "";
        int choice;
        int rc, cl, HP, AP, limiter, tempHP, tempAP, tempEnemyHP;
        int turn = 1;
        tempHP = tempAP = tempEnemyHP = 0;
        limiter = 3;
        HP = AP = 0;
        int[][] required = {{150, 8}, {400, 20}, {700, 50}, {1500, 100}, {3500, 200}};
        int[][] obtained = {{100, 8}, {150, 12}, {200, 15}, {300, 25}, {500, 40}};
        int[][] enemy = {{100, 12}, {300, 12}, {500, 15}};
        String[] enemyID = {"Aboleth", "Death Slaad", "Red Dragon Wyrmling"};
        String[] terrain = {"Gehennna", "Panedemonium", "Atra-hasis", "Inazuma", "Eridu City"};

        // Character customization
        do { 
            System.out.println("\n==================");
            System.out.println("     WELCOME!     ");
            System.out.println("==================");

            System.out.print("\nName: ");
            name = input.next();

            System.out.println("\nChoose your race!");
            System.out.println("[1] Elf (HP: 100 / AP: 9)" + "\n[2] Human (HP: 150 / AP: 7)" + "\n[3] Orc (HP: 200 / AP: 5)");
            System.out.print("Input: ");
            rc = input.nextInt();

            System.out.println("\nChoose your class!");
            System.out.println("[1] Fighter (HP +100 / AP +3)" + "\n[2] Wizard (HP +50 / AP +5)" + "\n[3] Ranger (HP +80 / AP +4)");
            System.out.print("Input: ");
            cl = input.nextInt();

            switch(rc) {
                case 1: 
                    race = "Elf";

                    if (cl == 1) { 
                        Class = "Fighter";
                        HP = 200;
                        AP = 12;
                    }

                    if (cl == 2) { 
                        Class = "Wizard";
                        HP = 150;
                        AP = 14;
                    }

                    if (cl == 3) { 
                        Class = "Ranger";
                        HP = 180;
                        AP = 13;
                    }
                    break;
                case 2: 
                    race = "Human";
                    
                    if (cl == 1) { 
                        Class = "Fighter";
                        HP = 250;
                        AP = 10;
                    }

                    if (cl == 2) { 
                        Class = "Wizard";
                        HP = 200;
                        AP = 12;
                    }

                    if (cl == 3) { 
                        Class = "Ranger";
                        HP = 230;
                        AP = 11;
                    }
                    break;
                case 3:
                    race = "Orc";
                    
                    if (cl == 1) { 
                        Class = "Fighter";
                        HP = 300;
                        AP = 8;
                    }

                    if (cl == 2) { 
                        Class = "Wizard";
                        HP = 250;
                        AP = 10;
                    }

                    if (cl == 3) {
                        Class = "Ranger";
                        HP = 280;
                        AP = 9;
                    }
                    break;
            }


            System.out.println("\n========================");
            System.out.println(" Here's your character! ");
            System.out.println("========================");
            System.out.println("Name: " + name);
            System.out.println("Race: " + race);
            System.out.println("Class: " + Class);
            System.out.println("HP: " + HP);
            System.out.println("AP: " + AP);
            System.out.println("\nDo you wish to proceed with this character?" + "\n[1] Confirm" + "\n[2] Re-create character");
            System.out.print("Input: ");
            choice = input.nextInt();
        } while (choice == 2); // Press 1 to confirm your character/Press 2 to re-create character

        System.out.println("\nGoing outside...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Choose between farming or battling
        do {
            System.out.println("\n==================");
            System.out.println("   Current Stat   ");
            System.out.println("==================");
            System.out.println("Name: " + name);
            System.out.println("Race: " + race);
            System.out.println("Class: " + Class);
            System.out.println("HP: " + HP);
            System.out.println("AP: " + AP);
            System.out.println("\nAlright, what do you wanna do? (Farming permit: " + limiter + ")");
            System.out.println("[1] Farming" + "\n[2] Battle");
            System.out.print("Input: ");
            choice = input.nextInt();

            switch(choice) {
                case 1: // Farming Mode
                    if (limiter == 0){ // You can't farm when the limiter reaches zero
                        System.out.println("\nYou have reached the limit of the farming permit! Engage in a single battle to recover your farming permit.");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    System.out.println("\nWhere do you wanna farm?");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("[" + (i+1) + "] " + terrain[i] + " (HP +" + obtained[i][0] + " / AP +" + obtained[i][1] + ")");
                    }
                    System.out.print("Input: ");
                    choice = input.nextInt();
                    choice--;

                    if (HP < required[choice][0] && AP < required[choice][1]) {
                        System.out.println("\nHP OR AP REQUIRED IS NOT MET!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    } else {
                        System.out.println("\nFarming...");
                        tempHP = HP;
                        tempAP = AP;
                        HP += obtained[choice][0];
                        AP += obtained[choice][1];
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
        
                        System.out.println("\n=================");
                        System.out.println("     RESULTS     ");
                        System.out.println("=================");
                        System.out.println("HP: " + tempHP + "  -->  " + HP);
                        System.out.println("AP: " + tempAP + "   -->  " + AP);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        limiter--;
                    }
                break;

                case 2: // Battle Mode
                    System.out.println("\nLet's see what you got against these monsters!");
                    for (int i = 0; i < 3; i++) {
                        System.out.println("[" + (i+1) + "] " + enemyID[i] + " (HP: " + enemy[i][0] + " / AP: " + enemy[i][1] + ")");
                    }
                    System.out.print("Input: ");
                    choice = input.nextInt();
                    choice--;
                    tempHP = HP;
                    tempEnemyHP = enemy[choice][0];

                    while (tempHP > 0 && tempEnemyHP > 0) {
                        System.out.println("\n==== TURN " + turn + " ====");
                        System.out.println("- " + name + " attacking " + enemyID[choice] + "!");
                        tempEnemyHP -= AP; // Decreasing enemy's HP by player's AP
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("  " + name + "'s HP: " + tempHP);
                        System.out.println("  " + enemyID[choice] + "'s HP: " + tempEnemyHP);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("- " + enemyID[choice] + " attacking " + name + "!");
                        tempHP -= enemy[choice][1]; // Decreasing player's HP by enemy's AP
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("  " + name + "'s HP: " + tempHP);
                        System.out.println("  " + enemyID[choice] + "'s HP: " + tempEnemyHP);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        turn++;
                    }

                    if (tempHP > tempEnemyHP) {
                        System.out.println("\nYou Win!");
                    } else {
                        System.out.println("\nYou Lose!");
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (limiter == 0) {
                        System.out.println("\nFarming permit recovered!");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        limiter += 3;
                    }
                    
                    turn = 1;
                break;
            }

            System.out.println("\nDo you wanna quit?");
            System.out.println("[1] Continue playing" + "\n[2] Quit");
            System.out.print("Input: ");
            choice = input.nextInt();
        } while (choice == 1); // Press 1 to continue the game/Press 2 to exit the game

        System.out.println("\nSee you again next time!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        input.close();
    }
}
