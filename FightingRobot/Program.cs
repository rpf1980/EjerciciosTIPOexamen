using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen3TINT_Ej1
{
    class FightingRobot
    {
        // Atributos
        private string name;
        private int maxHP;
        private int currentHP;
        private int maxEP;
        private int currentEP;
        private int attackPower;

        private static Random r = new Random();

        // Propiedades
        public string Name
        {
            get
            {
                return name;
            }
        }

        public int MaxHP
        {
            get
            {
                return maxHP;
            }
        }

        public int CurrentHP
        {
            get
            {
                return currentHP;
            }
        }

        public int MaxEP
        {
            get
            {
                return maxEP;
            }
        }

        public int CurrentEP
        {
            get
            {
                return currentEP;
            }
        }

        public int AttackPower
        {
            get
            {
                return attackPower;
            }
        }

        // Constructores
        public FightingRobot(string name, int maxHP, int maxEP, int attackPower)
        {
            if (name == "" || maxHP <= 0 || maxEP <= 0 || attackPower <= 0)
            {
                throw new Exception("Los parámetros del robot no son válidos");
            }
            else
            {
                this.name = name;
                this.maxHP = maxHP;
                this.currentHP = maxHP;
                this.maxEP = maxEP;
                this.currentEP = maxEP;
                this.attackPower = attackPower;
            }
        }

        public FightingRobot(string name)
        {
            if (name.Length > 0)
            {
                int[] valoresHP = { 200, 225, 250, 275, 300 };
                
                this.name = name;
                this.maxHP = valoresHP[r.Next(valoresHP.Length)];
                this.currentHP = this.maxHP;

                int energia = r.Next(1, 4 + 1);

                switch (energia)
                {
                    case 1: this.maxEP = 50; break;
                    case 2: this.maxEP = 80; break;
                    case 3: this.maxEP = 120; break;
                    default: this.maxEP = 150; break;
                }
                this.currentEP = this.maxEP;

                int[] valoresAtt = { 25, 30, 35, 40, 45, 50 };
                this.attackPower = valoresAtt[r.Next(valoresAtt.Length)];
            }
            else
            {
                throw new Exception("Debe especificar el nombre del robot");
            }
        }

        // Métodos
        public override string ToString()
        {
            return this.name + ": [HP=" + this.currentHP + "/" + this.maxHP + "] [EP=" +
                this.currentEP + "/" + this.maxEP + "] [Attack=" + this.attackPower + "]";
        }

        public void Attack(FightingRobot target)
        {
            target.currentHP = target.currentHP - this.attackPower;
            if (target.currentHP < 0)
            {
                target.currentHP = 0;
            }
        }

        public void SpecialAttack(FightingRobot target)
        {
            if (this.currentEP >= 50)
            {
                target.currentHP = target.currentHP - (this.attackPower * 2);
                if (target.currentHP < 0)
                {
                    target.currentHP = 0;
                }
                this.currentEP = this.currentEP - 50;
            }
            else
            {
                this.currentEP = 0;
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            int round = 1;
            int turn = 0;
            ConsoleKeyInfo option;

            FightingRobot[] robots = { new FightingRobot("Megatrón"), new FightingRobot("Ultratrón") };

            Console.WriteLine("Today's Robot Fight:");
            Console.WriteLine(robots[0]);
            Console.WriteLine("vs.");
            Console.WriteLine(robots[1]);
            Console.WriteLine();
            Console.Write(new string('-', 120));

            while (robots[0].CurrentHP > 0 && robots[1].CurrentHP > 0)
            {
                Console.WriteLine("Round " + round);

                Console.WriteLine(robots[0]);
                Console.WriteLine(robots[1]);
                Console.WriteLine();
                Console.WriteLine(robots[turn].Name + ", is your turn!");
                Console.WriteLine("1-Attack");
                Console.WriteLine("2-Special Attack (needs 50 EP)");

                option = Console.ReadKey(true);
                while (option.KeyChar != '1' && option.KeyChar != '2')
                {
                    option = Console.ReadKey(true);
                }

                if (option.KeyChar == '1')
                {
                    robots[turn].Attack(robots[(turn + 1) % 2]);
                }
                else
                {
                    robots[turn].SpecialAttack(robots[(turn + 1) % 2]);
                }
                Console.Write(new string('-', 120));

                turn = (turn + 1) % 2;
                round++;
            }

            Console.WriteLine("FINAL RESULT:");
            Console.WriteLine(robots[0]);
            Console.WriteLine(robots[1]);
            Console.WriteLine();
            if (robots[0].CurrentHP > 0)
            {
                Console.WriteLine(robots[0].Name + " WINS!!!");
            }
            else
            {
                Console.WriteLine(robots[1].Name + " WINS!!!");
            }

            Console.ReadKey();
        }

    }
}
