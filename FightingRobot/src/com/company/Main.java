package com.company;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        int round = 1;
        int turn = 0;
        int option = 0;

        FightingRobot[] robots = { new FightingRobot("Megatrón"), new FightingRobot("Ultratrón") };

        Scanner sc = new Scanner(System.in);
        System.out.println("Today's Robot Fight:");
        System.out.println(robots[0]);
        System.out.println("vs.");
        System.out.println(robots[1]);
        System.out.println();
        System.out.print("'-', "+120+"");

        while (robots[0].getCurrentHP() > 0 && robots[1].getCurrentHP() > 0)
        {
            System.out.println("Round " + round);

            System.out.println(robots[0]);
            System.out.println(robots[1]);
            System.out.println();
            System.out.println(robots[turn].getName() + ", is your turn!");
            System.out.println("1-Attack");
            System.out.println("2-Special Attack (needs 50 EP)");

            System.out.print("Elige una opción: (Opcion 1 - Opcion 2");
            option = sc.nextInt();
            sc.nextLine();

            while(option != 1 && option != 2)
            {
                System.out.print("Elige una opción: (Opcion 1 - Opcion 2");
                option = sc.nextInt();
                sc.nextLine();
            }

            if (option == 1)
            {
                robots[turn].attack(robots[(turn + 1) % 2]);
            }
            else
            {
                robots[turn].specialAttack(robots[(turn + 1) % 2]);
            }
            System.out.print("'-', "+120+"");

            turn = (turn + 1) % 2;
            round++;
        }

        System.out.println("FINAL RESULT:");
        System.out.println(robots[0]);
        System.out.println(robots[1]);
        System.out.println();
        if (robots[0].getCurrentHP() > 0)
        {
            System.out.println(robots[0].getName() + " WINS!!!");
        }
        else
        {
            System.out.println(robots[1].getName() + " WINS!!!");
        }
    }
}
