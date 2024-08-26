package recursion.basics;

public class PrintNameNTimes {

    public static void function(int i, String name){
        if(i == 0){
            return;
        }
        function(i-1, name);
        System.out.println("Name: " + name + " i: " + i );
    }

    public static void main(String[] args) {
        function(100, "Yash");
    }
}
