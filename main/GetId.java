package main;

public class GetId
{
    public static int getId(String arg)
    {
        String strId = "";
        int i = 1;
        while(arg.charAt(i) != ']')
        {
            strId = strId + arg.charAt(i);
            ++i;
        }
        return Integer.valueOf(strId);
    }
}
