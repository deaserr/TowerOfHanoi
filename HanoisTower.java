//Programmer: Dean Serrano
//Class: CS240
//Date: 05/09/2024
//MidTerm: Hanoi's Tower
//Purpose: Demonstrate a solution to the Tower of Hanoi using recursion and iteration
//Issues in the code: The iteritve method is not fully functional

import java.util.LinkedList;
import java.util.Scanner;

public class HanoisTower
{
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args)
    {
        int userCommand;

        intro();

        int loop = 0;
        while(loop == 0)
        {
            System.out.println("What method would you like to use to solve the puzzle?");
            System.out.print("Recursive(1) or Iterative(2): ");
            userCommand = input.nextInt();

            switch (userCommand) {
                case 1:
                    System.out.println("\nThis recursive solution demonstrates the steps (peg movements) for an n disk, 3 tower puzzle.");
                    System.out.print("How many disks would you like to use(n = ?): ");
                    int diskNum = input.nextInt();
                    hanoiRecur(diskNum, 1, 3);
                    loop++;
                    break;
                case 2:
                    System.out.println("This programs iterative solution allows you to specify the disk and peg numebr!");
                    runHanoiItr();
                    loop++;
                    break;
                default:
                    System.out.println("!Invalid input! please try again");
                    break;
            }
        }
    }//end of main

    //run hanoiitr asking for user input
    public static void runHanoiItr()
    {
        int diskNum;
        int pegNum;

        System.out.print("How many disks would you like?: ");
        diskNum = input.nextInt();
        System.out.print("How many pegs would you like?: ");
        pegNum = input.nextInt();

        hanoiItr(diskNum, pegNum);
    }//end of runHanoiItr

    //solves tower of hanoi iterativly
    public static void hanoiItr(int diskNum, int pegNum)
    {
        int start = 1;
        int end = pegNum;
        int disks = diskNum;
        LinkedList<LinkedList<Integer>> board = new LinkedList<LinkedList<Integer>>();

        //initailizes pegs
        for(int i = 0; i < pegNum; i++)
        {
            board.add(new LinkedList<>());
        }

        //Places disks onto first peg
        for(int i = 0; i < diskNum; i++)
        {
            board.get(0).add(disks);
            disks--;
        }

        printBoard(board, diskNum, pegNum);

        int currentPeg = 0;

        //while the end peg is not full
        while(board.get(pegNum - 1).size() != diskNum)
        {
            int currentDisk = board.get(currentPeg).getLast();
            
            //moves disk to the disk to the right
            if(board.get(currentPeg + 1).peekLast() == null)
            {
                board.get(currentPeg + 1).add(currentDisk);
                board.get(currentPeg).removeLast();
            }
            //if there is a disk on the next peg first check if its a valid move
            else if(board.get(currentPeg).peekLast() < board.get(currentPeg + 1).peekLast())
            {
                board.get(currentPeg + 1).add(currentDisk);
                board.get(currentPeg).removeLast();
            }
            //if not a valid move, move to the next peg over
            else
            {
                board.get(currentPeg + 2).add(currentDisk);
                board.get(currentPeg).removeLast();
            }
        }
        printBoard(board, diskNum, pegNum);
    }//end of hanoiItr

    //Shows currentl statis of board
    public static void printBoard(LinkedList<LinkedList<Integer>> board, int diskNum, int pegNum)
    {
        for(int i = 0; i < pegNum; i++)
        {
            //if peg empty
            if(board.get(i).size() == 0)
            {
                //System.out.println("Peg " + (i + 1) + " is empty.");
                System.out.print("|");
                for(int j = 0; j < diskNum * 2; j++)
                {
                    System.out.print("-");
                }
                System.out.println();
            }
            else
            {
                System.out.print("|");
                for(int j = 0; j < board.get(i).size(); j++)
                {
                    System.out.print(board.get(i).get(j) + " - ");
                }
                System.out.println();
            }
        }
    }//end of printBoard

    //Hanoi's tower recursive solution only for a three disk, three peg game.
    public static void hanoiRecur(int diskNum, int start, int end)
    {   
        if(diskNum == 1) //base case
        {
            printMove(start, end);
        }
        else
        {
            int other = 6 - (start + end);
            hanoiRecur(diskNum - 1, start, other);
            printMove(start, end);
            hanoiRecur(diskNum - 1, other, end);
        }
    }//end of hanoiRecur

    //prints moves of hanoiRecur
    public static void printMove(int start, int end)
    {
        System.out.println(start + " -> " + end);
    }//end of printMove

    //prints introduction to user about the program
    public static void intro()
    {
        System.out.println("Welcome to the Tower of Hanoi!");
        System.out.println("------------------------------\n");
        System.out.println("- In this program you can watch the step-by-step solutions to the Tower of Hanoi");
        System.out.println("in both a recursive and iterative variation");
        System.out.println("- For the iterative version you will also have the option to speify");
        System.out.println("a number of disks and pegs\n");
    }//end of intro
}