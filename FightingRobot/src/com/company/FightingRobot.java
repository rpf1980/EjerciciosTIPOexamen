package com.company;

import java.security.InvalidParameterException;
import java.util.Random;

public class FightingRobot
{
    //Atributos
    private String name;     // Nombre del robot
    private int maxHP;       // Puntos de vida máximos
    private int currentHP;   // Puntos de vida actuales... cuando vaya recibiendo palos irá bajando
    private int maxEP;       // Puntos de energía máximos
    private int currentEP;   // Puntos energía actuales... cuando gaste energía irá bajando
    private int attackPower; // Potencia ataque del robot

    //Propiedades de los atributos
    public String getName()
    {
        return name;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public int getMaxEP()
    {
        return maxEP;
    }

    public int getCurrentEP()
    {
        return currentEP;
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    //Constructores
    public FightingRobot(String name, int maxHP, int maxEP, int attackPower)
    {
        if(!name.isEmpty())
        {
            this.name = name;
        }
        else
        {
            throw new InvalidParameterException("El campo name no puede estar vacío.");
        }

        if(maxHP > 0)
        {
            this.maxHP = maxHP;
        }
        if(maxEP > 0)
        {
            this.maxEP = maxEP;
        }
        if(attackPower > 0)
        {
            this.attackPower = attackPower;
        }

        this.currentHP = maxHP;
        this.currentEP = maxEP;
    }

    public FightingRobot(String name)
    {
        if(name.isEmpty())
        {
            throw new InvalidParameterException("EL campo nombre no puede estar vacío");
        }
        else
        {
            this.name = name;
        }

        Random rd = new Random();
        int posLife = -1, posEnergy = -1, posAttack = -1;
        int rdLife = 0, rdEnergy = 0, rdAttack = 0;
        int i;

        int[] pointsRandomLife = {200, 225, 250, 275, 300};
        int[] pointsRandomEnergy = {50, 80, 120, 150};
        int[] pointsRandomAttack = {25, 30, 35, 40, 45, 50};


        //Recorremos rango randomLife y anotamos dato en puntos de vida
        posLife = rd.nextInt(5);
        rdLife = pointsRandomLife[posLife];
        this.maxHP = rdLife;

        //Recorremos rango randomEnergy y anotamos dato en puntos de energía
        posEnergy = rd.nextInt(4);
        rdEnergy = pointsRandomEnergy[posEnergy];
        this.maxEP = rdEnergy;

        //Recorremos rango randomEnergy y anotamos dato en potencia de ataque
        posAttack = rd.nextInt(6);
        rdAttack = pointsRandomAttack[posAttack];
        this.attackPower = rdAttack;

        this.currentHP = maxHP;
        this.currentEP = maxEP;

    }

    //Métodos
    public String toString()
    {

        return ""+this.name+": [HP="+this.currentHP+"/"+this.maxHP+"] [EP="+this.currentEP+"/"+this.maxEP+"] [Attack="+this.attackPower+"]";
    }

    public void attack(FightingRobot target)
    {
        target.currentHP = target.currentHP - attackPower; //Restamos puntos al robot que atacamos

        if(target.currentHP < 0)
        {
            target.currentHP = 0;
        }
    }

    public void specialAttack(FightingRobot target)
    {
        if(currentEP < 50)
        {
            target.currentEP = currentEP;
            currentEP = 0;
        }
        else
        {
            target.currentHP = target.currentHP - (attackPower*2); //Restamos doble de puntos al robot que atacamos
            if(target.currentHP < 0) //Volvemos a comprobar que la vida actual no da nº negativo
            {
                target.currentHP = 0;
            }
            currentEP = currentEP - 50; //Sobre el robot atacante restamos 50 de su energía actual
        }
    }
}
