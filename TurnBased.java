import java.util.Scanner;

public class TurnBased {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name, race, Class;
        race = Class = "-";
        int rc, cl, HP, AP, limiter;
        int turn = 1;
        int[][] required = {{150, 8}, {400, 20}, {700, 50}, {1500, 100}, {3500, 200}};
        int[][] obtained = {{100, 8}, {150, 12}, {200, 15}, {300, 25}, {500, 40}};
        int[][] enemy = {{100, 12}, {300, 12}, {500, 15}};
        String[] enemyID = {"Aboleth", "Death Slaad", "Red Dragon Wyrmling"};
        limiter = 3;
        HP = AP = 0;
        int choice;

        do {
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
            System.out.println(" Here's your character!");
            System.out.println("========================");
            System.out.println("Name: " + name);
            System.out.println("Race: " + race);
            System.out.println("Class: " + Class);
            System.out.println("HP: " + HP);
            System.out.println("AP: " + AP);
            System.out.println("\nDo you wish to proceed with the character?" + "\n[1] Re-create" + "\n[2] Start playing");
            System.out.print("Input: ");
            choice = input.nextInt();
        } while (choice == 1);

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
                    if (limiter == 0){
                        System.out.println("\nYou have reached the limit of the farming permit! Engage in a single battle to recover your farming permit.");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    System.out.println("\nWhere do you wanna farm?");
                    System.out.println("[1] Gehenna" + "\n[2] Pandemonium" + "\n[3] Atra-hasis" + "\n[4] Inazuma" +"\n[5] Eridu City");
                    System.out.print("Input: ");
                    choice = input.nextInt();
                    choice--;

                    if (HP <= required[choice][0] && AP <= required[choice][1]) {
                        System.out.println("\nHP OR AP REQUIRED IS NOT MET!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    } else {
                        System.out.println("\nFarming...");
                        HP += obtained[choice][0];
                        AP += obtained[choice][1];
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
        
                        System.out.println("\n================");
                        System.out.println("     RESULTS     ");
                        System.out.println("================");
                        System.out.println("HP: " + HP);
                        System.out.println("AP: " + AP);
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
                    System.out.println("[1] Aboleth (HP: 100 / AP: 12)" + "\n[2] Death Slaad (HP: 300 / AP: 10)" + "\n[3] Red Dragon Wyrmling (HP: 500 / AP: 15)");
                    System.out.print("Input: ");
                    choice = input.nextInt();
                    choice--;
                    int tempHP = HP;
                    int tempEnemyHP = enemy[choice][0];

                    while (tempHP > 0 && tempEnemyHP > 0) {
                        System.out.println("\n==== TURN " + turn + " ====");
                        System.out.println("- " + name + " attacking " + enemyID[choice] + "!");
                        tempHP -= enemy[choice][1];
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
                        tempEnemyHP -= AP;
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
                        limiter += 3;
                    }
                    
                    turn = 1;
                break;
            }

            System.out.println("\nDo you wanna quit?");
            System.out.println("[1] Continue playing" + "\n[2] Quit");
            System.out.print("Input: ");
            choice = input.nextInt();
        } while (choice == 1);

        input.close();
    }
}