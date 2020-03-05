package de.novatec.showcase.supplier.util;

import java.util.Calendar;
import java.util.Random;

public class RandomTypes
{
   static private Random random = new Random();

   static public int getInt(int minvalue, int maxvalue)
   {
      int ran = -1;
      if (minvalue < maxvalue)
         ran = random.nextInt((maxvalue + 1) - minvalue) + minvalue;
      return ran;
   }

   static public String getString(String prefix, int minlength, int maxlength)
   {
      int length = 5;
      if (minlength <= maxlength && minlength >= 0)
      {
         length = (random.nextInt(maxlength + 1 - minlength) + minlength);
         length -= prefix.length();
      }
      

      char[] newString = new char[length];

      for (int i = 0; i < length; i++)
      {
         // only capital letter
         newString[i] = (char) (random.nextInt((90 + 1) - 65) + 65);
      }

      return prefix + (new String(newString));
   }

   static public Calendar getDate(Calendar from, Calendar to)
   {
      long a = to.getTimeInMillis() - from.getTimeInMillis();

      long ranDate = random.nextLong();
      if (ranDate < 0)
         ranDate *= -1;

      a = ranDate % a;
      a += from.getTimeInMillis();

      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(a);
      return cal;
   }
   
   static public double getDouble(int minvalue, int maxvalue)
   {
      int ran = getInt(minvalue*100, maxvalue*100);  
      return (double)ran / 100.0;
   }
}
