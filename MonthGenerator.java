import java.util.Scanner;
/**
 * This program displays the month of a given year using
 * Zeller's formula.
 * Date modified: 12 October 2018
 * @author Akash Kumar
 * @version 1.0
 */
public class MonthGenerator
{
  public static void main(String[] args)
  {
    int m = 0, n = 0; // month and year.

    Scanner kb = new Scanner(System.in);
    // get the month (m) and year (n) as input from the user.
    while(m < 1 || m > 12)
    {
      System.out.print("Enter an integer for the month (1-12): ");
      m = kb.nextInt();
    }
    while(n < 1)
    {
      System.out.print("Enter a year: ");
      n = kb.nextInt();
    }

    String[] months = {"January", "February", "March", "April", "May", "June",
                      "July", "August", "September", "October", "November", "December"};

    // set the month's name.
    String month = months[m-1];

    final int firstDayOfWeek = zeller(m,n);

    // check if the year is a leap year.
    boolean leapYear = false;
    if(n%4 == 0)
    {
      if(n%100 == 0)
      {
        if(n%400 == 0)
          leapYear = true;
      }
      else
      {
        leapYear = true;
      }
    }

    // set the number of days in the month.
    int days = 0;
    switch(month)
    {
      case "January":
        days = 31;
        break;
      case "February":
        if(leapYear)
          days = 29;
        else
          days = 28;
        break;
      case "March":
        days = 31;
        break;
      case "April":
        days = 30;
        break;
      case "May":
        days = 31;
        break;
      case "June":
        days = 30;
        break;
      case "July":
        days = 31;
        break;
      case "August":
        days = 31;
        break;
      case "September":
        days = 30;
        break;
      case "October":
        days = 31;
        break;
      case "November":
        days = 30;
        break;
      case "December":
        days = 31;
        break;
    } // end switch

    makeMonth(month, n, days, firstDayOfWeek);
  } // end main

  /* uses Zeller's formula to find the first day of the week for a given month
     in a given year. */
  public static int zeller(int m, int n)
  {
    /* Zeller's formula: f = (k + [(13m-1)/5] + d + [d/4] + [c/4] + 5c) mod 7;
       f = day of the week, where Sunday = 0, Monday = 1,..., Saturday = 6
       k = day of the month, d = last 2 digits of the year.
       c = century, first 2 digits of the year.
       m = month, March = 1,..., December = 10, January = 11, February = 12, where
           January and February are treated as months of the previous year.
       [] = greatest integer function, round decimals down. */

    // calculate the day of the week of day 1 using Zeller's formula.
    int zellerMonth = m - 2;
    int zellerYear = n;
    // for January and February, add 12 to the zellerMonth and subtract 1 from the year.
    if(zellerMonth < 1)
    {
        zellerMonth += 12;
        zellerYear -= 1;
    }
    int k = 1, d = zellerYear%100, c = zellerYear/100;
    int f = (k + ((13*zellerMonth-1)/5) + d + (d/4) + (c/4) + (5*c)) % 7;

    return f;
  }

  // used to create and display the month.
  public static void makeMonth(String month, int year, int days, int firstDayOfWeek)
  {
    // print the month, year and weekdays.
    System.out.println();
    System.out.println();
    System.out.println(month + " " + year);
    System.out.println(" S  M Tu  W Th  F  S");
    System.out.println();

    // calculate and create the indent of the first week.
    String indent = "";
    for(int i = 0; i < firstDayOfWeek; i++)
    {
      if(i == 0)
        indent += "  ";
      else
        indent += "   ";
    }

    System.out.print(indent);
    for(int i = 1, dayOfWeek = firstDayOfWeek; i <= days; i++, dayOfWeek++)
    {
      if(i > 9 && (dayOfWeek > 0))
        System.out.print(" " + i);
      else if(i > 9 && (dayOfWeek == 0))
        System.out.print(i);
      else if(i < 10 && (dayOfWeek > 0))
        System.out.print("  " + i);
      else if(i < 10 && (dayOfWeek == 0))
        System.out.print(" " + i);

      if(dayOfWeek == 6)
      {
        System.out.println();
        dayOfWeek = -1;
      }
    } // end for

    System.out.println();
    System.out.println();
  } // end makeMonth method
} // end class
